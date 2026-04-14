package com.teb.practice.model.payment;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;

@JsonTypeInfo(use = NAME, include = PROPERTY, property = "type", defaultImpl = Payment.class)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DirectPayment.class, name = "direct"),
    @JsonSubTypes.Type(value = CardPayment.class, name = "card")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
public abstract class Payment {

    @JsonProperty("id")
    private final long id;

    private final double amount;
    private final long customerId;

    protected Payment(long id, double amount, long customerId) {
        this.id = id;
        this.amount = amount;
        this.customerId = customerId;
    }
}
