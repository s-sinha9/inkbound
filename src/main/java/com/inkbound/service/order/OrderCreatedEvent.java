package com.inkbound.service.order;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderCreatedEvent(

        UUID orderId,

        UUID customerId,

        BigDecimal amount

) {}