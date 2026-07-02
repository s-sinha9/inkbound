package com.inkbound.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;
import java.util.UUID;

@Entity
public class Cart {
    @Id
    private UUID id;
    @OneToOne
    private Customer customer;
    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;
    private Status status;

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
