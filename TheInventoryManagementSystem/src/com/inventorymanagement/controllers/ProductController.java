package com.inventorymanagement.controllers;

import com.inventorymanagement.models.Product;

import java.util.List;

public class ProductController {


    public List<Product> getAllProducts() {
        return Product.getAllProducts();
    }


    public boolean addProduct(String name, double price, int quantity) {
        Product product = new Product(name, price, quantity);
        return product.addProduct();
    }


    public boolean updateProduct(int id, String name, double price, int quantity) {
        Product product = new Product(id, name, price, quantity);
        return product.updateProduct();
    }


    public boolean deleteProduct(int id) {
        return new Product().deleteProduct(id);
    }


    public Product getProductById(int id) {
        return Product.getProductById(id);
    }
}