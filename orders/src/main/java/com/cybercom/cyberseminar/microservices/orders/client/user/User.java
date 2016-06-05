package com.cybercom.cyberseminar.microservices.orders.client.user;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

}
