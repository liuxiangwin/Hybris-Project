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
package com.testritegroup.ec.core.solr.query;

import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.SolrSynonymService;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.indexer.exceptions.IndexUpdateException;
import de.hybris.platform.solrfacetsearch.integration.AbstractSolrTest;
import de.hybris.platform.solrfacetsearch.search.FacetSearchException;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.solr.SolrConfigurationService;
import de.hybris.platform.solrfacetsearch.solr.impl.SolrServer;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Before;
import org.junit.Test;


@IntegrationTest
public class SolrQueryTest extends AbstractSolrTest
{
	private final String catalogVersion = "electronicsProductCatalog";
	@Resource
	//(name = "solrConfigurationService")
	private SolrConfigurationService solrConfigurationService;

	@Resource
	//(name = "solrSynonymService")
	private SolrSynonymService solrSynonymService;

	private SolrServer solrServer;

	@Resource
	private CommonI18NService commonI18NService;

	private FacetSearchConfig config;

	private CatalogVersionModel catalogVersionModel;

	@Override
	@Before
	public void setUp() throws Exception
	{
		/*
		 * createCoreData(); createDefaultCatalog(); importCsv(SOLR_CONFIG_DATA_IMPEX, "utf-8");
		 */
		super.setUp();
		solrServer = getSolrService().getSolrServerMaster(solrConfig, indexedType);
		//commonI18NService.setCurrentLanguage(commonI18NService.getLangua4ge("de"));
		//config = facetSearchConfigService.getConfiguration("ConfigWithExportIds");
		indexedType = config.getIndexConfig().getIndexedTypes().get("Product");
	}

	@Test
	public void testUpdateSynonyms() throws SolrServerException, IOException, FacetConfigServiceException, FacetSearchException,
			IndexUpdateException
	{
		final SearchQuery query = new SearchQuery(facetSearchConfig, indexedType);

		catalogVersionModel = catalogVersionService.getCatalogVersion("electronicsProductCatalog", "Online");

		//final PK expectedPK = productService.getProductForCode(hwOnline, "HW2300-2356").getPk();
		query.setCatalogVersion(catalogVersionModel);
		query.searchInField("name", "cameras");

		query.setLanguage("en");
		query.setCurrency("eur");
		query.addSolrParams("defType", "edismax");
		query.addSolrParams("fl", "id,name_text_en");

		final SolrDocumentList result = solrServer.query(converter.convertSolrQuery(query)).getResults();
		assertTrue("Result set should not be empty", result.size() > 0);
	}

}
