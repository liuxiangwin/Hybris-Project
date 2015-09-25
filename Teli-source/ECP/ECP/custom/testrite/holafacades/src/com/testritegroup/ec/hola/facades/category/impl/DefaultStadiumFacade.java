/**
 *
 */
package com.testritegroup.ec.hola.facades.category.impl;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.testritegroup.ec.core.category.ProductSearchService;
import com.testritegroup.ec.hola.facades.category.BrandStadiumFacade;


@Component(value = "brandStadiumFacade")
public class DefaultStadiumFacade implements BrandStadiumFacade
{

	private ProductSearchService productSearchService;


	@Override
	public ProductData getProdcutDataFromStadium(final String name, final String format)
	{
		return null;
	}


	@Override
	public List<ProductData> getStadiums(final String code)
	{
		final List<ProductModel> productModels = productSearchService.getProductFromStadiums(code);
		final List<ProductData> stadiumFacadeData = new ArrayList<ProductData>();
		for (final ProductModel productModel : productModels)
		{

			final ProductData productData = new ProductData();
			productData.setName(productModel.getCode());

			if (productModel.getManufacturerAID() != null)
			{
				productData.setManufacturer(productModel.getManufacturerAID());
			}

			productData.setDescription("Catelog-version is :" + productModel.getCatalogVersion().toString());
			stadiumFacadeData.add(productData);
		}
		return stadiumFacadeData;
	}

	//
	/**
	 * @param productSearchService
	 *           the productSearchService to set
	 */
	@Required
	public void setProductSearchService(final ProductSearchService productSearchService)
	{
		this.productSearchService = productSearchService;
	}
}