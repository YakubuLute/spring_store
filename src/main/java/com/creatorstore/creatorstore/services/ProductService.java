package com.creatorstore.creatorstore.services;

import com.creatorstore.creatorstore.entities.Product;
import com.creatorstore.creatorstore.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public Product createProduct( Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct( Long id, Product product) {
      Product foundProduct = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
          foundProduct.setName(product.getName());
          foundProduct.setDescription(product.getDescription());
          foundProduct.setPrice(product.getPrice());
          foundProduct.setCategory(product.getCategory());
          foundProduct.setStockQuantity(product.getStockQuantity());
          return productRepository.save(foundProduct);
    }

    public void deleteProduct( Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById( Long id) {
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
    }

    public List<Product> getProducts() {

      List<Product>  foundProduct = productRepository.findAll();
      if(foundProduct.isEmpty()){
          throw new RuntimeException("Product not found");
      }
      return foundProduct;
    }

}
