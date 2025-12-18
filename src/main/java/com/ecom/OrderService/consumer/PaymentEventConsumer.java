package com.ecom.OrderService.consumer;

import com.ecom.OrderService.entity.Order;
import com.ecom.OrderService.event.PaymentCompletedEvent;
import com.ecom.OrderService.event.PaymentFailedEvent;
import com.ecom.OrderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventConsumer {
    private final OrderRepository orderRepository;

    @KafkaListener(topics = "payment.completed",groupId = "order-service-group")
    public void handlePaymentCompleted(PaymentCompletedEvent event){

        Order order = orderRepository.findById(event.orderId()).orElseThrow();

        order.markPaymentCompleted();
        orderRepository.save(order);
        log.info("✅ Order {} marked as PAYMENT_COMPLETED", event.orderId());
    }

    @KafkaListener(topics = "payment.failed",groupId = "order-service-group")
    public void handlePaymentFailed(PaymentFailedEvent event){

        Order order = orderRepository.findById(event.orderId()).orElseThrow();

        order.markPaymentFailed();
        orderRepository.save(order);
        log.info("❌ Order {} marked as PAYMENT_FAILED", event.orderId());
    }
}
