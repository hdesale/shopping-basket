package com.github.hdesale.retail.pricing.item;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

/**
 * JUnit class for {@link BuyOneGetOneFreeItemPriceCalculator}
 *
 * @author Hemant
 */
public class BuyOneGetOneFreeItemPriceCalculatorTest {

    @Test
    public void testCalculateTotalPrice() throws Exception {
        BasicItemPriceCalculator basicCalculator = new BasicItemPriceCalculator(BigDecimal.TEN);
        BuyOneGetOneFreeItemPriceCalculator calculator = new BuyOneGetOneFreeItemPriceCalculator(basicCalculator);
        assertTrue(calculator.calculateTotalPrice(25).equals(BigDecimal.valueOf(130)));
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateTotalPriceWithNullCalculator() throws Exception {
        new BuyOneGetOneFreeItemPriceCalculator(null);
    }
}