package com.github.hdesale.retail.pricing.item;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

/**
 * Basic item price calculator which uniformly applies the price per unit
 * and calculates a total price for a supplied quantity of items.
 * <p>
 * This is thread-safe class.
 *
 * @author Hemant
 */
public class BasicItemPriceCalculator implements ItemPriceCalculator {

    @Override
    public BigDecimal calculateTotalPrice(BigDecimal pricePerUnit, long quantity) {
        requireNonNull(pricePerUnit, "Item price per unit can not be null");
        return quantity > 0 ? pricePerUnit.multiply(BigDecimal.valueOf(quantity)) : BigDecimal.ZERO;
    }
}
