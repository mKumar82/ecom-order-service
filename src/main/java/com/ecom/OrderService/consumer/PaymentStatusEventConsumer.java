package com.ecom.OrderService.consumer;

import com.ecom.OrderService.entity.Order;
import com.ecom.OrderService.event.PaymentCompletedEvent;
import com.ecom.OrderService.event.PaymentFailedEvent;
import com.ecom.OrderService.repository.OrderRepository;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentStatusEventConsumer {
    private final OrderRepository orderRepository;

    @KafkaListener(topics = "payment.completed")
    public void handlePaymentCompleted(JsonNode event) {
        UUID orderId = UUID.fromString(event.get("orderId").asText());
        UUID paymentId = UUID.fromString(event.get("paymentId").asText());

        log.info("✅ Payment completed orderId={} paymentId={}", orderId, paymentId);

        Order order = orderRepository.findById(orderId).orElseThrow();
        order.markPaymentCompleted();
        orderRepository.save(order);


    }

    @KafkaListener(topics = "payment.failed")
    public void handlePaymentFailed(JsonNode event) {
        UUID orderId = UUID.fromString(event.get("orderId").asText());
        String reason = event.get("reason").asText();

        log.info("❌ Payment failed orderId={} reason={}", orderId, reason);

        Order order = orderRepository.findById(orderId).orElseThrow();
        order.markPaymentFailed();
        orderRepository.save(order);
    }

}
