package com.minealex2244.univtest;

import java.util.ArrayList;
import java.util.Random;

public class Product {
    private String mName;
    private int mStock;

    public Product(String name, int stock) {
        mName = name;
        mStock = stock;
    }

    public String getName() {
        return mName;
    }

    public int getStock() {
        return mStock;
    }

    private static int lastProductId = 0;

    public static ArrayList<Product> createProductsList(int numProducts) {
        ArrayList<Product> products = new ArrayList<Product>();
        Random r = new Random();
        int result = r.nextInt(80) + 10;

        for (int i = 1; i <= numProducts; i++) {
            products.add(new Product("Product " + ++lastProductId, result));
            result = r.nextInt(20);
        }

        return products;
    }
}
