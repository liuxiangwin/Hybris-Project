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


import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.testritegroup.ec.core.category.EanData;
import com.testritegroup.ec.core.model.EanAlanModel;



public class EanPopulator<SOURCE extends EanAlanModel, TARGET extends EanData> implements Populator<EanAlanModel, EanData>
{


	@Override
	public void populate(final EanAlanModel source, final EanData target) throws ConversionException
	{
		if (source != null)
		{
			final String eanDesc = source.getEanDesc() == null ? null : source.getEanDesc();
			final String modify = eanDesc + "EanPopulator modify";
			target.setDescripton(modify);
			target.setDescripton("### EanPopulator logic###");
		}
	}
}
