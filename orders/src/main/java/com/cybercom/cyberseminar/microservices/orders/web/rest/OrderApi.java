package com.cybercom.cyberseminar.microservices.orders.web.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
class OrderApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderApi.class);

    private final OrderBO orderBO;

    @Autowired
    OrderApi(OrderBO orderBO) {
        this.orderBO = orderBO;
    }

    @RequestMapping(method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    List<Order> list() {
        LOGGER.info("List Orders");

        return orderBO.list();
    }

    @RequestMapping(value = "/{orderId}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Order> get(@PathVariable("orderId") UUID orderId) {
        LOGGER.info("Get Order by ID <{}>", orderId);

        Optional<Order> optional = orderBO.get(orderId);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Order> add(@RequestBody OrderNew orderNew) {
        LOGGER.info("Add Order <{}> <{}> <{}>",
                orderNew.getUserId(),
                orderNew.getProductId(),
                orderNew.getQuantity());

        Order order = orderBO.add(orderNew);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
