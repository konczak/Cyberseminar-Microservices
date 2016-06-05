package com.cybercom.cyberseminar.microservices.orders.client.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class ProductClient
        implements IProductClient {

    private final RestTemplate restTemplate;

    private final String productUrl;

    @Autowired
    ProductClient(RestTemplate restTemplate,
            @Value("${client.products.url}") String productsUrl) {
        this.restTemplate = restTemplate;
        this.productUrl = productsUrl + "/product/{productId}";
    }

    @Override
    public Optional<Product> find(long id) {
        HttpEntity<Product> response = restTemplate
                .getForEntity(productUrl, Product.class, id);

        Product product = response.getBody();

        return Optional.of(product);
    }

}
