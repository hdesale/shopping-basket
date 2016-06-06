package com.github.hdesale.retail.pricing.item;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

/**
 * JUnit class for {@link BasicItemPriceCalculator}
 *
 * @author Hemant
 */
public class BasicItemPriceCalculatorTest {

    @Test
    public void testCalculateTotalPrice() throws Exception {
        BasicItemPriceCalculator calculator = new BasicItemPriceCalculator(BigDecimal.valueOf(15));
        assertTrue(calculator.calculateTotalPrice(5).equals(BigDecimal.valueOf(75)));
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateTotalPriceWithNullItemPrice() throws Exception {
        new BasicItemPriceCalculator(null);
    }
}