package com.inkbound.service.order;

import com.inkbound.config.CurrentUserService;
import com.inkbound.dto.OrderResponse;
import com.inkbound.exception.EmptyCartException;
import com.inkbound.model.*;
import com.inkbound.repository.OrderRepository;
import com.inkbound.service.CartService;
import com.inkbound.service.InventoryService;
import com.inkbound.service.OutboxService;
import com.inkbound.service.PaymentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    private final CartService cartService;
    private final CurrentUserService currentUserService;
    private  final PaymentService paymentService;
    private final InventoryService inventoryService;

    private final ApplicationEventPublisher publisher;
    private final OutboxService outboxService;

    public OrderService(OrderRepository orderRepository, CartService cartService,
                        CurrentUserService currentUserService, InventoryService inventoryService,
                        PaymentService paymentService, OutboxService outboxService,
                        ApplicationEventPublisher publisher){
        this.orderRepository = orderRepository;
        this.cartService=cartService;
        this.currentUserService = currentUserService;
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.outboxService = outboxService;
        this.publisher = publisher;
    }

    @Transactional
    public OrderResponse createPendingOrder(){
        UUID customerId = currentUserService.getCurrentUser().userId();
        log.info("OrderService.createOrder started for "+currentUserService.getCurrentUser().username());
         Cart cart= cartService.getCartByCustomerId(customerId);
        validateCart(cart);
        inventoryService.reserveInventory(cart);
        Order order=createOrder(cart, customerId);
        Order savedOrder = orderRepository.save(order);
        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                customerId,
                savedOrder.getTotalAmount()
        );

        outboxService.save(event);

        publisher.publishEvent(event);
        return createOrderResponse(savedOrder);
    }

    public void confirmPayment(Order order){}

    public void cancelOrder(){}

    public void expireOrder(){}

    private void validateCart(Cart cart){
        if (cart.getItems().isEmpty()) {
            throw new EmptyCartException();
        }
    }

    private Order createOrder(Cart cart, UUID customerId){
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setCustomerId(customerId);
        BigDecimal totalAmount = cart.getItems().stream().map(CartItem::getSubtotal).reduce(BigDecimal.ZERO,BigDecimal::add);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.PAYMENT_PENDING);
        order.setCreatedAt(LocalDateTime.now());
        List<OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> {
                    return mapCartItemToOrderItem(cartItem, order);
                }).toList();
        order.setItems(orderItems);
        return order;
    }

    private PaymentStatus processPayment(Order order){
        Payment payment = paymentService.processPayment(order);
        return payment.getStatus();
    }

    private OrderResponse createOrderResponse(Order order){
        return new OrderResponse(order.getTotalAmount(),
                order.getStatus(), order.getCreatedAt(), order.getItems());

    }

    private OrderItem mapCartItemToOrderItem(CartItem cartItem, Order order){
        OrderItem om = new OrderItem();
        om.setId(UUID.randomUUID());
        om.setOrder(order);
        om.setProductId(cartItem.getProduct().getId());
        om.setProductName(cartItem.getProduct().getName());
        om.setQuantity(cartItem.getQuantity());
        om.setPrice(cartItem.getProduct().getPrice());
        return om;
    }
}
