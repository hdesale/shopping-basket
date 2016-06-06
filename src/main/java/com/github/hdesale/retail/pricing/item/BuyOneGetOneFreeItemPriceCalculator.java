package com.github.hdesale.retail.pricing.item;

import java.math.BigDecimal;

/**
 * Discounted item price calculator which applies the 'Buy 1 Get 1 Free' discount
 * and calculates a total price for a supplied quantity of items.
 * <p>
 * Note: If the item quantity is odd then the last item will be priced at full price.
 * <p>
 * This is thread-safe class.
 *
 * @author Hemant
 */
public class BuyOneGetOneFreeItemPriceCalculator extends DiscountedItemPriceCalculator {

    public BuyOneGetOneFreeItemPriceCalculator(ItemPriceCalculator calculator) {
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
