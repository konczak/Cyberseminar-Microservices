package com.cybercom.cyberseminar.microservices.orders.web.rest;

import lombok.Getter;
import lombok.Setter;

public class OrderNew {

    @Getter
    @Setter
    private long userId;

    @Getter
    @Setter
    private long productId;

    @Getter
    @Setter
    private long quantity;

}
