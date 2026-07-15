package com.creatorstore.creatorstore.services;

import org.springframework.stereotype.Service;

import com.creatorstore.creatorstore.repository.OrderRepository;
import com.creatorstore.creatorstore.entities.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    final  OrderRepository orderRepository;
    final  ProductRepository productRepository;

    public Order createOrder(OrderRequest orderRequest){

    }
}
