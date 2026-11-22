package com.inkbound.service;

import com.inkbound.model.Order;
import com.inkbound.model.Payment;
import com.inkbound.model.PaymentStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    public Payment processPayment(Order order){
//        TODO: stub
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setOrderId(order.getId());
        payment.setAmount(order.getTotalAmount());
        payment.setCreatedAt(LocalDateTime.now());
        return payment;
    }
}
