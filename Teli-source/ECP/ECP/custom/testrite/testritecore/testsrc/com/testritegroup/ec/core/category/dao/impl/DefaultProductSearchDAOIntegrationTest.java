/**
 *
 */
package com.testritegroup.ec.core.category.dao.impl;

import static org.junit.Assert.assertTrue;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.testritegroup.ec.core.category.dao.ProductSearchDAO;


/**
 * @author luca.gennari
 *
 */
public class DefaultProductSearchDAOIntegrationTest extends ServicelayerTransactionalTest
{
	/** As this is an integration test the test to class gets injected here. */
	@Resource
	private ProductSearchDAO productSearchDAO;

	/** ModelService used for creation of test data. */
	@Resource
	private ModelService modelService;

	/** Name of test stadium. */
	private static final String STADIUM_NAME = "Wembley";

	/** Capacity of test stadium. */
	private static final Integer STADIUM_CAPACITY = Integer.valueOf(12345);



	@Test
	public void stadiumDAOTest()
	{
		final List<ProductModel> productsByCode = productSearchDAO.findProductFromStadiums("107701");
		//assertTrue("There should be no ProductModel found", productModelList.isEmpty());

		assertTrue("There should ProductModel found", productsByCode.size() > 0);

		final List<ProductModel> allProducts = productSearchDAO.findAllProudcts();
		final int size = allProducts.size();

		assertTrue("There should ProductModel found", size > 0);

		/*
		 * final StadiumModel stadiumModel = new StadiumModel(); stadiumModel.setCode(STADIUM_NAME);
		 * stadiumModel.setCapacity(STADIUM_CAPACITY); modelService.save(stadiumModel);
		 *
		 * allProducts = stadiumDAO.findStadiums(); assertEquals(size + 1, allProducts.size());
		 * assertEquals("Unexpected stadium found", stadiumModel, allProducts.get(allProducts.size() - 1));
		 *
		 * stadiumsByCode = stadiumDAO.findStadiumsByCode(STADIUM_NAME); assertEquals("Find the one we just saved", 1,
		 * stadiumsByCode.size()); assertEquals("Check the names", STADIUM_NAME, stadiumsByCode.get(0).getCode());
		 * assertEquals("Check the capacity", STADIUM_CAPACITY, stadiumsByCode.get(0).getCapacity());
		 */

	}

	/*
	 * @Test(expected = IllegalArgumentException.class) public void stadiumDAOCornerCases() { // Calling
	 * findStadiumsByCode with the empty string returns no results List<StadiumModel> stadiums =
	 * productSearchDAO.findStadiumsByCode(""); assertTrue("No Stadium should be returned", stadiums.isEmpty());
	 * 
	 * // Calling findStadiumByCode with null throws an IllegalArgumentException stadiums =
	 * productSearchDAO.findStadiumsByCode(null);
	 * 
	 * // Create a StadiumModel and call saveStadiumModel final StadiumModel stadiumModel = new StadiumModel();
	 * stadiumModel.setCapacity(Integer.valueOf(1000)); stadiumModel.setCode(STADIUM_NAME);
	 * modelService.save(stadiumModel); }
	 */
}
