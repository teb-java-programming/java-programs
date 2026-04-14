package com.teb.practice.serialisation;

import static com.teb.practice.model.order.OrderStatus.COMPLETED;
import static com.teb.practice.util.DataFactoryUtil.createCustomer;
import static com.teb.practice.util.DataFactoryUtil.createOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.teb.practice.model.customer.Customer;
import com.teb.practice.model.order.Order;
import com.teb.practice.util.JsonUtil;

import org.junit.jupiter.api.Test;

import tools.jackson.databind.JsonNode;

class OrderTest {

    private final JsonUtil jsonUtil = new JsonUtil();

    @Test
    void testSerialisesOrder() {

        Customer customer = createCustomer();
        Order order = createOrder(customer);
        String json = jsonUtil.toJson(order);
        JsonNode node = jsonUtil.objectMapper().readTree(json);

        assertEquals(order.getId(), node.get("id").asLong());
        assertEquals(order.getAmount(), node.get("amount").asDouble());
        assertEquals("created", node.get("status").asString());

        // Handle custom LocalDateTime
        assertTrue(node.get("orderedOn").asString().contains("-"));

        // Orders should not serialise due to @JsonBackReference
        assertNull(node.get("customer"));
    }

    @Test
    void testDeserialisesOrder() {

        String json =
                """
                {
                  "id": 1776184082332,
                  "amount": 640.0,
                  "status": "completed",
                  "orderedOn": "08-04-2026 13:55",
                  "customer": null
                }
                """;

        Order order = jsonUtil.fromJson(json, Order.class);

        assertEquals(1776184082332L, order.getId());
        assertEquals(640.0, order.getAmount());
        assertEquals(COMPLETED, order.getStatus());
        assertNotNull(order.getOrderedOn());
    }
}
