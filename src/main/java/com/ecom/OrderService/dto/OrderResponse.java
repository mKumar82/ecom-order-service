package com.ecom.OrderService.dto;

import java.util.UUID;

public record OrderResponse(
        UUID orderId,
        Double totalAmount,
        String status
) {
}
