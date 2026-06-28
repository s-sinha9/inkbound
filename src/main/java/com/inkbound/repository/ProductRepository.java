package com.inkbound.repository;

import com.inkbound.model.Category;
import com.inkbound.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByCategory(Category category);

    List<Product> findByNameContainingIgnoreCase(String keyword);

}
