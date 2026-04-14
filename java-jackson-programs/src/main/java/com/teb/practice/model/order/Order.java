package com.teb.practice.model.order;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teb.practice.model.customer.Customer;

import lombok.Getter;

import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

@JsonInclude(NON_NULL)
@Getter
public class Order {

    private final long id;
    private final double amount;
    private final OrderStatus status;

    @JsonSerialize(using = com.teb.practice.serialisation.LocalDateTimeSerializer.class)
    @JsonDeserialize(using = com.teb.practice.serialisation.LocalDateTimeDeserializer.class)
    private final LocalDateTime orderedOn;

    @JsonBackReference private final Customer customer;

    @JsonCreator
    public Order(
            @JsonProperty("id") long id,
            @JsonProperty("amount") double amount,
            @JsonProperty("status") OrderStatus status,
            @JsonProperty("orderedOn") LocalDateTime orderedOn,
            @JsonProperty("customer") Customer customer) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.orderedOn = orderedOn;
        this.customer = customer;
    }
}
