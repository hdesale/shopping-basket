package com.github.hdesale.retail.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * JUnit class for {@link Basket}
 *
 * @author Hemant
 */
public class BasketTest {

    private List<String> items;

    private Basket basket;

    @Before
    public void setUp() {
        items = Arrays.asList("Apple", "Banana", "Apple", "Lime", "Banana", "Melon", "Melon");
        basket = new Basket(items);
    }

    @Test
    public void testGetItems() throws Exception {
        List<Item> itemsFromBasket = basket.getItems();
        assertNotNull(itemsFromBasket);
        assertTrue(itemsFromBasket.size() == items.size());
        List<String> bNames = itemsFromBasket.stream().map(i -> i.getName().toUpperCase()).collect(Collectors.toList());
        List<String> iNames = items.stream().map(String::toUpperCase).collect(Collectors.toList());
        assertTrue(bNames.equals(iNames));
    }

    @Test
    public void testCalculateTotalPrice() throws Exception {
        assertTrue(basket.calculateTotalPrice().equals(BigDecimal.valueOf(175)));
    }

    @Test
    public void testCalculateTotalPriceWithoutItems() throws Exception {
        Basket emptyBasket = new Basket(Collections.emptyList());
        assertTrue(emptyBasket.calculateTotalPrice().equals(BigDecimal.ZERO));
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateTotalPriceWithNullItems() throws Exception {
        new Basket(null);
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateTotalPriceWithNullCalculator() throws Exception {
        new Basket(items, null);
    }
}