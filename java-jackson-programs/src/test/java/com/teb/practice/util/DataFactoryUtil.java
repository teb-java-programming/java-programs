package com.teb.practice.util;

import static com.teb.practice.model.order.OrderStatus.COMPLETED;
import static com.teb.practice.model.order.OrderStatus.CREATED;

import static java.lang.System.currentTimeMillis;
import static java.time.LocalDateTime.now;

import com.teb.practice.model.customer.Address;
import com.teb.practice.model.customer.Customer;
import com.teb.practice.model.order.Order;
import com.teb.practice.model.payment.CardPayment;
import com.teb.practice.model.payment.DirectPayment;
import com.teb.practice.model.payment.Payment;

import java.time.LocalDate;
import java.util.List;

public class DataFactoryUtil {

    public static Customer createCustomer() {

        Order firstOrder = new Order(currentTimeMillis(), 100.0, CREATED, now(), null);
        Order secondOrder = new Order(currentTimeMillis(), 250.0, COMPLETED, now(), null);

        Customer customer =
                new Customer(
                        currentTimeMillis(),
                        "Sean",
                        "jb007@email.com",
                        "dr30No",
                        new Address("Edinburgh", "Scotland"),
                        LocalDate.of(1930, 8, 25),
                        List.of(firstOrder, secondOrder));

        customer.setPaymentTypes(createPayments(customer.getId()));

        return customer;
    }

    public static Order createOrder(Customer customer) {

        return new Order(currentTimeMillis(), 100.0, CREATED, now(), customer);
    }

    public static List<Payment> createPayments(Long id) {

        return List.of(createDirectPayment(id), createCardPayment(id));
    }

    public static CardPayment createCardPayment(Long id) {

        return new CardPayment(currentTimeMillis() + 1, 200.0, id, "1234-5678-9876-0000");
    }

    public static DirectPayment createDirectPayment(Long id) {

        return new DirectPayment(currentTimeMillis(), 20.0, id, "00-11-22", "12345678");
    }
}
