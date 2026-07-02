package com.inkbound.controller;

import com.inkbound.dto.AddCartItemRequest;
import com.inkbound.dto.CartResponse;
import com.inkbound.dto.CustomerResponse;
import com.inkbound.service.CartService;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customers/{customerId}/cart")
public class CartController {
    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart(@PathVariable UUID customerId){
        log.info("CartController.getCart");
        CartResponse response = this.cartService.getCart(customerId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/items")
    public ResponseEntity<CartResponse> createItem(@PathVariable UUID customerId, @RequestParam AddCartItemRequest item){
        log.info("CartController.createItem");
        CartResponse response = this.cartService.createItem(customerId, item);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<CartResponse> updateItem(@PathVariable UUID customerId, @PathVariable UUID itemId, @RequestParam AddCartItemRequest item){
        log.info("CartController.updateItem");
        CartResponse response = this.cartService.updateItem(customerId, itemId, item);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<CartResponse> deleteItem(@PathVariable UUID customerId, @PathVariable UUID itemId){
        log.info("CartController.deleteItem");
        CartResponse response = this.cartService.deleteItem(customerId, itemId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<CartResponse> deleteAllCartItems(@PathVariable UUID customerId){
        log.info("CartController.deleteAllCartItems");
        CartResponse response = this.cartService.deleteAllCartItems(customerId);
        return ResponseEntity.ok(response);
    }

}
