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
import com.testritegroup.ec.core.model.EanAlanModel;
import com.testritegroup.ec.core.model.ManafactureModel;



public class ProductFeaturePopulator extends ProductPopulator
//extends ProductPopulator
{

	private Converter<ManafactureModel, ManafactureData> manafactureConverter;

	private Converter<EanAlanModel, EanData> eanModelConverter;

	@Override
	public void populate(final ProductModel source, final ProductData target)
	{
		//super.populate(source, target);

		if (source.getManafacture() != null)
		{
			final ManafactureData manafactureData = manafactureConverter.convert(source.getManafacture());
			target.setManufacturer(manafactureData.getName());
		}
		else
		{
			target.setManufacturer(null);
		}

		if (source.getEanAlan() != null)
		{
			final EanData eanData = eanModelConverter.convert(source.getEanAlan());
			target.setSummary(eanData.getDescripton());
		}
		else
		{
			target.setSummary(null);
		}

	}

	public Converter<ManafactureModel, ManafactureData> getManafactureConverter()
	{
		return manafactureConverter;
	}


	public void setManafactureConverter(final Converter<ManafactureModel, ManafactureData> manafactureConverter)
	{
		this.manafactureConverter = manafactureConverter;
	}


	public Converter<EanAlanModel, EanData> getEanModelConverter()
	{
		return eanModelConverter;
	}

	public void setEanModelConverter(final Converter<EanAlanModel, EanData> eanModelConverter)
	{
		this.eanModelConverter = eanModelConverter;
	}
}
