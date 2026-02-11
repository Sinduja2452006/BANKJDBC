package com.wipro.bank.util;

public class InsufficientFundsException extends Exception {
    private static final long serialVersionUID = 1L;

    public InsufficientFundsException() {
        super("INSUFFICIENT FUNDS");
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
