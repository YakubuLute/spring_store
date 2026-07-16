package com.creatorstore.creatorstore.controllers;

import com.creatorstore.creatorstore.dto.OrderRequest;
import com.creatorstore.creatorstore.entities.Order;
import com.creatorstore.creatorstore.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public Order createOrder(@Valid @RequestBody  OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    //Get all orders
    public List<Order> getAllOrder(){
        return orderService.getAllOrder();
    }

    public Order getOrderById(Long orderId){
        return orderService.getOrderById(id);
    }

}
