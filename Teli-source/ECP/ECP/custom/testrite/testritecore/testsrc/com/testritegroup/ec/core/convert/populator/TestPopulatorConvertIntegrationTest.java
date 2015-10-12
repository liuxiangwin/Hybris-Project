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
package com.testritegroup.ec.core.convert.populator;

import static org.junit.Assert.assertEquals;

import de.hybris.platform.chinaaccelerator.facades.data.CityData;
import de.hybris.platform.chinaaccelerator.services.enums.InvoiceCategory;
import de.hybris.platform.chinaaccelerator.services.model.invoice.InvoiceModel;
import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;


public class TestPopulatorConvertIntegrationTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(TestPopulatorConvertIntegrationTest.class);

	@Resource
	private CartService cartService;
	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Resource
	private ModelService modelService;


	//@Ignore
	@Test
	public void testCartPopulates()
	{
		final CartModel source = cartService.getSessionCart();
		final CartData target = new CartData();

		final InvoiceModel invoice = new InvoiceModel();
		invoice.setCategory(InvoiceCategory.FOOD);
		source.setInvoice(invoice);


		////cartPopulator.populate(source, target);

		assertEquals(target.getInvoice().getInvoicedCategory(), InvoiceCategory.FOOD.getCode());
	}


	//@Test
	public void testCityPopulates()
	{

		final CityModel cityModel = modelService.create(CityModel.class); //new CityModel();
		cityModel.setActive(Boolean.TRUE);
		cityModel.setCode("TEST-CITY-CODE");
		cityModel.setName("TEST-CITY-NAME"); // if just new CityModel(), then [mjava.lang.IllegalStateException: there is no LocaleProvider for (detached) model de.hybris.platform.servicelayer.model.ItemModelContextImpl@6b6955aa

		final RegionModel regionModel = modelService.create(RegionModel.class);
		regionModel.setActive(Boolean.TRUE);
		//regionModel.setName("TEST-REGION-NAME", Locale.ENGLISH);
		regionModel.setName("TEST-REGION-NAME");
		regionModel.setIsocode("TEST-REGION-CODE");
		final CountryModel countryModel = modelService.create(CountryModel.class);
		countryModel.setActive(Boolean.TRUE);
		countryModel.setName("TEST-COUNTRY-NAME-EN");
		countryModel.setName("TEST-COUNTRY-NAME-ZH");
		countryModel.setIsocode("TEST-COUNTRY-CODE");
		modelService.save(countryModel);
		regionModel.setCountry(countryModel);
		modelService.save(regionModel);
		cityModel.setRegion(regionModel);
		modelService.save(cityModel);

		final CityData target = new CityData();
		//cityPopulator.populate(cityModel, target);
		assertEquals(target.getName(), cityModel.getName());

	}


}