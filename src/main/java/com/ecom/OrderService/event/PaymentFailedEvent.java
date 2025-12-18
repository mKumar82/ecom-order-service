package com.ecom.OrderService.event;

import java.util.UUID;

public record PaymentFailedEvent(
        UUID orderId,
        String reason
) {
}
