package com.github.hdesale.retail.pricing.item;

import java.math.BigDecimal;

/**
 * Item price calculator which calculates the total price for a supplied quantity of items.
 *
 * @author Hemant
 * @see BasicItemPriceCalculator
 * @see DiscountedItemPriceCalculator
 */
public interface ItemPriceCalculator {

    BigDecimal calculateTotalPrice(BigDecimal pricePerUnit, long quantity);
}
