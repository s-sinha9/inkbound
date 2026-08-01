package com.inkbound.service;

import com.inkbound.service.order.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuditListener {

    private static final Logger log = LoggerFactory.getLogger(AuditListener.class);

    @EventListener
    public void handle(OrderCreatedEvent event) {
        log.info("AUDIT: Order {} created for customer {}",
                event.orderId(),
                event.customerId());
    }
}
