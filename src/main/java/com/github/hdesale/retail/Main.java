package com.github.hdesale.retail;

import com.github.hdesale.retail.model.Basket;

import java.util.Arrays;
import java.util.List;

/**
 * Sample class to show the usage of Basket and price calculation.
 *
 * @author Hemant
 */
public class Main {

    public static void main(String[] args) {
        List<String> items = Arrays.asList("Apple", "Banana", "Apple", "Lime", "Banana", "Melon", "Melon");
        Basket basket = new Basket(items);
        System.out.println("Basket items: " + basket.getItems());
        System.out.println("Basket price: " + basket.calculateTotalPrice() + "p");
    }
}
