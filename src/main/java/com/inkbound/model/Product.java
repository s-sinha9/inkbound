package com.inkbound.model;

import com.inkbound.repository.ProductRepository;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Product{
    @Id
    private UUID id;
    private String name;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Category category;
    private int stock;

    @Version
    private Long version;

    public Product(){}

    public Product(UUID id){
        this.id=id;
    }

    public UUID getId() { return id; }

    public BigDecimal getPrice() { return price; }

    public Category getCategory() { return category; }

    public int getStock() { return stock; }

    public String getName() { return name; }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}