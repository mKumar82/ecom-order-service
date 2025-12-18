package com.ecom.OrderService.event;

import java.util.UUID;

public record PaymentCompletedEvent(
        UUID orderId,
        UUID paymentId
) {
}
