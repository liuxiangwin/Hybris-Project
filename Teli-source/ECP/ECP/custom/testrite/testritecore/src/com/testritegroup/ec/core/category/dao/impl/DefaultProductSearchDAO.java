/**
 *
 */
package com.testritegroup.ec.core.category.dao.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
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

	private static final String QUERY_ALL_PRODCUCT = "SELECT {p:PK} " + "FROM {Product AS p} ";

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
	public List<ProductModel> findStadiumsByCode(final String code)
	{
		return null;
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

}
