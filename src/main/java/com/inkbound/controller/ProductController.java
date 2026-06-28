package com.inkbound.controller;

import com.inkbound.dto.CreateProductRequest;
import com.inkbound.dto.ProductResponse;
import com.inkbound.model.Product;
import com.inkbound.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest product){
        log.info("ProductController.createProduct "+product.toString());
        ProductResponse response=productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        log.info("ProductController.getAllProducts");

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable UUID id){
        log.info("ProductController.getProductById: "+id);

        return productService.getProductById(id);
    }


}