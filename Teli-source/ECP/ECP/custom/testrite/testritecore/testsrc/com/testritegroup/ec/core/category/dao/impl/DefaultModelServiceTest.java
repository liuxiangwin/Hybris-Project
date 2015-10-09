/**
 *
 */
package com.testritegroup.ec.core.category.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.testritegroup.ec.core.category.dao.ProductSearchDAO;


/**
 * @author Alan Liu
 *
 */
public class DefaultModelServiceTest extends ServicelayerTransactionalTest
{
	/** As this is an integration test the test to class gets injected here. */
	@Resource
	private ProductSearchDAO productSearchDAO;

	/** ModelService used for creation of test data. */
	@Resource
	private ModelService modelService;

	@Resource
	private CatalogVersionService catalogVersionService;

	private static final String code_NAME = "Test--ModelService";

	private static final String manaufacture_NAME = "Alan-Liu-Manaufacture";



	@Test
	public void productSearchDAOTestByCode()
	{
		List<ProductModel> allProducts = productSearchDAO.findAllProudcts();
		final int size = allProducts.size();

		assertTrue("There should ProductModel found", size > 0);

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("electronicsProductCatalog",
				"Online");

		final ProductModel productModel = new ProductModel();
		productModel.setCode(code_NAME);
		productModel.setManufacturerName(manaufacture_NAME);
		productModel.setCatalogVersion(catalogVersionModel);
		productModel.setAllowOnlineSell(true);
		modelService.save(productModel);


		allProducts = productSearchDAO.findAllProudcts();
		assertEquals(size + 1, allProducts.size());
		assertEquals("Unexpected Product found", productModel, allProducts.get(allProducts.size() - 1));


		/*
		 * final List<ProductModel> productsByCode = productSearchDAO.findProductFromStadiums(code_NAME);
		 * assertEquals("Find the one we just saved", 1, productsByCode.size()); assertEquals("Check the code names",
		 * code_NAME, productsByCode.get(0).getCode()); assertEquals("Check the manaufacture_name", manaufacture_NAME,
		 * productsByCode.get(0).getManufacturerName());
		 */
	}


}
