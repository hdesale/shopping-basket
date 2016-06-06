package com.github.hdesale.retail.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * JUnit class for {@link Basket}
 *
 * @author Hemant
 */
public class BasketTest {

    private Basket basket;

    @Before
    public void setUp() {
        basket = new Basket();
    }

    @Test
    public void testAddItem() throws Exception {
        basket.addItem("Apple");

        Map<Item, Integer> items = basket.getItems();
        assertNotNull(items);

        Optional<Item> apple = ItemFactory.getInstance().getItem("Apple");
        assertTrue(apple.isPresent());
        assertTrue(items.containsKey(apple.get()));
        assertTrue(items.get(apple.get()) == 1);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullItem() throws Exception {
        basket.addItem(null);
    }

    @Test
    public void testAddItems() throws Exception {
        basket.addItems(Arrays.asList("Apple", "Lime", "Apple"));

        Map<Item, Integer> items = basket.getItems();
        assertNotNull(items);

        Optional<Item> apple = ItemFactory.getInstance().getItem("Apple");
        assertTrue(apple.isPresent());
        assertTrue(items.containsKey(apple.get()));
        assertTrue(items.get(apple.get()) == 2);

        Optional<Item> lime = ItemFactory.getInstance().getItem("Lime");
        assertTrue(lime.isPresent());
        assertTrue(items.containsKey(lime.get()));
        assertTrue(items.get(lime.get()) == 1);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullItems() throws Exception {
        basket.addItems(null);
    }

    @Test
    public void testRemoveItem() throws Exception {
        basket.addItem("Apple");

        Map<Item, Integer> itemsBefore = basket.getItems();
        assertNotNull(itemsBefore);

        Optional<Item> apple = ItemFactory.getInstance().getItem("Apple");
        assertTrue(apple.isPresent());
        assertTrue(itemsBefore.containsKey(apple.get()));
        assertTrue(itemsBefore.get(apple.get()) == 1);

        basket.removeItem("Apple");

        Map<Item, Integer> itemsAfter = basket.getItems();
        assertNotNull(itemsAfter);

        assertTrue(itemsAfter.containsKey(apple.get()));
        assertTrue(itemsAfter.get(apple.get()) == 0);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullItem() throws Exception {
        basket.removeItem(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingItem() throws Exception {
        basket.removeItem("Melon");
    }

    @Test
    public void testRemoveItems() throws Exception {
        basket.addItems(Arrays.asList("Apple", "Lime", "Apple"));

        Map<Item, Integer> itemsBefore = basket.getItems();
        assertNotNull(itemsBefore);

        Optional<Item> apple = ItemFactory.getInstance().getItem("Apple");
        assertTrue(apple.isPresent());
        assertTrue(itemsBefore.containsKey(apple.get()));
        assertTrue(itemsBefore.get(apple.get()) == 2);

        Optional<Item> lime = ItemFactory.getInstance().getItem("Lime");
        assertTrue(lime.isPresent());
        assertTrue(itemsBefore.containsKey(lime.get()));
        assertTrue(itemsBefore.get(lime.get()) == 1);

        basket.removeItems(Arrays.asList("Apple", "Lime"));
        Map<Item, Integer> itemsAfter = basket.getItems();
        assertNotNull(itemsAfter);

        assertTrue(itemsAfter.containsKey(apple.get()));
        assertTrue(itemsAfter.get(apple.get()) == 1);
        assertTrue(itemsAfter.containsKey(lime.get()));
        assertTrue(itemsAfter.get(lime.get()) == 0);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullItems() throws Exception {
        basket.removeItems(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingItems() throws Exception {
        basket.removeItems(Arrays.asList("Banana", "Melon"));
    }

    @Test
    public void testGetItems() throws Exception {
        basket.addItems(Arrays.asList("Apple", "Lime", "Apple"));

        Map<Item, Integer> items = basket.getItems();
        assertNotNull(items);
    }

    @Test
    public void testCalculateTotalPrice() throws Exception {
        basket.addItem("Apple");
        basket.addItem("Banana");
        basket.addItem("Apple");
        basket.addItems(Arrays.asList("Lime", "Banana", "Melon", "Melon"));
        assertTrue(basket.calculateTotalPrice().equals(BigDecimal.valueOf(175)));
    }

    @Test
    public void testCalculateTotalPriceWithoutItems() throws Exception {
        assertTrue(basket.calculateTotalPrice().equals(BigDecimal.ZERO));
    }
}