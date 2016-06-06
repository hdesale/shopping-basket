package com.github.hdesale.retail.pricing.basket;

import java.math.BigDecimal;

/**
 * Basket price calculator interface.
 *
 * @author Hemant
 */
public interface BasketPriceCalculator {

    BigDecimal calculateTotalPrice();
}
