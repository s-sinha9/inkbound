package com.inkbound.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AddCartItemRequest {
    @NotNull
    private UUID productId;
    @NotNull
    @Min(1)
    @Max(100)
    private Integer quantity;

    public UUID getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
}
