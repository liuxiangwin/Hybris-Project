/**
 *
 */
package com.testritegroup.ec.core.category;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * @author Alan Liu
 *
 */
public interface ProductSearchService
{
	/**
	 * Gets all stadiums of the system.
	 *
	 * @return all stadiums of system
	 */
	List<ProductModel> getProductFromStadiums(String code);

	/**
	 * Gets the stadium for given code.
	 *
	 * @throws de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException
	 *            in case more then one stadium for given code is found
	 * @throws de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException
	 *            in case no stadium for given code can be found
	 */
	ProductModel getStadiumForCode(String code);

	/**
	 * Gets the url for an image with the given format
	 *
	 * @param format
	 *           format to be taken to identify the image
	 * @throws de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException
	 *            in case no format can be found
	 * @throws de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException
	 *            in case more than one format is found
	 * @throws IllegalArgumentException
	 *            if given <code>format</code> is null
	 */
	String getImageUrlFromStadium(ProductModel stadium, String format);
}
