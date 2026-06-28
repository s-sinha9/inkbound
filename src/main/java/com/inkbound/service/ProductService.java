package com.inkbound.service;

import com.inkbound.dto.CreateProductRequest;
import com.inkbound.dto.ProductResponse;
import com.inkbound.exception.ProductNotFoundException;
import com.inkbound.model.Category;
import com.inkbound.model.Product;
import com.inkbound.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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
        return mapToResponse(product);
    }

    public Page<ProductResponse> getAllProducts(Pageable pageable){
        log.info("ProductService.getAllProducts");
        Page<Product> products = repository.findAll(pageable);
        return products.map(this::mapToResponse);
    }

    public ProductResponse getProductById(UUID id){
        log.info("ProductService.getProductById: "+id);
        Product product = repository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
        return mapToResponse(product);
    }

    public List<ProductResponse> getProductsByCategory(Category category){
        log.info("ProductService.getProductsByCategory "+category);
        List<Product> products = repository.findByCategory(category);
        return products.stream().map(this::mapToResponse).toList();

    }

    public List<ProductResponse> searchProducts(String keyword){
        log.info("ProductService.searchProducts");
        List<Product> products = repository.findByNameContainingIgnoreCase(keyword);
        return products.stream().map(this::mapToResponse).toList();

    }


    private ProductResponse mapToResponse(Product product){
        ProductResponse pr = new ProductResponse();
        pr.setId(product.getId());
        pr.setName(product.getName());
        pr.setCategory(product.getCategory());
        pr.setPrice(product.getPrice());
        pr.setStock(product.getStock());
        return pr;
    }
}