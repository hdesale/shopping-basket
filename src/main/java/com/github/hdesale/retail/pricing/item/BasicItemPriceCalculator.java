package com.github.hdesale.retail.pricing.item;

import java.math.BigDecimal;

import static java.util.Objects.*;

/**
 * Basic item price calculator which uniformly applies the price per unit
 * and calculates a total price for a supplied quantity of items.
 * <p>
 * This is thread-safe class.
 *
 * @author Hemant
 */
public class BasicItemPriceCalculator implements ItemPriceCalculator {

    private final BigDecimal pricePerUnit;

    public BasicItemPriceCalculator(BigDecimal pricePerUnit) {
        requireNonNull(pricePerUnit, "Item price can not be null");
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public BigDecimal calculateTotalPrice(int quantity) {
        return pricePerUnit.multiply(BigDecimal.valueOf(quantity));
    }
}
