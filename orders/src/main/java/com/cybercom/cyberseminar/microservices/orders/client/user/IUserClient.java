package com.cybercom.cyberseminar.microservices.orders.client.user;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "users",
             decode404 = true)
public interface IUserClient {

    @RequestMapping(value = "/user/{userId}",
                    method = RequestMethod.GET)
    ResponseEntity<User> find(@PathVariable("userId") long id);

}
