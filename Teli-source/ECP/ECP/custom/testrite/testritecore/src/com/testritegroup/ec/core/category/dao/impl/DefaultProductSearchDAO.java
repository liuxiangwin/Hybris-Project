/**
 *
 */
package com.testritegroup.ec.core.category.dao.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
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
	/**
	 * @param typecode
	 */
	public DefaultProductSearchDAO(final String typecode)
	{
		super(typecode);
	}


	//@Autowired
	//private FlexibleSearchService flexibleSearchService;

	@Autowired
	private SearchRestrictionService searchRestrictionService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.cuppytrail.daos.StadiumDAO#findStadiums()
	 */
	//@Override
	/*
	 * public List<StadiumModel> findStadiums() { // Build a query for the flexible search. final String queryString = //
	 * "SELECT {p:" + StadiumModel.PK + "} "// + "FROM {" + StadiumModel._TYPECODE + " AS p} ";
	 *
	 * final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
	 *
	 * // Note that we could specify paginating logic by providing a start and count variable (commented out below) //
	 * This can provide a safeguard against returning very large amounts of data, or hogging the database when there are
	 * // for example millions of items being returned. // As we know that there are only a few persisted stadiums in
	 * this use case we do not need to provide this.
	 *
	 * //query.setStart(start); //query.setCount(count);
	 *
	 * // Return the list of StadiumModels. return flexibleSearchService.<StadiumModel> search(query).getResult(); }
	 */


	@Override
	public List<ProductModel> findProductFromStadiums(final String code)
	{

		/*
		 * final String queryString = // "SELECT {p:" + ProductModel.PK + "} "// + "FROM {" + ProductModel._TYPECODE +
		 * " AS p} ";
		 */
		//final String queryString = "SELECT {p:" + ProductModel.PK + "}" + "FROM {" + ProductModel._TYPECODE + " AS p} " + "WHERE "
		//		+ "{p:" + ProductModel.CODE + "}=?code ";

		List<ProductModel> resultList = null;
		try
		{
			searchRestrictionService.disableSearchRestrictions();

			final String queryString = "SELECT {p:" + ProductModel.PK + "}" + "FROM {" + ProductModel._TYPECODE + " AS p} "
					+ "WHERE " + "{p:" + ProductModel.APPROVALSTATUS + "}=?approvalStatus ";

			final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
			//query.addQueryParameter("code", code);
			query.addQueryParameter("approvalStatus", code);

			final SearchResult<ProductModel> result = getFlexibleSearchService().search(query);

			resultList = result.getResult();
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

		//return flexibleSearchService.<ProductModel> search(query).getResult();
		return resultList;

	}

	@Override
	public List<ProductModel> findStadiumsByCode(final String code)
	{
		// YTODO Auto-generated method stub
		return null;
	}

}
