/**
 *
 */
package com.testritegroup.ec.hola.facades.category;


import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.List;


/**
 * @author luca.gennari
 *
 */
public interface BrandStadiumFacade
{
	ProductData getProdcutDataFromStadium(String name, String format);

	List<ProductData> getStadiums(String code);
}
