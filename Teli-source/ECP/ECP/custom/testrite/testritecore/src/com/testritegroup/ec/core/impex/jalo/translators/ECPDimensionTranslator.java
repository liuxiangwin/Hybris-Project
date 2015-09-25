/**
 * [y] hybris Platform
 * <p>
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 * <p>
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 **/

package com.testritegroup.ec.core.impex.jalo.translators;

import de.hybris.platform.impex.jalo.translators.SingleValueTranslator;
import de.hybris.platform.jalo.Item;

public class ECPDimensionTranslator extends SingleValueTranslator
{
   /**
    * Converts a non-null and non-empty string into a jalo attribute value.
    *
    * @param expr
    *       string to translate
    * @param forItem
    *       item instance the translated value will be set to as attribute
    * @return Object translated value
    */
   @Override
   protected Object convertToJalo(String expr, Item forItem)
   {

      return null;
   }

   /**
    * Converts a non-null jalo attribute value into its string representation.
    *
    * @param value
    *       value to translate
    * @return translated value
    */
   @Override
   protected String convertToString(Object value)
   {
      return null;
   }
}
