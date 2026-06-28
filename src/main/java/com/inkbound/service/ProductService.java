package com.inkbound.service;

import com.inkbound.dto.CreateProductRequest;
import com.inkbound.dto.ProductResponse;
import com.inkbound.exception.ProductNotFoundException;
import com.inkbound.model.Product;
import com.inkbound.repository.ProductRepository;
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
//    private final Map<UUID, Product> products = new ConcurrentHashMap<>();
    private final ProductRepository repository;

    public ProductService(ProductRepository productRepository){
        this.repository = productRepository;
    }

    public ProductResponse createProduct(CreateProductRequest request){
        log.info("ProductService.createProduct"+request.toString());
        Product product=new Product(UUID.randomUUID());
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        repository.save(product);
        ProductResponse response = new ProductResponse(product);
        log.info("Created product with id:"+product.getId());
        return response;
    }
    public List<Product> getAllProducts(){
        log.info("ProductService.getAllProducts");
        return repository.findAll();
    }

    public ProductResponse getProductById(UUID id){
        log.info("ProductService.getProductById: "+id);
        Product product = repository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
        return new ProductResponse(product);
    }
}