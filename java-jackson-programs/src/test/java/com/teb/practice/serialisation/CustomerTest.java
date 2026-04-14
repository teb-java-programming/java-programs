package com.teb.practice.serialisation;

import static com.teb.practice.util.DataFactoryUtil.createCustomer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.teb.practice.model.customer.Customer;
import com.teb.practice.util.JsonUtil;

import org.junit.jupiter.api.Test;

import tools.jackson.databind.JsonNode;

class CustomerTest {

    private final JsonUtil jsonUtil = new JsonUtil();

    @Test
    void testSerialisesCustomer() {

        Customer customer = createCustomer();
        String json = jsonUtil.toJson(customer);
        JsonNode node = jsonUtil.objectMapper().readTree(json);

        assertEquals(customer.getId(), node.get("id").asLong());
        assertEquals("Sean", node.get("name").asString());
        assertEquals("jb007@email.com", node.get("email").asString());

        // Password hidden using @JsonIgnore
        assertNull(node.get("password"));

        // Address flattened using @JsonUnwrapped
        assertEquals("Edinburgh", node.get("city").asString());
        assertEquals("Scotland", node.get("country").asString());

        assertFalse(node.has("metadata"));
        assertTrue(node.get("orders").isArray());
    }

    @Test
    void testDeserialisesCustomer() {

        String json =
                """
                {
                  "id": 1776184082332,
                  "name": "Roger",
                  "email": "octo83yssup@email.com",
                  "password": "700_yps",
                  "city": "London",
                  "country": "England",
                  "dateOfBirth": "14-10-1927",
                  "orders": []
                }
                """;

        Customer customer = jsonUtil.fromJson(json, Customer.class);

        assertEquals(1776184082332L, customer.getId());
        assertEquals("Roger", customer.getName());
        assertEquals("octo83yssup@email.com", customer.getEmail());
        assertEquals("700_yps", customer.getPassword());

        assertEquals("London", customer.getAddress().city());
        assertEquals("England", customer.getAddress().country());
    }
}
