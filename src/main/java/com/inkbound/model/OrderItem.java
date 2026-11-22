package com.inkbound.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class OrderItem {

    @Id
    private UUID id;

    private UUID productId;

    private String productName;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne
    private Order order;

    public UUID getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Order getOrder() {
        return order;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}