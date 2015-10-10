/**
 *
 */
package com.testritegroup.ec.core.category.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.testritegroup.ec.core.category.dao.ProductSearchDAO;


/**
 * @author Alan Liu ModelService Test Basic Saving Logic
 *
 */
public class DefaultModelServiceTest extends ServicelayerTest
//extends ServicelayerTransactionalTest
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
	public void testModelService_Save()
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

	}


	@Test
	public void testModelService_SaveAll()
	{
		List<ProductModel> allProducts = productSearchDAO.findAllProudcts();
		final int size = allProducts.size();

		assertTrue("There should ProductModel found", size > 0);

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("electronicsProductCatalog",
				"Online");


		final ProductModel productModel = modelService.create(ProductModel.class);
		productModel.setCode(code_NAME);
		productModel.setManufacturerName(manaufacture_NAME);
		productModel.setCatalogVersion(catalogVersionModel);
		productModel.setAllowOnlineSell(true);
		modelService.saveAll();


		allProducts = productSearchDAO.findAllProudcts();
		assertEquals(size + 1, allProducts.size());
		assertEquals("Unexpected Product found", productModel, allProducts.get(allProducts.size() - 1));

	}

}
