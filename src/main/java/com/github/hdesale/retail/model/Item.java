package com.github.hdesale.retail.model;

import com.github.hdesale.retail.pricing.item.ItemPriceCalculator;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Item class which represents an item that could be added to a shopping basket and purchased.
 * <p>
 * E.g. - Apple, Banana etc.
 * <p>
 * This is thread-safe class.
 *
 * @author Hemant
 */
public class Item {

    private final String name;

    private final ItemPriceCalculator priceCalculator;

    public Item(String name, ItemPriceCalculator priceCalculator) {
        requireNonNull(name, "Item name can not be null");
        requireNonNull(priceCalculator, "Item price calculator can not be null");
        this.name = name;
        this.priceCalculator = priceCalculator;
    }

    public String getName() {
        return name;
    }

    public BigDecimal calculateTotalPrice(int quantity) {
        return quantity > 0 ? priceCalculator.calculateTotalPrice(quantity) : BigDecimal.ZERO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                '}';
    }
}
