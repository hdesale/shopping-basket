package com.github.hdesale.retail.model;

import com.github.hdesale.retail.pricing.item.BasicItemPriceCalculator;
import com.github.hdesale.retail.pricing.item.ItemPriceCalculator;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Item class which represents an item that could be added into a shopping basket.
 * <p>
 * E.g. - Apple, Banana, Melon, Lime etc.
 * <p>
 * Each item uses the associated {@link ItemPriceCalculator} to calculate the total
 * price of item for a supplied quantity.
 * <p>
 * Items are uniquely identified by their names.
 * <p>
 * This is thread-safe class.
 *
 * @author Hemant
 * @see ItemFactory
 */
public class Item {

    private final String name;

    private final BigDecimal pricePerUnit;

    private final ItemPriceCalculator priceCalculator;

    public Item(String name, BigDecimal pricePerUnit) {
        this(name, pricePerUnit, new BasicItemPriceCalculator());
    }

    public Item(String name, BigDecimal pricePerUnit, ItemPriceCalculator priceCalculator) {
        requireNonNull(name, "Item name can not be null");
        requireNonNull(pricePerUnit, "Item price per unit can not be null");
        requireNonNull(priceCalculator, "Item price calculator can not be null");
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.priceCalculator = priceCalculator;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public BigDecimal calculateTotalPrice(long quantity) {
        return quantity > 0 ? priceCalculator.calculateTotalPrice(pricePerUnit, quantity) : BigDecimal.ZERO;
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
        return "Item{" + "name='" + name + '\'' + '}';
    }
}
