package com.inkbound.service;

import com.inkbound.model.OutboxEvent;
import com.inkbound.repository.OutboxRepository;
import com.inkbound.service.order.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OutboxService {

    private static final Logger log = LoggerFactory.getLogger(OutboxService.class);

    private final OutboxRepository repository;
    private final ObjectMapper objectMapper;

    public OutboxService(
            OutboxRepository repository,
            ObjectMapper objectMapper) {

        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public void save(OrderCreatedEvent event) {

        try {
            OutboxEvent outbox = new OutboxEvent();

            outbox.setId(UUID.randomUUID());
            outbox.setAggregateType("ORDER");
            outbox.setAggregateId(event.orderId());
            outbox.setEventType("ORDER_CREATED");
            outbox.setPayload(
                    objectMapper.writeValueAsString(event)
            );
            outbox.setPublished(false);
            outbox.setCreatedAt(LocalDateTime.now());
            repository.save(outbox);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void publishPendingEvents() {

        List<OutboxEvent> events =
                repository.findByPublishedFalse();

        for (OutboxEvent event : events) {

            publish(event);

            event.setPublished(true);

            repository.save(event);
        }
    }

    private void publish(OutboxEvent event) {

        System.out.println(
                "Publishing : " + event.getPayload()
        );

    }

}