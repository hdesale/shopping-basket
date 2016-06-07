package com.github.hdesale.retail.pricing.basket;

import com.github.hdesale.retail.model.Basket;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * JUnit class for {@link BasicBasketPriceCalculator}
 *
 * @author Hemant
 */
public class BasicBasketPriceCalculatorTest {

    @Test
    public void testCalculateTotalPrice() throws Exception {
        BasicBasketPriceCalculator calculator = new BasicBasketPriceCalculator();
        Basket basket = new Basket(Arrays.asList("Apple", "Banana", "Apple"), calculator);
        assertTrue(basket.calculateTotalPrice().equals(BigDecimal.valueOf(90)));
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateTotalPriceWithNullQuantities() throws Exception {
        BasicBasketPriceCalculator calculator = new BasicBasketPriceCalculator();
        calculator.calculateTotalPrice(null);
    }
}