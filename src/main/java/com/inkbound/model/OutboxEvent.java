package com.inkbound.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class OutboxEvent {

    @Id
    private UUID id;

    private String aggregateType;

    private UUID aggregateId;

    private String eventType;

    @Lob
    private String payload;

    private boolean published;

    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public UUID getAggregateId() {
        return aggregateId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getPayload() {
        return payload;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAggregateId(UUID aggregateId) {
        this.aggregateId = aggregateId;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
