package com.inkbound.dto;

import com.inkbound.model.CartItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CartResponse {
    UUID customerId;
    List<CartItem> items;
    int totalItems;
    BigDecimal totalPrice;

    public List<CartItem> getItems() {
        return items;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
