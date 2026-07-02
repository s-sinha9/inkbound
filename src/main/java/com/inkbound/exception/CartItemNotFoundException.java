package com.inkbound.exception;

import java.util.UUID;

public class CartItemNotFoundException extends RuntimeException{

    public CartItemNotFoundException(UUID id){
        super("Cart item with id "+id+ " not found!");
    }
}
