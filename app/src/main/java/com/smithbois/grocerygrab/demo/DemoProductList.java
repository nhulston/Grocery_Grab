package com.smithbois.grocerygrab.demo;

import com.smithbois.grocerygrab.activities.ARActivity;
import com.smithbois.grocerygrab.util.pathfinding.Product;

import java.util.ArrayList;
import java.util.List;

public class DemoProductList {

    public static List<Product> list(){
        List<Product> list = new ArrayList<>();
        list.add(new Product(2, 1, "cheese",5));
        list.add(new Product(5, 5, "milk",6));
        list.add(new Product(6, 7, "chips",7));
        return list;
    }
}
