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

import com.testritegroup.ec.core.category.ManafactureData;
import com.testritegroup.ec.core.model.ManafactureModel;


/**
 * Alan Liu ManafacturePopulator
 */
public class ManafacturePopulator<SOURCE extends ManafactureModel, TARGET extends ManafactureData> implements
		Populator<ManafactureModel, ManafactureData>
//extends ProductPopulator
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ManafactureModel source, final ManafactureData target) throws ConversionException
	{
		if (source != null)
		{
			//target.setInvoicedTitle(source.getTitle() == null ? null : source.getTitle().getCode());
			//target.setInvoicedName(source.getInvoicedName());
			//target.setInvoicedCategory(source.getCategory() == null ? null : source.getCategory().getCode());
		}
	}





}
