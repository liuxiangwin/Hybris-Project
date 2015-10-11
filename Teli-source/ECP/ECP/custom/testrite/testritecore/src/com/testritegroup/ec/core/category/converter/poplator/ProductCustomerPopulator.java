/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.testritegroup.ec.core.category.converter.poplator;

import de.hybris.platform.commercefacades.product.converters.populator.ProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.testritegroup.ec.core.category.EanData;
import com.testritegroup.ec.core.category.ManafactureData;
import com.testritegroup.ec.core.model.EanModel;
import com.testritegroup.ec.core.model.ManafactureModel;


/**
 * Accelerator specific variant option data converter implementation.
 */
public class ProductCustomerPopulator extends ProductPopulator
//extends ProductPopulator
{

	private Converter<ManafactureModel, ManafactureData> manafactureConverter;
	private Converter<EanModel, EanData> paymentModeConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commercefacades.product.converters.populator.ProductPopulator#populate(de.hybris.platform.core
	 * .model.product.ProductModel, de.hybris.platform.commercefacades.product.data.ProductData)
	 */
	@Override
	public void populate(final ProductModel source, final ProductData target)
	{
		// YTODO Auto-generated method stub
		super.populate(source, target);
	}






}
