package com.teb.practice.model.customer;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.teb.practice.model.order.Order;
import com.teb.practice.model.payment.Payment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(NON_NULL)
@Getter
public class Customer {

    private final long id;
    private final String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate dateOfBirth;

    @JsonAlias({"mail", "emailAddress"})
    private final String email;

    private final String password;
    private final Map<String, Object> metadata = new HashMap<>();

    @JsonUnwrapped private final Address address;
    @JsonManagedReference private final List<Order> orders;
    @Setter private List<Payment> paymentTypes;

    @JsonCreator
    public Customer(
            @JsonProperty("id") long id,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("address") Address address,
            @JsonProperty("dateOfBirth") LocalDate dateOfBirth,
            @JsonProperty("orders") List<Order> orders) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.orders = orders;
    }

    @JsonIgnore
    public String getPassword() {

        return password;
    }

    @JsonAnyGetter
    public Map<String, Object> getMetadata() {

        return metadata;
    }
}
