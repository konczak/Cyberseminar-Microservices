package com.cybercom.cyberseminar.microservices.orders.client.user;

import java.util.Optional;

public interface IUserClient {

    Optional<User> find(long id);

}
