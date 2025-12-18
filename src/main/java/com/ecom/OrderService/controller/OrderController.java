package com.ecom.OrderService.controller;

import com.ecom.OrderService.dto.CreateOrderRequest;
import com.ecom.OrderService.dto.OrderResponse;
import com.ecom.OrderService.entity.Order;
import com.ecom.OrderService.service.OrderService;
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
