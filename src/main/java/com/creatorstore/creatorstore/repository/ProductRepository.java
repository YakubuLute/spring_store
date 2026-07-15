package com.creatorstore.creatorstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creatorstore.creatorstore.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
