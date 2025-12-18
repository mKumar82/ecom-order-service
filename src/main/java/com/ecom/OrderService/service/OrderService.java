package com.ecom.OrderService.service;

import com.ecom.OrderService.entity.Order;
import com.ecom.OrderService.entity.OrderStatus;
import com.ecom.OrderService.event.OrderCreatedEvent;
import com.ecom.OrderService.producer.OrderEventProducer;
import com.ecom.OrderService.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

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


        orderEventProducer.publishOrderCreated(event);
        return savedOrder;
    }
}
