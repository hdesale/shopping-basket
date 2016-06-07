package com.github.hdesale.retail.pricing.item;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

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
    protected BigDecimal calculateDiscount(BigDecimal pricePerUnit, long quantity) {
        requireNonNull(pricePerUnit, "Item price per unit can not be null");
        return quantity > 0 ? super.calculateOriginalPrice(pricePerUnit, quantity / 2) : BigDecimal.ZERO;
    }
}
