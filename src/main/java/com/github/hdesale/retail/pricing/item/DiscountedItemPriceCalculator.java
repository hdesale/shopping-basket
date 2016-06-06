package com.github.hdesale.retail.pricing.item;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

/**
 * Discounted item price calculator which applies the discount to original price
 * and calculates a total discounted price for a supplied quantity of items.
 * <p>
 * This abstract class acts a decorator.
 *
 * @author Hemant
 * @see ThreeForTwoItemPriceCalculator
 * @see BuyOneGetOneFreeItemPriceCalculator
 */
public abstract class DiscountedItemPriceCalculator implements ItemPriceCalculator {

    private final ItemPriceCalculator originalCalculator;

    public DiscountedItemPriceCalculator(ItemPriceCalculator calculator) {
        requireNonNull(calculator, "Item price calculator can not be null");
        this.originalCalculator = calculator;
    }

    @Override
    public BigDecimal calculateTotalPrice(int quantity) {
        BigDecimal originalPrice = calculateOriginalPrice(quantity);
        BigDecimal discountedPrice = calculateDiscount(quantity);
        return originalPrice.subtract(discountedPrice);
    }

    protected BigDecimal calculateOriginalPrice(int quantity) {
        return originalCalculator.calculateTotalPrice(quantity);
    }

    protected abstract BigDecimal calculateDiscount(int quantity);
}
