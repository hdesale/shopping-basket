package com.github.hdesale.retail.pricing.basket;

import com.github.hdesale.retail.model.Item;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Basket price calculator interface.
 *
 * @author Hemant
 */
public interface BasketPriceCalculator {

    BigDecimal calculateTotalPrice(Map<Item, Long> quantitiesPerItem);
}
