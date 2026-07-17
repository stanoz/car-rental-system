package com.state.street.backend.exceptions.car;

public class InvalidCarStockAmountException extends Exception {
    public InvalidCarStockAmountException(int number) {
        super(String.format("Unable to decrement Car's inStock by %d. In stock value after decrementation must be at least 0.", number));
    }
}
