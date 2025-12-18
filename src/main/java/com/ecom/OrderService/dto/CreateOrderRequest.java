package com.ecom.OrderService.dto;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record CreateOrderRequest(
        @NotNull
        UUID userId,

        @NotNull
        @Positive
        Double totalAmount
) {
}
