package com.github.hdesale.retail.model;

import com.github.hdesale.retail.pricing.basket.BasicBasketPriceCalculator;
import com.github.hdesale.retail.pricing.basket.BasketPriceCalculator;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Basket which contains multiple items.
 * <pre>
 * To create basket, use -
 *      - {@link #Basket(List, BasketPriceCalculator)}
 * To view items from basket, use -
 *      - {@link #getItems()}
 * To calculate the total price of basket, use -
 *      - {@link #calculateTotalPrice()}
 * </pre>
 * This is thread-safe class.
 *
 * @author Hemant
 */
public class Basket {

    private final List<Item> items;

    private final BasketPriceCalculator priceCalculator;

    public Basket(List<String> itemNames) {
        this(itemNames, new BasicBasketPriceCalculator());
    }

    public Basket(List<String> itemNames, BasketPriceCalculator priceCalculator) {
        requireNonNull(itemNames, "Item list can not be null");
        requireNonNull(priceCalculator, "Basket price calculator can not be null");
        this.items = Collections.unmodifiableList(convert(itemNames));
        this.priceCalculator = priceCalculator;
    }

    public List<Item> getItems() {
        return items;
    }

    public BigDecimal calculateTotalPrice() {
        return items.size() > 0 ? priceCalculator.calculateTotalPrice(getQuantitiesPerItem()) : BigDecimal.ZERO;
    }

    List<Item> convert(List<String> itemNames) {
        ItemFactory factory = ItemFactory.getInstance();
        return itemNames.stream().map(factory::getItem).collect(Collectors.toList());
    }

    Map<Item, Long> getQuantitiesPerItem() {
        return items.stream().collect(Collectors.groupingBy(item -> item, Collectors.counting()));
    }

    @Override
    public String toString() {
        return "Basket{" + "items=" + items + '}';
    }
}
