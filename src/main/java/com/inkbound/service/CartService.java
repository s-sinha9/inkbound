package com.inkbound.service;

import com.inkbound.dto.AddCartItemRequest;
import com.inkbound.dto.CartResponse;
import com.inkbound.exception.CartItemNotFoundException;
import com.inkbound.exception.CustomerNotFoundException;
import com.inkbound.exception.ProductNotFoundException;
import com.inkbound.model.CartItem;
import com.inkbound.model.Customer;
import com.inkbound.model.Cart;
import com.inkbound.model.Product;
import com.inkbound.repository.CartItemRepository;
import com.inkbound.repository.CustomerRepository;
import com.inkbound.repository.CartRepository;
import com.inkbound.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CartService {

    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public CartService(CartItemRepository cartItemRepository,
                       CartRepository cartRepository,
                       CustomerRepository customerRepository,
                       ProductRepository productRepository){
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public CartResponse getCart(UUID customerId){
        log.info("CartService.getCart");
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException(customerId));
        Cart cart = cartRepository.findByCustomer(customer);
        
        // creating response object
        CartResponse response = new CartResponse();
        List<CartItem> items= cart.getItems();
        response.setCustomerId(customerId);
        response.setItems(cart.getItems());
        response.setTotalItems(items.size());
        BigDecimal totalPrice = items.stream().map(CartItem::getSubtotal).reduce(BigDecimal.ZERO,BigDecimal::add);
        response.setTotalPrice(totalPrice);
        return response;
    }

    public CartResponse createItem(UUID customerId, AddCartItemRequest item){
        log.info("CartService.createItem");
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException(customerId));
        Cart cart = cartRepository.findByCustomer(customer);
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setId(UUID.randomUUID());
        Product product = productRepository.findById(item.getProductId()).orElseThrow(()-> new ProductNotFoundException(item.getProductId()));
        cartItem.setProduct(product);
        cartItem.setQuantity(item.getQuantity());

        cartItemRepository.save(cartItem);

        // creating response object
        CartResponse response = new CartResponse();
        List<CartItem> items= cart.getItems();
        items.add(cartItem);
        response.setCustomerId(customerId);
        response.setItems(cart.getItems());
        response.setTotalItems(items.size());
        BigDecimal totalPrice = items.stream().map(CartItem::getSubtotal).reduce(BigDecimal.ZERO,BigDecimal::add);
        response.setTotalPrice(totalPrice);
        return response;
    }

    public CartResponse updateItem(UUID customerId, UUID itemId, AddCartItemRequest item){
        log.info("CartService.updateItem");
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException(customerId));
        CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow(()->new CartItemNotFoundException(itemId));

        cartItem.setQuantity(cartItem.getQuantity()+item.getQuantity());
        cartItemRepository.save(cartItem);

        // creating response object
        Cart cart = cartRepository.findByCustomer(customer);
        CartResponse response = new CartResponse();
        List<CartItem> items= cart.getItems();
        response.setCustomerId(customerId);
        response.setItems(cart.getItems());
        response.setTotalItems(items.size());
        BigDecimal totalPrice = items.stream().map(CartItem::getSubtotal).reduce(BigDecimal.ZERO,BigDecimal::add);
        response.setTotalPrice(totalPrice);
        return response;
    }

    public CartResponse deleteItem(UUID customerId, UUID itemId){
        log.info("CartService.deleteItem");
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException(customerId));
        CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow(()->new CartItemNotFoundException(itemId));
        Cart cart = cartRepository.findByCustomer(customer);

        if(cartItem.getCart().equals(cart)) {
            cartItemRepository.delete(cartItem);
        }

        // creating response object
        CartResponse response = new CartResponse();
        cart = cartRepository.findByCustomer(customer);
        List<CartItem> items= cart.getItems();
        response.setCustomerId(customerId);
        response.setItems(cart.getItems());
        response.setTotalItems(items.size());
        BigDecimal totalPrice = items.stream().map(CartItem::getSubtotal).reduce(BigDecimal.ZERO,BigDecimal::add);
        response.setTotalPrice(totalPrice);
        return response;
    }

    public CartResponse deleteAllCartItems(UUID customerId){
        log.info("CartService.deleteAllCartItems");
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException(customerId));
        Cart cart = cartRepository.findByCustomer(customer);

        cartItemRepository.deleteAll(cart.getItems());

        // creating response object
        CartResponse response = new CartResponse();
        cart = cartRepository.findByCustomer(customer);
        List<CartItem> items= cart.getItems();
        response.setCustomerId(customerId);
        response.setItems(cart.getItems());
        response.setTotalItems(items.size());
        BigDecimal totalPrice = items.stream().map(CartItem::getSubtotal).reduce(BigDecimal.ZERO,BigDecimal::add);
        response.setTotalPrice(totalPrice);
        return response;
    }
}
