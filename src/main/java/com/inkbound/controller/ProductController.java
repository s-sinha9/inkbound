package com.inkbound.controller;

import com.inkbound.dto.CreateProductRequest;
import com.inkbound.dto.ProductResponse;
import com.inkbound.model.Category;
import com.inkbound.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ResponseEntity<Page<ProductResponse>> getAllProducts(@PageableDefault(size=20) Pageable pageable){
        log.info("ProductController.getAllProducts");
        Page<ProductResponse> res = productService.getAllProducts(pageable);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id){
        log.info("ProductController.getProductById: "+id);
        ProductResponse res = productService.getProductById(id);
        return ResponseEntity.ok(res);

    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>> getProductByCategory(@PathVariable Category category){
        log.info("ProductController.getProductByCategory");
        List<ProductResponse> res = productService.getProductsByCategory(category);
        return ResponseEntity.ok(res);

    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword){
        log.info("ProductController.searchProducts");
        List<ProductResponse> res = productService.searchProducts(keyword);
        return ResponseEntity.ok(res);

    }

}