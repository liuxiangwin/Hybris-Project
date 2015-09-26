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
package com.testritegroup.ec.hola.storefront.controllers.pages;


import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractCategoryPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.testritegroup.ec.hola.facades.category.BrandStadiumFacade;
import com.testritegroup.ec.hola.facades.category.ECPBrandFacade;
import com.testritegroup.ec.hola.facades.category.data.ECPBrandData;


/**
 * @author   Alan Liu
 * @function BrandCategoryPageController for a brand category page
 */
/**
 *
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/**/brand")
public class BrandCategoryPageController extends AbstractCategoryPageController
{
	protected static final Logger LOG = Logger.getLogger(BrandCategoryPageController.class);

	// CMS Pages
	private static final String BRAND_CATEGORY_CMS_PAGE = "BrandCategoryPage";

	@Resource(name = "brandFacade")
	private ECPBrandFacade ecpBrandFacade;

	@Resource(name = "brandStadiumFacade")
	private BrandStadiumFacade stadiumFacade;

	//@RequestMapping(value = CATEGORY_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequestMapping(value = "/{categoryCode}/{code}", method = RequestMethod.GET)
	//"/{categoryCode:.*}"
	public String category(@PathVariable("categoryCode") final String categoryCode, @PathVariable("code") final String code,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode, final Model model,
			final HttpServletRequest request, final HttpServletResponse response) throws UnsupportedEncodingException,
			CMSItemNotFoundException
	{
		getStadiumData(code);
		getMockData(model);
		storeCmsPageInModel(model, getContentPageForLabelOrId(BRAND_CATEGORY_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(BRAND_CATEGORY_CMS_PAGE));
		return getViewForPage(model);

	}

	protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(cmsPage.getTitle()));
	}


	private void getMockData(final Model model)
	{
		//https://item.taobao.com/item.htm?spm=a230r.1.14.98.LiLPIn&id=43602241746&ns=1&abbucket=10#detail
		final ECPBrandData test1 = new ECPBrandData();
		test1.setCode("Samsung");
		test1.setName("Samsung Mobile");
		test1.setStory("Samsung S6 Story");

		final ECPBrandData test2 = new ECPBrandData();
		test2.setCode("Apple");
		test2.setName("Apple Mobile");
		test2.setStory("Apple 6s");

		final ECPBrandData test3 = new ECPBrandData();
		test3.setCode("HTC");
		test3.setName("HTC Mobile");
		test3.setStory("HTC Story");

		final List<ECPBrandData> ecpBrandList = new ArrayList<ECPBrandData>();
		ecpBrandList.add(test1);
		ecpBrandList.add(test2);
		ecpBrandList.add(test3);

		model.addAttribute("ecpList", ecpBrandList);

	}

	/**
	 * Add some parameter
	 * 
	 * @param code
	 */
	private void getStadiumData(final String code)
	{
		stadiumFacade.getStadiums(code);
	}
}