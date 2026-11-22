package com.inkbound.dto;

import com.inkbound.model.OrderItem;
import com.inkbound.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        BigDecimal totalAmount,
        OrderStatus status,
        LocalDateTime createdAt,
        List<OrderItem> items
) {
}
