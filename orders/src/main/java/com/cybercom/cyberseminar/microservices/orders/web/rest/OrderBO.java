package com.cybercom.cyberseminar.microservices.orders.web.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cybercom.cyberseminar.microservices.orders.client.product.IProductClient;
import com.cybercom.cyberseminar.microservices.orders.client.product.Product;
import com.cybercom.cyberseminar.microservices.orders.client.user.IUserClient;
import com.cybercom.cyberseminar.microservices.orders.client.user.User;

@Service
class OrderBO {

    private final IUserClient userClient;

    private final IProductClient productClient;

    private final List<Order> orders;

    @Autowired
    OrderBO(IUserClient userClient, IProductClient productClient) {
        this.userClient = userClient;
        this.productClient = productClient;
        this.orders = new ArrayList<>();
    }

    Order add(OrderNew orderNew) {
        ResponseEntity<User> user = userClient.find(orderNew.getUserId());
        ResponseEntity<Product> product = productClient.find(orderNew.getProductId());

        if (!user.hasBody()) {
            throw new RuntimeException("User not exists");
        }
        if (!product.hasBody()) {
            throw new RuntimeException("Product not exists");
        }

        Order order = new Order(user.getBody(), product.getBody(), orderNew.getQuantity());

        orders.add(order);

        return order;
    }

    List<Order> list() {
        return Collections.unmodifiableList(orders);
    }

    Optional<Order> get(UUID id) {
        return orders.stream()
                .filter(user -> user.getId().toString().equals(id.toString()))
                .findFirst();
    }

}
