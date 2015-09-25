/**
 *
 */
package com.testritegroup.ec.core.category.dao;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * @author Alan Liu
 *
 */
public interface ProductSearchDAO
{
	/**
	 * Return a list of stadium models that are currently persisted. If none are found an empty list is returned.
	 *
	 * @return all Stadiums of system
	 */
	List<ProductModel> findProductFromStadiums(String code);

	/**
	 * Finds all stadiums with given code. If none is found, an empty list will be returned.
	 *
	 * @param code
	 *           the code to search for stadiums
	 * @return All stadiums with the given code.
	 */
	List<ProductModel> findStadiumsByCode(String code);
}
