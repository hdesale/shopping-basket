package com.github.hdesale.retail.pricing.basket;

import com.github.hdesale.retail.model.Basket;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

/**
 * JUnit class for {@link BasicBasketPriceCalculator}
 *
 * @author Hemant
 */
public class BasicBasketPriceCalculatorTest {

    @Test
    public void testCalculateTotalPrice() throws Exception {
        Basket basket = new Basket();
        basket.addItem("Apple");
        basket.addItem("Banana");
        basket.addItem("Apple");
        BasicBasketPriceCalculator calculator = new BasicBasketPriceCalculator(basket.getItems());
        assertTrue(calculator.calculateTotalPrice().equals(BigDecimal.valueOf(90)));
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateTotalPriceWithoutBasket() throws Exception {
        BasicBasketPriceCalculator calculator = new BasicBasketPriceCalculator(null);
        calculator.calculateTotalPrice();
    }
}