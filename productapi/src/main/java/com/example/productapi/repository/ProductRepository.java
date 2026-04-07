package com.example.productapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.productapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Derived query method
    List<Product> findByCategory(String category);

    // Derived query method
    List<Product> findByPriceBetween(double min, double max);

    // JPQL query for sorting
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findProductsSortedByPrice();

    // JPQL query for expensive products
    @Query("SELECT p FROM Product p WHERE p.price > ?1")
    List<Product> findExpensiveProducts(double price);
}