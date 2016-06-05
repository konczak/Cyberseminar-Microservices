package com.cybercom.cyberseminar.microservices.crm.web.rest;

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
@RequestMapping("/user")
class UserApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);

    private final List<User> users;

    UserApi() {
        this.users = new ArrayList<>(3);
        this.users.add(new User("jan", "kowalski"));
        this.users.add(new User("kunegunda", "nowak"));
        this.users.add(new User("ferdynand", "kiepski"));
    }

    @RequestMapping(method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    List<User> list() {
        LOGGER.info("List Users");

        return Collections.unmodifiableList(users);
    }

    @RequestMapping(value = "/{userId}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<User> get(@PathVariable("userId") long userId) {
        LOGGER.info("Get User by ID <{}>", userId);

        Optional<User> optional = users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst();

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<User> add(@RequestBody UserNew userNew) {
        LOGGER.info("Add User <{}> <{}>",
                userNew.getFirstname(), userNew.getLastname());

        User user = new User(userNew.getFirstname(), userNew.getLastname());

        users.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
