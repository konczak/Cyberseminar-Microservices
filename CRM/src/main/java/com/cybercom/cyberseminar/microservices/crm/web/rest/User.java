package com.cybercom.cyberseminar.microservices.crm.web.rest;

import java.util.concurrent.atomic.AtomicLong;

import lombok.Getter;

public class User {

    private static final AtomicLong idSequence = new AtomicLong(1);

    @Getter
    private final long id;

    @Getter
    private final String firstname;

    @Getter
    private final String lastname;

    public User(String firstname, String lastname) {
        this.id = idSequence.getAndIncrement();
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
