package com.github.hdesale.retail.pricing.item;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

/**
 * Discounted item price calculator which applies the '3 for 2' discount
 * and calculates a total price for a supplied quantity of items.
 * <p>
 * This is thread-safe class.
 *
 * @author Hemant
 */
public class ThreeForTwoItemPriceCalculator extends DiscountedItemPriceCalculator {

    public ThreeForTwoItemPriceCalculator(ItemPriceCalculator calculator) {
        super(calculator);
    }

    @Override
    protected BigDecimal calculateDiscount(BigDecimal pricePerUnit, long quantity) {
        requireNonNull(pricePerUnit, "Item price per unit can not be null");
        return quantity > 0 ? super.calculateOriginalPrice(pricePerUnit, quantity / 3) : BigDecimal.ZERO;
    }
}
