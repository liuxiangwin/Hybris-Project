/**
 *
 */
package com.testritegroup.ec.core.category.dao.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.testritegroup.ec.core.category.dao.ProductSearchDAO;


/**
 * @author Alan Liu
 *
 */
//@Component(value = "productSearchDAO")
public class DefaultProductSearchDAO extends DefaultGenericDao<ProductModel> implements ProductSearchDAO
{

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Autowired
	private SearchRestrictionService searchRestrictionService;

	@Autowired
	private CatalogVersionService catalogVersionService;

	@Autowired
	private ModelService modelService;



	private static final String QUERY_ALL_PRODCUCT = "SELECT {p:PK} " + "FROM {Product AS p} ";

	private static final int DEFAULT_LIMIT = 100;

	//final String queryString2 = "SELECT {p:" + ProductModel.PK + "} "//
	//+ "FROM {" + ProductModel._TYPECODE + " AS p} ";

	/**
	 * @param typecode
	 */
	public DefaultProductSearchDAO(final String typecode)
	{
		super(typecode);
	}

	/**
    *
    */
	@Override
	public List<ProductModel> findProductFromStadiums(final String code)
	{

		List<ProductModel> resultList = null;
		try
		{
			searchRestrictionService.disableSearchRestrictions();
			final String queryProductByCode = //
			"SELECT {p:" + ProductModel.PK + "} "//
					+ "FROM {" + ProductModel._TYPECODE + " AS p} " + "WHERE " + "{p:" + ProductModel.CODE + "}=?code";

			//final select {p:pk}final from {final Product as p} final WHERE {p:code}=107701

			final FlexibleSearchQuery query = new FlexibleSearchQuery(queryProductByCode);
			query.addQueryParameter("code", code);
			resultList = flexibleSearchService.<ProductModel> search(query).getResult();
			//wrapperLogic();
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			searchRestrictionService.enableSearchRestrictions();
		}
		return resultList;

	}

	@Override
	public List<ProductModel> findAllProudcts()
	{

		List<ProductModel> resultList = null;
		try
		{
			//final int maxResultCount = limit == null ? DEFAULT_LIMIT : limit.intValue();
			searchRestrictionService.disableSearchRestrictions();
			final String queryString = //
			"SELECT {p:" + ProductModel.PK + "} "//
					+ "FROM {" + ProductModel._TYPECODE + " AS p} ";

			final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
			//query.setStart(start);
			//query.setCount(DEFAULT_LIMIT);

			// Return the list of StadiumModels.
			resultList = flexibleSearchService.<ProductModel> search(query).getResult();


		}
		catch (final Exception e)
		{
			// YTODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			searchRestrictionService.enableSearchRestrictions();
		}
		return resultList;
	}


	private String getAllProduct(final String approvalStatus)
	{
		final String QUERY_APPOVAL_STATUS = "SELECT {p:" + ProductModel.PK + "}" + "FROM {" + ProductModel._TYPECODE + " AS p} "
				+ "WHERE " + "{p:" + ProductModel.APPROVALSTATUS + "}=?approvalStatus ";

		final StringBuilder builder = new StringBuilder(QUERY_APPOVAL_STATUS);
		//query.addQueryParameter("approvalStatus", code);

		return builder.toString();
	}

	private SearchResult<?> getSearchResult(final String query)
	{
		final SearchResult<ProductModel> result = getFlexibleSearchService().search(query);
		return result;
	}

	private void wrapperLogic()
	{
		final ProductModel product = modelService.create(ProductModel.class);
		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("electronicsProductCatalog",
				"Online");
		product.setCatalogVersion(catalogVersionModel);
		product.setManufacturerName("Sony");
		modelService.save(product);
	}

}
