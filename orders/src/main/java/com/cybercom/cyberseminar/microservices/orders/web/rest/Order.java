package com.cybercom.cyberseminar.microservices.orders.web.rest;

import java.util.UUID;

import com.cybercom.cyberseminar.microservices.orders.client.product.Product;
import com.cybercom.cyberseminar.microservices.orders.client.user.User;

import lombok.Getter;

public class Order {

    @Getter
    private final UUID id;

    @Getter
    private final String firstname;

    @Getter
    private final String lastname;

    @Getter
    private final String title;

    @Getter
    private final long quantity;

    public Order(User user, Product product, long quantity) {
        this.id = UUID.randomUUID();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.title = product.getTitle();
        this.quantity = quantity;
    }

}
