package com.ecom.OrderService.service;

import com.ecom.OrderService.entity.Order;
import com.ecom.OrderService.entity.OrderStatus;
import com.ecom.OrderService.event.OrderCreatedEvent;
import com.ecom.OrderService.producer.OrderCreatedEventProducer;
import com.ecom.OrderService.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderCreatedEventProducer orderCreatedEventProducer;

    @Transactional
    public Order createOrder(UUID userId, Double totalAmount){

        Order order = Order.builder()
                .userId(userId)
                .totalAmount(totalAmount)
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();


        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getTotalAmount(),
                savedOrder.getCreatedAt()
        );


        orderCreatedEventProducer.publishOrderCreated(event);
        return savedOrder;
    }
}
