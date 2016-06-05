package com.cybercom.cyberseminar.microservices.orders.client.product;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${client.products.url}",
             value = "products",
             decode404 = true)
public interface IProductClient {

    @RequestMapping(value = "/product/{productId}",
                    method = RequestMethod.GET)
    ResponseEntity<Product> find(@PathVariable("productId") long id);
}
