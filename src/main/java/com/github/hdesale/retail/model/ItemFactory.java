package com.github.hdesale.retail.model;

import com.github.hdesale.retail.pricing.item.BasicItemPriceCalculator;
import com.github.hdesale.retail.pricing.item.ItemPriceCalculator;
import com.github.hdesale.retail.pricing.item.ThreeForTwoItemPriceCalculator;
import com.github.hdesale.retail.pricing.item.BuyOneGetOneFreeItemPriceCalculator;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Factory class to create instances of {@link Item}. Items are identified by their names.
 * <p>
 * Currently this factory class is designed to initialize lazily and while initializing it
 * loads some predefined types of items in memory. This loading part could be enhanced to
 * load the data from other places like database, web-service, file etc.
 *
 * @author Hemant
 */
public class ItemFactory {

    private static volatile ItemFactory factory;

    private final Map<String, Item> itemsByName;

    private ItemFactory(Map<String, Item> items) {
        this.itemsByName = Collections.unmodifiableMap(items);
    }

    public static ItemFactory getInstance() {
        if (factory == null) {
            synchronized (ItemFactory.class) {
                if (factory == null) {
                    factory = new ItemFactory(loadItems());
                }
            }
        }
        return factory;
    }

    public Optional<Item> getItem(String itemName) {
        return Optional.ofNullable(itemsByName.get(itemName.trim().toUpperCase()));
    }

    private static Map<String, Item> loadItems() {
        // Currently this is just an in-memory map of items filled with static data.
        // If required this could be enhanced to pull the data from some other places
        // like database, web-service or file etc.
        Item apple = new Item("APPLE", getBasicPriceCalculator("35"));
        Item banana = new Item("BANANA", getBasicPriceCalculator("20"));
        Item melon = new Item("MELON", getBuyOneGetOneFreePriceCalculator(getBasicPriceCalculator("50")));
        Item lime = new Item("LIME", getThreeForTwoPriceCalculator(getBasicPriceCalculator("15")));

        Map<String, Item> items = new HashMap<>();
        items.put("APPLE", apple);
        items.put("BANANA", banana);
        items.put("MELON", melon);
        items.put("LIME", lime);
        return items;
    }

    private static BasicItemPriceCalculator getBasicPriceCalculator(String price) {
        return new BasicItemPriceCalculator(new BigDecimal(price));
    }

    private static BuyOneGetOneFreeItemPriceCalculator getBuyOneGetOneFreePriceCalculator(ItemPriceCalculator calculator) {
        return new BuyOneGetOneFreeItemPriceCalculator(calculator);
    }

    private static ThreeForTwoItemPriceCalculator getThreeForTwoPriceCalculator(ItemPriceCalculator calculator) {
        return new ThreeForTwoItemPriceCalculator(calculator);
    }
}
