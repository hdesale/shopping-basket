package com.github.hdesale.retail.model;

import com.github.hdesale.retail.pricing.basket.BasicBasketPriceCalculator;
import com.github.hdesale.retail.pricing.basket.BasketPriceCalculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Basket which contains multiple items.
 * <pre>
 * To add items into basket, use -
 *      - {@link #addItem(String)}
 *      - {@link #addItems(List)}
 * To remove items from basket, use -
 *      - {@link #removeItem(String)}
 *      - {@link #removeItems(List)}
 * To view items from basket, use -
 *      - {@link #getItems()}
 * To calculate the total price of basket, use -
 *      - {@link #calculateTotalPrice()}
 * </pre>
 *
 * @author Hemant
 */
public class Basket {

    private final ConcurrentHashMap<Item, AtomicInteger> quantitiesPerItem;

    public Basket() {
        this.quantitiesPerItem = new ConcurrentHashMap<>();
    }

    public void addItem(String itemName) {
        assertItemName(itemName);
        doAddItem(getItem(itemName));
    }

    public void addItems(List<String> itemNames) {
        assertItemNames(itemNames);
        itemNames.forEach(this::addItem);
    }

    public void removeItem(String itemName) {
        assertItemName(itemName);
        doRemoveItem(getItem(itemName));
    }

    public void removeItems(List<String> itemNames) {
        assertItemNames(itemNames);
        itemNames.forEach(this::removeItem);
    }

    public Map<Item, Integer> getItems() {
        return quantitiesPerItem.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get()));
    }

    public BigDecimal calculateTotalPrice() {
        BasketPriceCalculator priceCalculator = new BasicBasketPriceCalculator(getItems());
        return priceCalculator.calculateTotalPrice();
    }

    private void assertItemName(String itemName) {
        requireNonNull(itemName, "Item name can not be null");
    }

    private void assertItemNames(List<String> itemNames) {
        requireNonNull(itemNames, "Item names list can not be null");
    }

    private Item getItem(String itemName) {
        Optional<Item> item = ItemFactory.getInstance().getItem(itemName);
        if (!item.isPresent()) {
            throw new IllegalArgumentException("Unknown item name: " + itemName);
        }
        return item.get();
    }

    private void doAddItem(Item item) {
        AtomicInteger quantity = quantitiesPerItem.computeIfAbsent(item, k -> new AtomicInteger(0));
        quantity.incrementAndGet();
    }

    private void doRemoveItem(Item item) {
        AtomicInteger quantity = quantitiesPerItem.computeIfPresent(item, this::decrementQuantity);
        if (quantity == null) {
            throw new IllegalArgumentException("Item '" + item.getName() + "' does not exist in basket");
        }
    }

    private AtomicInteger decrementQuantity(Item item, AtomicInteger quantity) {
        if (quantity.get() == 0) {
            throw new IllegalArgumentException("Item '" + item.getName() + "' does not exist in basket");
        }
        quantity.decrementAndGet();
        return quantity;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "items=" + getItems() +
                '}';
    }
}
