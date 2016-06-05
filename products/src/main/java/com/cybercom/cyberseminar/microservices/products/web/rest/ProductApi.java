package com.cybercom.cyberseminar.microservices.products.web.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/product")
class ProductApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductApi.class);

    private final List<Product> products;

    ProductApi() {
        this.products = new ArrayList<>(5);
        this.products.add(new Product("choinka"));
        this.products.add(new Product("ksiazka"));
        this.products.add(new Product("laptop"));
        this.products.add(new Product("spinacz"));
        this.products.add(new Product("woda"));
    }

    @RequestMapping(method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    List<Product> list() {
        LOGGER.info("List Products");

        return Collections.unmodifiableList(products);
    }

    @RequestMapping(value = "/{productId}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Product> get(@PathVariable("productId") long productId) {
        LOGGER.info("Get Product by ID <{}>", productId);

        Optional<Product> optional = products.stream()
                .filter(user -> user.getId().equals(productId))
                .findFirst();

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Product> add(@RequestBody ProductNew productNew) {
        LOGGER.info("Add Product <{}>", productNew.getTitle());

        Product product = new Product(productNew.getTitle());

        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
