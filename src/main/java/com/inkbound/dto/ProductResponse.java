package com.inkbound.dto;

import com.inkbound.model.Category;
import com.inkbound.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductResponse {
    private UUID id;
    private String name;
    private BigDecimal price;
    private Category category;
    private int stock;
    public ProductResponse(Product product){
        this.id=product.getId();
        this.name=product.getName();
        this.category=product.getCategory();
        this.stock=product.getStock();
        this.price=product.getPrice();
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
