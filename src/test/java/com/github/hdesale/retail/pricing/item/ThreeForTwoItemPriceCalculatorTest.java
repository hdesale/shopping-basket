package com.github.hdesale.retail.pricing.item;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * JUnit class for {@link ThreeForTwoItemPriceCalculator}
 *
 * @author Hemant
 */
public class ThreeForTwoItemPriceCalculatorTest {

    @Test
    public void testCalculateTotalPrice() throws Exception {
        BasicItemPriceCalculator basicCalculator = new BasicItemPriceCalculator(BigDecimal.TEN);
        ThreeForTwoItemPriceCalculator calculator = new ThreeForTwoItemPriceCalculator(basicCalculator);
        assertTrue(calculator.calculateTotalPrice(25).equals(BigDecimal.valueOf(170)));
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateTotalPriceWithNullCalculator() throws Exception {
        new ThreeForTwoItemPriceCalculator(null);
    }
}