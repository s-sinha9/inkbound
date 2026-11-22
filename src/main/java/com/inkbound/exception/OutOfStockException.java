package com.inkbound.exception;

public class OutOfStockException extends RuntimeException{
    public OutOfStockException() {
        super("Insufficient stock for product");
    }
}
