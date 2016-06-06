package com.github.hdesale.retail;

import com.github.hdesale.retail.model.Basket;

import java.util.Arrays;

/**
 * Sample class to show the usage of Basket and price calculation.
 *
 * @author Hemant
 */
public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.addItem("Apple");
        basket.addItem("Banana");
        basket.addItem("Apple");
        basket.addItems(Arrays.asList("Lime", "Banana", "Melon", "Melon"));

        System.out.println("Basket items: " + basket.getItems());
        System.out.println("Basket price: " + basket.calculateTotalPrice() + "p");
    }
}
