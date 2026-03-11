package com.example.productapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // Add product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    // Get all products
    @GetMapping
    public List<Product> getProducts() {
        return repository.findAll();
    }

    // Find by category
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return repository.findByCategory(category);
    }

    // Filter by price range
    @GetMapping("/filter")
    public List<Product> filterByPrice(@RequestParam double min, @RequestParam double max) {
        return repository.findByPriceBetween(min, max);
    }

    // Sort by price
    @GetMapping("/sorted")
    public List<Product> getSortedProducts() {
        return repository.findProductsSortedByPrice();
    }

    // Expensive products
    @GetMapping("/expensive/{price}")
    public List<Product> getExpensiveProducts(@PathVariable double price) {
        return repository.findExpensiveProducts(price);
    }
}