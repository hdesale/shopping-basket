package com.github.hdesale.retail.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * JUnit class for {@link ItemFactory}
 *
 * @author Hemant
 */
public class ItemFactoryTest {

    @Test
    public void testGetInstance() throws Exception {
        ItemFactory factory = ItemFactory.getInstance();
        assertNotNull(factory);

        ItemFactory itemFactory = ItemFactory.getInstance();
        assertTrue(factory == itemFactory);
    }

    @Test
    public void testGetItem() throws Exception {
        ItemFactory factory = ItemFactory.getInstance();
        Item apple = factory.getItem("Apple");
        Item banana = factory.getItem("Banana");
        Item melon = factory.getItem("Melon");
        Item lime = factory.getItem("Lime");

        assertNotNull(apple);
        assertNotNull(banana);
        assertNotNull(melon);
        assertNotNull(lime);

        assertTrue(apple.calculateTotalPrice(0).equals(BigDecimal.ZERO));
        assertTrue(apple.calculateTotalPrice(1).equals(BigDecimal.valueOf(35)));
        assertTrue(banana.calculateTotalPrice(1).equals(BigDecimal.valueOf(20)));
        assertTrue(melon.calculateTotalPrice(2).equals(BigDecimal.valueOf(50)));
        assertTrue(lime.calculateTotalPrice(3).equals(BigDecimal.valueOf(30)));
        assertTrue(lime.calculateTotalPrice(-3).equals(BigDecimal.ZERO));
    }

    @Test
    public void testCaseSensitivity() throws Exception {
        ItemFactory factory = ItemFactory.getInstance();
        Item lowerApple = factory.getItem("apple");
        Item upperApple = factory.getItem("APPLE");
        Item titleApple = factory.getItem("Apple");

        assertNotNull(lowerApple);
        assertNotNull(upperApple);
        assertNotNull(titleApple);
        assertTrue(lowerApple == upperApple);
        assertTrue(upperApple == titleApple);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithUnknownItem() throws Exception {
        ItemFactory factory = ItemFactory.getInstance();
        factory.getItem("xyz");
    }
}