/**
 *
 */
package com.testritegroup.ec.core.category.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.media.MediaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.testritegroup.ec.core.category.ProductSearchService;
import com.testritegroup.ec.core.category.dao.ProductSearchDAO;


/**
 * @author Alan Liu
 *
 */
public class DefaultProductSearchService implements ProductSearchService
{

	private ProductSearchDAO productSearchDAO;

	/**
	 * @param productSearchDAO
	 *           the productSearchDAO to set
	 */
	@Required
	public void setProductSearchDAO(final ProductSearchDAO productSearchDAO)
	{
		this.productSearchDAO = productSearchDAO;
	}

	@Autowired
	private MediaService mediaService;

	@Override
	public List<ProductModel> getProductFromStadiums(final String code)
	{
		final List<ProductModel> productModelList = productSearchDAO.findProductFromStadiums(code);
		return productModelList;
	}

	@Override
	public ProductModel getStadiumForCode(final String code)
	{
		return null;
	}

	@Override
	public String getImageUrlFromStadium(final ProductModel stadium, final String format)
	{
		return null;
	}




}
