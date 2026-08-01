package com.inkbound.exception;

import java.util.UUID;

public class EmptyCartException extends RuntimeException{
    public EmptyCartException() {
        super("Cart is empty");
    }
}
