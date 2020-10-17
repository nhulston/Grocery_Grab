package com.smithbois.grocerygrab.util;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static List<String> cart = new ArrayList<>();

    public static List<String> getCart() {
        return cart;
    }
}
