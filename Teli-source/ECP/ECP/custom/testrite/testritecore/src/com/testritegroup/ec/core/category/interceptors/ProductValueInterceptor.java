/**
 *
 */
package com.testritegroup.ec.core.category.interceptors;

import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Alan Liu
 *
 */
public class ProductValueInterceptor implements ValidateInterceptor, PrepareInterceptor
{

	private static final String MANUFACTURE_NAME = "Sony";

	@Autowired
	private EventService eventService;

	@Autowired
	private CatalogVersionService catalogVersionService;

	/**
	 * Trigger event service in the PrepareInterceptor stage
	 */
	@Override
	public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
	{

		if (model instanceof ProductModel)
		{
			final ProductModel productModel = (ProductModel) model;
			if (checkManufactureName(productModel, ctx))
			{
				//eventService.publishEvent(new ManaufactureEvent(productModel.getCode(), productModel.getManufacturerName()));
			}
		}

		if (model instanceof OrderModel)
		{
			//final OrderModel orderModel = (OrderModel) model;

			//eventService.publishEvent(new ManaufactureEvent(orderModel.getCode(), orderModel.getManufacturerName()));
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.ValidateInterceptor#onValidate(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onValidate(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model instanceof ProductModel)
		{
			final ProductModel productModel = (ProductModel) model;
			final Boolean allowOnlineSell = productModel.getAllowOnlineSell();
			if (allowOnlineSell != null && !allowOnlineSell.booleanValue())
			{
				throw new InterceptorException("Product is not allowed online sell");
			}
		}

	}

	private boolean checkManufactureName(final ProductModel productModel, final InterceptorContext ctx)
	{
		final String manufacturerName = productModel.getManufacturerName();
		if (manufacturerName != null && manufacturerName != "Sony")
		{
			if (ctx.isNew(productModel))
			{
				return true;
			}
			else
			{
				final String oldValue = getItemModelContext(productModel).getOriginalValue(productModel.getManufacturerName());
				if (oldValue == null || oldValue == "Sony")
				{
					return true;
				}
			}
		}
		return false;
	}
}
