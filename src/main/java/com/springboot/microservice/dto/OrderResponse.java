package com.springboot.microservice.dto;

import java.util.UUID;

public record OrderResponse(
        UUID orderId,
        Double totalAmount,
        String status
) {
}
