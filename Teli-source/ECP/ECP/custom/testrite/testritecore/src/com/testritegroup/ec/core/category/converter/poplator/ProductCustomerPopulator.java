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

import de.hybris.platform.chinaaccelerator.facades.data.PaymentModeData;
import de.hybris.platform.chinaaccelerator.services.model.invoice.InvoiceModel;
import de.hybris.platform.commercefacades.order.data.InvoiceData;
import de.hybris.platform.commercefacades.product.converters.populator.ProductPopulator;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;


/**
 * Accelerator specific variant option data converter implementation.
 */
public class ProductCustomerPopulator extends ProductPopulator
//extends ProductPopulator
{

	private Converter<InvoiceModel, InvoiceData> manafactureConverter;
	private Converter<PaymentModeModel, PaymentModeData> paymentModeConverter;






}
