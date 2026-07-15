package com.creatorstore.creatorstore.controllers;

import com.creatorstore.creatorstore.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.creatorstore.creatorstore.entities.Product;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductController {

    final ProductService productService;

    @PostMapping // POST HTTP request method
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

     @PutMapping("/{id}") // PUT HTTP request method
    public Product updateProduct(@PathVariable Long id,  @Valid @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")  // DELETE HTTP request method
    public void deleteProduct(@PathVariable Long id) {
    
    }

    @GetMapping("/{id}")  // GET HTTP request method
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping // GET HTTP request method
    public List<Product> getProducts() {
        return productService.getProducts();
    }


}

