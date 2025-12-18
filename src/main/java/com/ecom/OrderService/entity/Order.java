package com.ecom.OrderService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import java.util.UUID;

@Entity
@Table(name="orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Order {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public void markPaymentCompleted(){
        this.status = OrderStatus.PAID;
    }

    public void markPaymentFailed(){
        this.status = OrderStatus.CANCELLED;
    }

}
