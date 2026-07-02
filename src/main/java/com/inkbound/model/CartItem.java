package com.inkbound.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class CartItem {
    @Id
    private UUID id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Cart cart;
    private int quantity;

    public UUID getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getSubtotal(){
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
