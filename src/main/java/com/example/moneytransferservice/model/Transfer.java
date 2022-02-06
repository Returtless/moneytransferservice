package com.example.moneytransferservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Transfer {
    private Card cardFrom;
    private Card cardTo;
    private Amount amount;
    private Operation operationId;

    @JsonCreator
    public Card getCardFrom() {
        return cardFrom;
    }

    public void setCardFrom(Card cardFrom) {
        this.cardFrom = cardFrom;
    }

    public Card getCardTo() {
        return cardTo;
    }

    public void setCardTo(Card cardTo) {
        this.cardTo = cardTo;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Operation getOperationId() {
        return operationId;
    }

    public void setOperationId(Operation operationId) {
        this.operationId = operationId;
    }
}
