package com.example.moneytransferservice.model;

public class Confirmation {
    private Operation operationId;
    private String code;

    public Operation getOperationId() {
        return operationId;
    }

    public void setOperationId(Operation operationId) {
        this.operationId = operationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
