/**
 *
 */
package com.testritegroup.ec.core.category.events;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Alan Liu
 *
 */
public class ManaufactureEventListener extends AbstractEventListener<AbstractEvent>

{
	private static final Logger LOG = Logger.getLogger(ManaufactureEventListener.class);

	@Autowired
	private ModelService modelService;

	@Autowired
	private CatalogVersionService catalogVersionService;

	/**
	 * Declare Event Listener lister which kind of Event
	 */
	@Override
	public void onEvent(final AbstractEvent event)
	{
		if (event instanceof ManaufactureEvent)
		{
			LOG.debug("### ManaufactureEvent Entering event handler ###");

			final ManaufactureEvent manaufactureEvent = (ManaufactureEvent) event;


			final OrderModel order = modelService.create(OrderModel.class);
			final String content = "Manaufacture Name:" + manaufactureEvent.getManaufactureName();

			//final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("electronicsProductCatalog",
			//		"Online");
			order.setCode(manaufactureEvent.getCode());
			order.setStatusInfo(content);
			order.setCurrency(new CurrencyModel());
			order.setDate(new Date(2018, 9, 28));
			order.setUser(new UserModel());
			modelService.save(order);


		}
	}
}
