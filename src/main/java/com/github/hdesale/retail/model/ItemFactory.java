package com.github.hdesale.retail.model;

import com.github.hdesale.retail.pricing.item.BasicItemPriceCalculator;
import com.github.hdesale.retail.pricing.item.BuyOneGetOneFreeItemPriceCalculator;
import com.github.hdesale.retail.pricing.item.ItemPriceCalculator;
import com.github.hdesale.retail.pricing.item.ThreeForTwoItemPriceCalculator;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory class to create instances of {@link Item}. Items are identified by their names.
 * <p>
 * Currently this factory class is designed to initialize lazily and while initializing it
 * loads some predefined types of items in memory. This loading part could be enhanced to
 * load the data from other places like database, web-service, file etc.
 *
 * @author Hemant
 */
class ItemFactory {

    private static class LazyInitializer {
        private static final ItemFactory factory = new ItemFactory();
    }

    private final Map<String, Item> itemsByName;

    private ItemFactory() {
        this.itemsByName = Collections.unmodifiableMap(loadItems());
    }

    static ItemFactory getInstance() {
        return LazyInitializer.factory;
    }

    Item getItem(String itemName) {
        Item item = itemsByName.get(itemName.trim().toUpperCase());
        if (item == null) {
            throw new IllegalArgumentException("Unknown item name: " + itemName);
        }
        return item;
    }

    private Map<String, Item> loadItems() {
        Item apple = new Item("APPLE", new BigDecimal("35"));
        Item banana = new Item("BANANA", new BigDecimal("20"));
        Item melon = new Item("MELON", new BigDecimal("50"), getBuyOneGetOneFreeCalculator(getBasicCalculator()));
        Item lime = new Item("LIME", new BigDecimal("15"), getThreeForTwoCalculator(getBasicCalculator()));

        Map<String, Item> items = new HashMap<>();
        items.put("APPLE", apple);
        items.put("BANANA", banana);
        items.put("MELON", melon);
        items.put("LIME", lime);
        return items;
    }

    private static BasicItemPriceCalculator getBasicCalculator() {
        return new BasicItemPriceCalculator();
    }

    private static BuyOneGetOneFreeItemPriceCalculator getBuyOneGetOneFreeCalculator(ItemPriceCalculator calculator) {
        return new BuyOneGetOneFreeItemPriceCalculator(calculator);
    }

    private static ThreeForTwoItemPriceCalculator getThreeForTwoCalculator(ItemPriceCalculator calculator) {
        return new ThreeForTwoItemPriceCalculator(calculator);
    }
}
