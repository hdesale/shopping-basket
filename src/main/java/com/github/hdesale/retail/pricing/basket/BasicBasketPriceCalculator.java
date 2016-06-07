package com.github.hdesale.retail.pricing.basket;

import com.github.hdesale.retail.model.Item;

import java.math.BigDecimal;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Basic basket price calculator which calculates the total price of basket
 * by simply summing up the total price of all quantities per item.
 * <p>
 * This is thread-safe class.
 *
 * @author Hemant
 */
public class BasicBasketPriceCalculator implements BasketPriceCalculator {

    @Override
    public BigDecimal calculateTotalPrice(Map<Item, Long> quantitiesPerItem) {
        requireNonNull(quantitiesPerItem, "Item quantities can not be null");
        return quantitiesPerItem.entrySet()
                .stream()
                .map(entry -> entry.getKey().calculateTotalPrice(entry.getValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
