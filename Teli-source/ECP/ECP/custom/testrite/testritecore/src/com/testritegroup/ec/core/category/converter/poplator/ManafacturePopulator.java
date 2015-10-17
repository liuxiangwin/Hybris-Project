package com.testritegroup.ec.core.category.converter.poplator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.Locale;

import com.testritegroup.ec.core.category.ManafactureData;
import com.testritegroup.ec.core.model.ManafactureModel;


/**
 * Alan Liu
 *
 * @param
 * @param
 */
public class ManafacturePopulator<SOURCE extends ManafactureModel, TARGET extends ManafactureData> implements
		Populator<ManafactureModel, ManafactureData>
//extends ProductPopulator
{


	@Override
	public void populate(final ManafactureModel source, final ManafactureData target) throws ConversionException
	{
		if (source != null)
		{

			final String manafactureName = source.getManafactureName(Locale.ENGLISH)
			//source.getManafactureName()
			== null ? null : source.getManafactureName(Locale.ENGLISH);
			final String modify = manafactureName + " Alan accuquired this company ";

			target.setName(modify);
			target.setDescripton("### ManafacturePopulator logic###");
		}
	}



}
