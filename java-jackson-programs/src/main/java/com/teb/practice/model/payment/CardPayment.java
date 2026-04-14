package com.teb.practice.model.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("card")
public class CardPayment extends Payment {

    @JsonIgnore private final String cardNumber;

    @JsonCreator
    public CardPayment(
            @JsonProperty("id") long id,
            @JsonProperty("amount") double amount,
            @JsonProperty("customerId") long customerId,
            @JsonProperty("cardNumber") String cardNumber) {
        super(id, amount, customerId);
        this.cardNumber = cardNumber;
    }
}
