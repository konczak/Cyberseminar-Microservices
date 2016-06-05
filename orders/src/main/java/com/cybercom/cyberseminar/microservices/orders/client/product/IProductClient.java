package com.cybercom.cyberseminar.microservices.orders.client.product;

import java.util.Optional;

public interface IProductClient {

    Optional<Product> find(long id);
}
