package com.github.hdesale.retail.model;

import com.github.hdesale.retail.pricing.item.BasicItemPriceCalculator;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * JUnit class for {@link Item}
 *
 * @author Hemant
 */
public class ItemTest {

    @Test
    public void testGetName() throws Exception {
        Item item = new Item("test", BigDecimal.valueOf(100), new BasicItemPriceCalculator());
        assertNotNull(item.getName());
        assertTrue(item.getName().equals("test"));
    }

    @Test(expected = NullPointerException.class)
    public void testWithNullName() throws Exception {
        new Item(null, BigDecimal.valueOf(100), new BasicItemPriceCalculator());
    }

    @Test(expected = NullPointerException.class)
    public void testWithNullPrice() throws Exception {
        new Item("test", null);
    }

    @Test
    public void testCalculateTotalPrice() throws Exception {
        Item item = new Item("test", BigDecimal.valueOf(100), new BasicItemPriceCalculator());
        assertTrue(item.calculateTotalPrice(1).equals(BigDecimal.valueOf(100)));
    }

    @Test(expected = NullPointerException.class)
    public void testWithNullCalculator() throws Exception {
        new Item("test", BigDecimal.TEN, null);
    }

    @Test
    public void testEquality() throws Exception {
        Item item = new Item("test", BigDecimal.valueOf(100), new BasicItemPriceCalculator());
        Set<Item> items = new HashSet<>();
        items.add(item);
        // note: price calculator is not considered for item equality
        assertTrue(items.contains(new Item("test", BigDecimal.valueOf(1), new BasicItemPriceCalculator())));
    }
}