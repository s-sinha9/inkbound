package com.inkbound.repository;

import com.inkbound.model.Customer;
import com.inkbound.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {

    Cart findByCustomer(Customer customer);
}
