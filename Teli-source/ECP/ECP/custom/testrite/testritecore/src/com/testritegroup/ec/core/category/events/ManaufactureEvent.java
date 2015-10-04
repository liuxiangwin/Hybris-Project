/**
 *
 */
package com.testritegroup.ec.core.category.events;

import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;


/**
 * @author Alan Liu
 *
 */
public class ManaufactureEvent extends AbstractEvent implements ClusterAwareEvent
{
	private final String manaufactureName;

	private final String code;

	public ManaufactureEvent(final String code, final String manaufactureName)
	{
		super();
		this.code = code;
		this.manaufactureName = manaufactureName;
	}

	/**
	 * @return the manaufactureName
	 */
	public String getManaufactureName()
	{
		return manaufactureName;
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @return the manaufactureName
	 */
	@Override
	public String toString()
	{

		return this.manaufactureName;
	}

	@Override
	public boolean publish(final int sourceNodeId, final int targetNodeId)
	{
		return (sourceNodeId == targetNodeId);
	}
}
