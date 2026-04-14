package com.teb.practice.model.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("direct")
public class DirectPayment extends Payment {

    @JsonIgnore private final String sortCode;
    @JsonIgnore private final String accountNumber;

    @JsonCreator
    public DirectPayment(
            @JsonProperty("id") long id,
            @JsonProperty("amount") double amount,
            @JsonProperty("customerId") long customerId,
            @JsonProperty("sortCode") String sortCode,
            @JsonProperty("accountNumber") String accountNumber) {
        super(id, amount, customerId);
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
    }
}
