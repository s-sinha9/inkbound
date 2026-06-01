package com.example.restservice;

import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    ConcurrentHashMap<UUID, Product> products = new ConcurrentHashMap<>();
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return products.values().stream().toList();
    }
    @PostMapping("/products")
    public int createProduct(@RequestBody Product product){
        try {
            products.put(product.getId(), product);
            return Response.SC_CREATED;

        } catch(Exception e){

            return Response.SC_OK;
        }
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable UUID id){
        return products.get(id);
    }

}
