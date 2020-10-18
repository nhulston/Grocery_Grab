package com.smithbois.grocerygrab.util;

import com.smithbois.grocerygrab.util.pathfinding.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private static List<Product> cart = new ArrayList<>();
    private static HashMap<String, Double> prices = new HashMap<>();

    public static List<Product> getCart() {
        return cart;
    }

    public static HashMap<String, Double> getPrices() {
        return prices;
    }
}
