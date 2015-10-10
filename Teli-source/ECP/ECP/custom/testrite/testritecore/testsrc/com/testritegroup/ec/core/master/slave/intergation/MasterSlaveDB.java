/**
 *
 */
package com.testritegroup.ec.core.master.slave.intergation;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.AbstractTenant;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.Tenant;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.regioncache.region.CacheRegion;
import de.hybris.platform.regioncache.region.CacheRegionProvider;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


@IntegrationTest
public class MasterSlaveDB extends ServicelayerTest
{
	private static final String PRODUCT_CATALOG = "Default";
	private static final String CATALOG_VERSION = "Staged";
	private static final String SLAVE_DATASOURCE = "dayoneslave";
	private static final String ALT_DATASOURCE = "dayonealt";
	private static int COUNT_PRODUCTS = 0;
	private static final String CODE_NAME = "Master-Slave-Test";
	private CatalogVersionModel catalogVersionModel;
	@Resource
	private FlexibleSearchService flexibleSearchService;
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	CacheRegionProvider cacheRegionProvider;
	@Resource
	private ModelService modelService;

	@Before
	public void setUp() throws Exception
	{
		jaloSession.setUser(UserManager.getInstance().getAdminEmployee()); //because of some restrictions
		catalogVersionModel = catalogVersionService.getCatalogVersion(PRODUCT_CATALOG, CATALOG_VERSION);
		catalogVersionService.setSessionCatalogVersion(PRODUCT_CATALOG, CATALOG_VERSION); //does not seem to work
		COUNT_PRODUCTS = countProducts();
	}

	@Test
	/**
	 * To show how to switch to Salve DB
	 *
	 */
	public void testActivateSlaveDatasource()
	{
		insertProduct();
		Assert.assertEquals(COUNT_PRODUCTS + 1, countProducts());
		clearQueryCache(); //to avoid caching from countProducts(). FlexibleSearch.CACHE_TTL not enough.
		final Tenant tenant = Registry.getCurrentTenant();
		try
		{
			((AbstractTenant) tenant).cancelForceMasterMode(); //required or activateSlaveDataSource() does not work
			tenant.activateSlaveDataSource(SLAVE_DATASOURCE);
			Assert.assertEquals(SLAVE_DATASOURCE, tenant.getDataSource().getID());
			Assert.assertTrue(tenant.isSlaveDataSource());
			Assert.assertEquals(COUNT_PRODUCTS, countProducts());
		}
		finally
		{
			tenant.deactivateSlaveDataSource();
		}
	}

	//@Test
	/**
	 * To show how to switch to Alt DB
	 *
	 */
	public void testActivateAltDatasource() throws InterruptedException
	{
		insertProduct();
		Assert.assertEquals(COUNT_PRODUCTS + 1, countProducts());
		clearQueryCache(); //to avoid caching from countProducts(). FlexibleSearch.CACHE_TTL not enough.
		final Tenant tenant = Registry.getCurrentTenant();
		try
		{
			tenant.activateAlternativeMasterDataSource(ALT_DATASOURCE);
			Assert.assertEquals(ALT_DATASOURCE, tenant.getDataSource().getID());
			Assert.assertEquals(COUNT_PRODUCTS, countProducts());
		}
		finally
		{
			tenant.deactivateAlternativeDataSource();
		}
	}

	//@Test
	/**
	 * In a multi-threaded batch, it will not work if you switch in the parent thread do not forget to switch inside the
	 * runnable thread
	 *
	 */
	public void testActivateAltDatasourceWithMultithreads() throws InterruptedException
	{
		insertProduct();
		Assert.assertEquals(COUNT_PRODUCTS + 1, countProducts());
		clearQueryCache();
		//to avoid caching from countProducts(). FlexibleSearch.CACHE_TTL not enough.
		final ExecutorService executor = Executors.newFixedThreadPool(10);
		final Tenant tenant = Registry.getCurrentTenant();
		final Map<Integer, Integer> counts = new HashMap();
		for (int i = 0; i < 5; i++)
		{
			final int counter = i;
			executor.execute(new Runnable()
			{
				public void run()
				{
					try
					{
						Registry.setCurrentTenant(tenant);
						JaloSession.getCurrentSession().activate();
						JaloSession.getCurrentSession().setUser(UserManager.getInstance().getAdminEmployee());
						catalogVersionService.setSessionCatalogVersion(PRODUCT_CATALOG, CATALOG_VERSION);
						tenant.activateAlternativeMasterDataSource("dayonealt");
						counts.put(Integer.valueOf(counter), Integer.valueOf(countProducts()));
					}
					finally
					{
						JaloSession.getCurrentSession().close();
						Registry.unsetCurrentTenant();
					}
				}
			});
		}
		executor.shutdown();
		executor.awaitTermination(200, TimeUnit.SECONDS);
		for (int i = 0; i < 5; i++)
		{
			Assert.assertEquals(COUNT_PRODUCTS, counts.get(Integer.valueOf(i)).intValue());
		}
	}

	private void insertProduct()
	{
		final ProductModel productModel = modelService.create(ProductModel.class);
		productModel.setCode(CODE_NAME);
		productModel.setCatalogVersion(catalogVersionModel);
		productModel.setName("Master Slave Sample Name");
		productModel.setDescription("Master Slave Sample Description TEST Product ");
		productModel.setEan("EAN Sample");
		modelService.saveAll();
	}

	private int countProducts()
	{
		final String query = "select {pk} from {product}";
		final SearchResult<ProductModel> searchResult = flexibleSearchService.search(query);
		return searchResult.getResult().size();
	}

	private void clearQueryCache()
	{
		final CacheRegion region = cacheRegionProvider.getRegionByName("queryCacheRegion").get(0);
		region.clearCache();
	}

}