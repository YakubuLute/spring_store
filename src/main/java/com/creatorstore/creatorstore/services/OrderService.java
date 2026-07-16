package com.creatorstore.creatorstore.services;

import com.creatorstore.creatorstore.dto.OderItemRequest;
import com.creatorstore.creatorstore.dto.OrderRequest;
import com.creatorstore.creatorstore.entities.OrderItem;
import com.creatorstore.creatorstore.entities.Product;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import com.creatorstore.creatorstore.repository.*;
import com.creatorstore.creatorstore.entities.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final  OrderRepository orderRepository;
    private final  ProductRepository productRepository;

    @Transactional
    public Order createOrder(@Valid @RequestBody OrderRequest orderRequest){

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        Order order =  new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setStatus("CONFIRMED");

    for(OderItemRequest itemRequest: orderRequest.getOrderItems()) {
        Product product = productRepository.findById(itemRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product with this ID was not found " + itemRequest.getProductId()));

        if (product.getStockQuantity() < itemRequest.getQuantity()) {
            throw new RuntimeException("Not enough stock for this product " + itemRequest.getProductId());
        }
        // calculate total price
        totalPrice = totalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));


        product.setStockQuantity(
                product.getStockQuantity() - itemRequest.getQuantity()
        );
        productRepository.save(product);

        //Builder pattern to make obj
        OrderItem orderItem = OrderItem.builder().order(order).product(product).quantity(itemRequest.getQuantity()).priceAtPurchase(product.getPrice()).build();
        orderItems.add(orderItem);

    }
    order.setTotalPrice(totalPrice);
   order.setOrderItem(orderItems);

        return orderRepository.save(order);

        }

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(()->  new RuntimeException("Order with this ID was not found " + id));
    }
}

