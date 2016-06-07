package com.github.hdesale.retail.pricing.item;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

/**
 * Discounted item price calculator which applies the discount to original price
 * and calculates a total discounted price for a supplied quantity of items.
 * <p>
 * This abstract class acts a decorator.
 * <p>
 * This is thread-safe class.
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
    public BigDecimal calculateTotalPrice(BigDecimal pricePerUnit, long quantity) {
        requireNonNull(pricePerUnit, "Item price per unit can not be null");
        BigDecimal originalPrice = calculateOriginalPrice(pricePerUnit, quantity);
        BigDecimal discountedPrice = calculateDiscount(pricePerUnit, quantity);
        return quantity > 0 ? originalPrice.subtract(discountedPrice) : BigDecimal.ZERO;
    }

    protected BigDecimal calculateOriginalPrice(BigDecimal pricePerUnit, long quantity) {
        return quantity > 0 ? originalCalculator.calculateTotalPrice(pricePerUnit, quantity) : BigDecimal.ZERO;
    }

    protected abstract BigDecimal calculateDiscount(BigDecimal pricePerUnit, long quantity);
}
