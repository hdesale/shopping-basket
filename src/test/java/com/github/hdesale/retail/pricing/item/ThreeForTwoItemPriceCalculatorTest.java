package com.github.hdesale.retail.pricing.item;

import com.github.hdesale.retail.model.Item;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

/**
 * JUnit class for {@link ThreeForTwoItemPriceCalculator}
 *
 * @author Hemant
 */
public class ThreeForTwoItemPriceCalculatorTest {

    @Test
    public void testCalculateTotalPrice() throws Exception {
        BasicItemPriceCalculator basicCalculator = new BasicItemPriceCalculator();
        ThreeForTwoItemPriceCalculator calculator = new ThreeForTwoItemPriceCalculator(basicCalculator);
        Item testItem = new Item("Test", BigDecimal.TEN, calculator);
        assertTrue(testItem.calculateTotalPrice(25).equals(BigDecimal.valueOf(170)));
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateTotalPriceWithNullCalculator() throws Exception {
        new ThreeForTwoItemPriceCalculator(null);
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateTotalPriceWithNullItemPrice() throws Exception {
        BasicItemPriceCalculator basicCalculator = new BasicItemPriceCalculator();
        ThreeForTwoItemPriceCalculator calculator = new ThreeForTwoItemPriceCalculator(basicCalculator);
        calculator.calculateTotalPrice(null, 1);
    }

    @Test
    public void testCalculateTotalPriceWithZeroQuantity() throws Exception {
        BasicItemPriceCalculator basicCalculator = new BasicItemPriceCalculator();
        ThreeForTwoItemPriceCalculator calculator = new ThreeForTwoItemPriceCalculator(basicCalculator);
        assertTrue(calculator.calculateTotalPrice(BigDecimal.TEN, 0).equals(BigDecimal.ZERO));
    }
}