package com.sephora.data.service;

import com.sephora.data.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {

    // Number of product per category
    public Map<String, Long> countProductsByCategory(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategorie,
                        Collectors.counting()
                ));
    }

    // Number of vegan product per brand
    public Map<Integer, Long> countVeganProductsByBrand(List<Product> products) {
        return products.stream()
                .filter(Product::isVegan)
                .collect(Collectors.groupingBy(
                        Product::getBrandId,
                        Collectors.counting()
                ));
    }

    // Average price per category
    public Map<String, Double> averagePriceByCategory(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategorie,
                        Collectors.averagingDouble(Product::getPrix)
                ));
    }

    // 10 most expensive products
    public Map<String, Double> top10MostExpensiveProducts(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrix).reversed())
                .limit(10)
                .collect(Collectors.toMap(
                        Product::getNom,
                        Product::getPrix,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
