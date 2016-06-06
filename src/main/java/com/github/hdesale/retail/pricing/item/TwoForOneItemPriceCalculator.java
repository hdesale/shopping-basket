package com.github.hdesale.retail.pricing.item;

import java.math.BigDecimal;

/**
 * Discounted item price calculator which applies the '2 for 1' discount
 * and calculates a total price for a supplied quantity of items.
 * <p>
 * This is thread-safe class.
 *
 * @author Hemant
 */
public class TwoForOneItemPriceCalculator extends DiscountedItemPriceCalculator {

    public TwoForOneItemPriceCalculator(ItemPriceCalculator calculator) {
        super(calculator);
    }

    @Override
    public BigDecimal calculateTotalPrice(int quantity) {
        return super.calculateTotalPrice(quantity);
    }

    @Override
    protected BigDecimal calculateDiscount(int quantity) {
        return super.calculateOriginalPrice(quantity / 2);
    }
}