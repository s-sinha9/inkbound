package com.inkbound.service;

import com.inkbound.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final Map<UUID, Product> products = new ConcurrentHashMap<>();

    public Product createProduct(Product product){
        product.setId(UUID.randomUUID());
        products.put(product.getId(),product);
        log.info("Created product with id:"+product.getId());
        return product;
    }
    public List<Product> getAllProducts(){
        return new ArrayList<>(products.values());
    }

    public Product getProductById(UUID id){
        return products.get(id);
    }
}