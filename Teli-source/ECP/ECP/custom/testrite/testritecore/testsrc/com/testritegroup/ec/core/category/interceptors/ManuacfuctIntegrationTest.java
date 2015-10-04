/**
 *
 */
package com.testritegroup.ec.core.category.interceptors;

import static org.fest.assertions.Assertions.assertThat;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author liuxiangwin
 *
 */
public class ManuacfuctIntegrationTest extends ServicelayerTest
{
	private static final String code_NAME = "AlanLiuXiang";
	@Resource
	private ModelService modelService;
	@Resource
	private FlexibleSearchService flexibleSearchService;
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private SearchRestrictionService searchRestrictionService;

	@Before
	public void setUp() throws Exception
	{
		//createCoreData();
		//createDefaultCatalog();
	}

	//@Test
	public void testValidationInterceptor()
	{
		//given
		final ProductModel product = modelService.create(ProductModel.class);

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("electronicsProductCatalog",
				"Online");
		final Random rnd = new Random();
		final String code = code_NAME + String.valueOf(rnd.nextInt());
		product.setCode(code);
		product.setCatalogVersion(catalogVersionModel);
		product.setManufacturerName("Sony");
		product.setAllowOnlineSell(false);
		//when
		try
		{
			modelService.save(product);
			Assert.fail();
		}
		//then
		catch (final ModelSavingException e)
		{
			assertThat(e.getCause().getClass()).isEqualTo(InterceptorException.class);
			assertThat(e.getMessage()).contains("Product is not allowed online sell");
		}
	}

	@Test
	public void testEventSending() throws Exception
	{
		//given
		final ProductModel product = modelService.create(ProductModel.class);
		//final String evenNews = "Manaufacture Name:";

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("electronicsProductCatalog",
				"Online");

		final Random rnd = new Random();
		final String code = code_NAME + String.valueOf(rnd.nextInt());
		product.setCode(code);
		product.setCatalogVersion(catalogVersionModel);
		product.setManufacturerName("Dell");
		product.setAllowOnlineSell(true);
		//when
		modelService.save(product);

		//then}
		final OrderModel orderModel = findLastNews();
		//final String messgage = productModel.getDescription(Locale.ENGLISH);
		final String messgage = orderModel.getStatusInfo();
		Assert.assertNotNull(messgage);
		//assertThat(messgage.contains(evenNews));
	}

	private OrderModel findLastNews()
	{
		try
		{
			//final int maxResultCount = limit == null ? DEFAULT_LIMIT : limit.intValue();
			searchRestrictionService.disableSearchRestrictions();

			catalogVersionService.setSessionCatalogVersion("electronicsProductCatalog", "Online");
			final StringBuilder builder = new StringBuilder();
			builder.append("SELECT {n:").append(OrderModel.PK).append("} ");
			builder.append("FROM {").append(OrderModel._TYPECODE).append(" AS n} ");
			//builder.append("WHERE ").append("{n:").append(ProductModel.).append("} IS NULL ");
			builder.append("ORDER BY ").append("{n:").append(ProductModel.CREATIONTIME).append("} DESC");

			final List<OrderModel> list = flexibleSearchService.<OrderModel> search(builder.toString()).getResult();
			if (list.isEmpty())
			{
				return null;
			}
			else
			{
				return list.get(0);
			}
		}
		finally
		{
			searchRestrictionService.enableSearchRestrictions();
		}
	}

}
