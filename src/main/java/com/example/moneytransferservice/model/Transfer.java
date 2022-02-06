package com.example.moneytransferservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transfer {
    private Card cardFrom;
    private Card cardTo;
    private Amount amount;
    private Operation operationId;

    @JsonCreator
    public Transfer(@JsonProperty("cardFromNumber") String fromNumber,
                    @JsonProperty("cardFromValidTill") String validTill,
                    @JsonProperty("cardFromCVV") String cardCVV,
                    @JsonProperty("cardToNumber") String toNumber,
                    @JsonProperty("amount") Amount amount) {
        this.cardFrom = new Card(fromNumber, validTill, cardCVV);
        this.cardTo = new Card(toNumber);
        this.amount = amount;
        this.operationId = new Operation(Operation.generationСode());
    }

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
