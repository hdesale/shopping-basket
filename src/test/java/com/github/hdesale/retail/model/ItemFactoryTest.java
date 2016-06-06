package com.github.hdesale.retail.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

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
        Optional<Item> apple = factory.getItem("Apple");
        Optional<Item> banana = factory.getItem("Banana");
        Optional<Item> melon = factory.getItem("Melon");
        Optional<Item> lime = factory.getItem("Lime");
        Optional<Item> xyz = factory.getItem("xyz");

        assertTrue(apple.isPresent());
        assertTrue(banana.isPresent());
        assertTrue(melon.isPresent());
        assertTrue(lime.isPresent());
        assertFalse(xyz.isPresent());

        assertTrue(apple.get().calculateTotalPrice(0).equals(BigDecimal.ZERO));
        assertTrue(apple.get().calculateTotalPrice(1).equals(BigDecimal.valueOf(35)));
        assertTrue(banana.get().calculateTotalPrice(1).equals(BigDecimal.valueOf(20)));
        assertTrue(melon.get().calculateTotalPrice(2).equals(BigDecimal.valueOf(50)));
        assertTrue(lime.get().calculateTotalPrice(3).equals(BigDecimal.valueOf(30)));
        assertTrue(lime.get().calculateTotalPrice(-3).equals(BigDecimal.ZERO));
    }

    @Test
    public void testCaseSensitivity() throws Exception {
        ItemFactory factory = ItemFactory.getInstance();
        Optional<Item> lowerApple = factory.getItem("apple");
        Optional<Item> upperApple = factory.getItem("APPLE");
        Optional<Item> titleApple = factory.getItem("Apple");

        assertTrue(lowerApple.isPresent());
        assertTrue(upperApple.isPresent());
        assertTrue(titleApple.isPresent());
        assertTrue(lowerApple.get() == upperApple.get());
        assertTrue(upperApple.get() == titleApple.get());
    }
}