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

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.chinaaccelerator.facades.data.CityData;
import de.hybris.platform.chinaaccelerator.services.model.location.CityModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.Locale;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.testritegroup.ec.core.category.converter.poplator.ProductFeaturePopulator;
import com.testritegroup.ec.core.model.EanAlanModel;
import com.testritegroup.ec.core.model.ManafactureModel;


public class TestPopulatorConvertIntegrationTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(TestPopulatorConvertIntegrationTest.class);

	private static final String code = "Test--Populator-Convert";

	private static final String manaufacture = "Sap-Manaufacture";

	private static final String ean = "Hybris-suite";

	@Resource
	private CartService cartService;
	@Resource
	private FlexibleSearchService flexibleSearchService;
	@Resource
	private ProductFeaturePopulator productFeaturePopulator;
	@Resource
	private ModelService modelService;
	@Resource
	private CatalogVersionService catalogVersionService;

	private CatalogVersionModel catalogVersionModel;
	private ManafactureModel manafactureModel;
	private EanAlanModel eanAlanModel;

	@Before
	public void setUp()
	{
		catalogVersionModel = catalogVersionService.getCatalogVersion("electronicsProductCatalog", "Online");
		//manafactureModel = modelService.create(ManafactureModel.class); //

		manafactureModel = new ManafactureModel();
		manafactureModel.setManafactureName(manaufacture, Locale.ENGLISH);
		modelService.attach(manafactureModel);

		//eanAlanModel = modelService.create(EanAlanModel.class);
		eanAlanModel = new EanAlanModel();
		eanAlanModel.setEanDesc(ean, Locale.ENGLISH);
		modelService.attach(eanAlanModel);



	}

	@Test
	public void testCartPopulates()
	{
		final ProductModel source = new ProductModel();
		source.setCode(code);
		source.setCatalogVersion(catalogVersionModel);
		source.setAllowOnlineSell(true);

		source.setManafacture(manafactureModel);
		source.setEanAlan(eanAlanModel);


		final ProductData target = new ProductData();
		productFeaturePopulator.populate(source, target);

		final String manufacture = target.getManufacturer();
		final String summary = target.getSummary();
		Assert.assertNotNull(manufacture);
		Assert.assertNotNull(summary);
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