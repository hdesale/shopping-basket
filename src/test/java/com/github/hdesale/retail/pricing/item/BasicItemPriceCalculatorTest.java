package com.github.hdesale.retail.pricing.item;

import com.github.hdesale.retail.model.Item;
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
        BasicItemPriceCalculator calculator = new BasicItemPriceCalculator();
        Item testItem = new Item("Test", BigDecimal.valueOf(15), calculator);
        assertTrue(testItem.calculateTotalPrice(5).equals(BigDecimal.valueOf(75)));
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateTotalPriceWithNullItemPrice() throws Exception {
        BasicItemPriceCalculator calculator = new BasicItemPriceCalculator();
        calculator.calculateTotalPrice(null, 1);
    }

    @Test
    public void testCalculateTotalPriceWithZeroQuantity() throws Exception {
        BasicItemPriceCalculator basicCalculator = new BasicItemPriceCalculator();
        assertTrue(basicCalculator.calculateTotalPrice(BigDecimal.TEN, 0).equals(BigDecimal.ZERO));
    }
}