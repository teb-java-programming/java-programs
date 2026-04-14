package com.teb.practice.model.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Address(String city, String country) {

    @JsonCreator
    public Address(@JsonProperty("city") String city, @JsonProperty("country") String country) {
        this.city = city;
        this.country = country;
    }
}
