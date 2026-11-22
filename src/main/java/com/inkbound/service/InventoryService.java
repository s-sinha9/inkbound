package com.inkbound.service;

import com.inkbound.exception.OutOfStockException;
import com.inkbound.model.Cart;
import com.inkbound.model.CartItem;
import com.inkbound.model.Order;
import com.inkbound.model.Product;
import com.inkbound.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void reserveInventory(Cart cart){
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (product.getStock() < item.getQuantity()) {
                throw new OutOfStockException();
            }

            product.setStock(
                    product.getStock() - item.getQuantity()
            );

            productRepository.save(product);
        };
    }

    public void releaseInventory(Cart cart, Order order){}
}
