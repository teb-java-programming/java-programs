package com.teb.practice.model.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    CREATED("created"),
    PROCESSING("processing"),
    COMPLETED("completed");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {

        return value;
    }
}
