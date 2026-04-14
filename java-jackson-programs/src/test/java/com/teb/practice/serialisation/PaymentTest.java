package com.teb.practice.serialisation;

import static com.teb.practice.util.DataFactoryUtil.createCardPayment;
import static com.teb.practice.util.DataFactoryUtil.createDirectPayment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

import static java.lang.System.currentTimeMillis;

import com.teb.practice.model.payment.CardPayment;
import com.teb.practice.model.payment.DirectPayment;
import com.teb.practice.model.payment.Payment;
import com.teb.practice.util.JsonUtil;

import org.junit.jupiter.api.Test;

import tools.jackson.databind.JsonNode;

class PaymentTest {

    private final JsonUtil jsonUtil = new JsonUtil();

    @Test
    void testSerialisesCardPayment() {

        CardPayment payment = createCardPayment(currentTimeMillis());
        String json = jsonUtil.toJson(payment);
        JsonNode node = jsonUtil.objectMapper().readTree(json);

        assertEquals("card", node.get("type").asString());
        assertEquals(payment.getId(), node.get("id").asLong());
        assertEquals(payment.getAmount(), node.get("amount").asDouble());
        assertEquals(payment.getCustomerId(), node.get("customerId").asLong());

        // Card number hidden using @JsonIgnore
        assertNull(node.get("cardNumber"));
    }

    @Test
    void testSerialisesDirectPayment() {

        DirectPayment payment = createDirectPayment(currentTimeMillis());
        String json = jsonUtil.toJson(payment);
        JsonNode node = jsonUtil.objectMapper().readTree(json);

        assertEquals("direct", node.get("type").asString());
        assertEquals(payment.getId(), node.get("id").asLong());
        assertEquals(payment.getAmount(), node.get("amount").asDouble());
        assertEquals(payment.getCustomerId(), node.get("customerId").asLong());

        // Sensitive fields hidden using @JsonIgnore
        assertNull(node.get("sortCode"));
        assertNull(node.get("accountNumber"));
    }

    @Test
    void testDeserialisesCardPayment() {

        String json =
                """
                {
                  "type": "card",
                  "id": 1,
                  "amount": 240.0,
                  "customerId": 1776186264272,
                  "cardNumber": "1234-5678-0000-1448"
                }
                """;

        Payment payment = jsonUtil.fromJson(json, Payment.class);

        assertInstanceOf(CardPayment.class, payment);

        assertEquals(1L, payment.getId());
        assertEquals(240.0, payment.getAmount());
        assertEquals(1776186264272L, payment.getCustomerId());
    }

    @Test
    void testDeserialisesDirectPayment() {

        String json =
                """
                {
                  "type": "direct",
                  "id": 2,
                  "amount": 520.0,
                  "customerId": 1776186264272,
                  "sortCode": "00-11-22",
                  "accountNumber": "27145368"
                }
                """;

        Payment payment = jsonUtil.fromJson(json, Payment.class);

        assertInstanceOf(DirectPayment.class, payment);

        assertEquals(2L, payment.getId());
        assertEquals(520.0, payment.getAmount());
        assertEquals(1776186264272L, payment.getCustomerId());
    }
}
