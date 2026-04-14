package com.teb.practice.integration;

import static com.teb.practice.util.DataFactoryUtil.createCustomer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.teb.practice.model.customer.Customer;
import com.teb.practice.util.JsonUtil;

import org.junit.jupiter.api.Test;

import tools.jackson.databind.JsonNode;

class JacksonIntegrationTest {

    private final JsonUtil jsonUtil = new JsonUtil();

    @Test
    void testSerialisesAndDeserialisesFullCustomerGraph() {

        // Create full object graph
        Customer original = createCustomer();

        // Serialise
        String json = jsonUtil.toJson(original);
        JsonNode node = jsonUtil.objectMapper().readTree(json);

        // Basic structure validation
        assertEquals(original.getId(), node.get("id").asLong());
        assertEquals("Sean", node.get("name").asString());
        assertEquals("jb007@email.com", node.get("email").asString());

        // Address flattened using @JsonUnwrapped
        assertEquals("Edinburgh", node.get("city").asString());
        assertEquals("Scotland", node.get("country").asString());

        // Collections exist
        assertTrue(node.get("orders").isArray());
        assertTrue(node.get("paymentTypes").isArray());

        // Sensitive field excluded using @JsonIgnore
        assertNull(node.get("password"));

        // Deserialise back
        Customer restored = jsonUtil.fromJson(json, Customer.class);

        // Validate object integrity
        assertEquals(original.getId(), restored.getId());
        assertEquals(original.getName(), restored.getName());
        assertEquals(original.getEmail(), restored.getEmail());

        // Address integrity
        assertEquals(original.getAddress().city(), restored.getAddress().city());
        assertEquals(original.getAddress().country(), restored.getAddress().country());

        // Orders integrity
        assertEquals(original.getOrders().size(), restored.getOrders().size());

        for (int i = 0; i < restored.getOrders().size(); i++) {
            assertEquals(original.getOrders().get(i).getId(), restored.getOrders().get(i).getId());
            assertEquals(
                    original.getOrders().get(i).getStatus(),
                    restored.getOrders().get(i).getStatus());
        }

        // Payments integrity
        assertEquals(original.getPaymentTypes().size(), restored.getPaymentTypes().size());
    }
}
