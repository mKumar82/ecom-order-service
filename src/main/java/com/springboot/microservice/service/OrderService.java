package com.springboot.microservice.service;

import com.springboot.microservice.entity.Order;
import com.springboot.microservice.entity.OrderStatus;
import com.springboot.microservice.repository.OrderRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order createOrder(UUID userId, Double totalAmount){

        Order order = Order.builder()
                .userId(userId)
                .totalAmount(totalAmount)
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();
        return orderRepository.save(order);
    }
}
