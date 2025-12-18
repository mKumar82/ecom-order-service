package com.ecom.OrderService.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderId,
        UUID userId,
        double totalAmount,
        LocalDateTime createdAt
) {
}
