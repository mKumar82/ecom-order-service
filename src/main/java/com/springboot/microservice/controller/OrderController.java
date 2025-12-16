package com.springboot.microservice.controller;

import com.springboot.microservice.dto.CreateOrderRequest;
import com.springboot.microservice.dto.OrderResponse;
import com.springboot.microservice.entity.Order;
import com.springboot.microservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(
            @Valid @RequestBody CreateOrderRequest request
            ){
        Order order = orderService.createOrder(request.userId(),request.totalAmount());
        return new OrderResponse(order.getId(), order.getTotalAmount(), order.getStatus().name());
    }
}
