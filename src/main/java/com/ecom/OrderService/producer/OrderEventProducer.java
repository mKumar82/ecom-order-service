package com.ecom.OrderService.producer;

import com.ecom.OrderService.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    private  static final String TOPIC = "order.created";

    public void publishOrderCreated(OrderCreatedEvent event){
        kafkaTemplate.send(TOPIC,event.orderId().toString(),event);

        log.info("✅ Order order created", event.orderId());
    }


}
