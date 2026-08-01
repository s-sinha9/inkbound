package com.inkbound.service.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    private static final Logger log = LoggerFactory.getLogger(OrderCreatedListener.class);

    @EventListener
    public void handle(OrderCreatedEvent event) {
        log.info("Order {} created for customer {}",
                event.orderId(),
                event.customerId());
    }
}