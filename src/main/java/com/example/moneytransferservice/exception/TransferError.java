package com.example.moneytransferservice.exception;

public class TransferError extends RuntimeException {
    public TransferError(String msg) {
        super(msg);
    }
}

