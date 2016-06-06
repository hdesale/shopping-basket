package com.github.hdesale.retail.pricing.basket;

import com.github.hdesale.retail.model.Item;

import java.math.BigDecimal;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Basic basket price calculator which calculates the total price of basket
 * by simply summing up the total price per item.
 *
 * @author Hemant
 */
public class BasicBasketPriceCalculator implements BasketPriceCalculator {

    private final Map<Item, Integer> items;

    public BasicBasketPriceCalculator(Map<Item, Integer> items) {
        requireNonNull(items, "Items can not be null");
        this.items = items;
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return items.entrySet()
                .stream()
                .map(entry -> entry.getKey().calculateTotalPrice(entry.getValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
