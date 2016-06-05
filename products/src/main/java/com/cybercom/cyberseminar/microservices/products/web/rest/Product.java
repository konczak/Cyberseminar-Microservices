package com.cybercom.cyberseminar.microservices.products.web.rest;

import java.util.concurrent.atomic.AtomicLong;

import lombok.Getter;

public class Product {

    private static final AtomicLong idSequence = new AtomicLong(1);

    @Getter
    private final Long id;

    @Getter
    private final String title;

    public Product(String title) {
        this.id = idSequence.getAndIncrement();
        this.title = title;
    }

}
