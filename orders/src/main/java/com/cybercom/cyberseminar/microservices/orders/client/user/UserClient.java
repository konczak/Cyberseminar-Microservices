package com.cybercom.cyberseminar.microservices.orders.client.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class UserClient
        implements IUserClient {

    private final RestTemplate restTemplate;

    private final String userUrl;

    @Autowired
    UserClient(RestTemplate restTemplate,
            @Value("${client.users.url}") String clientsUrl) {
        this.restTemplate = restTemplate;
        this.userUrl = clientsUrl + "/user/{userId}";
    }

    @Override
    public Optional<User> find(long id) {
//        Map<String, String> vars = new HashMap<>();
//        vars.put("userId", Long.toString(id));
        HttpEntity<User> response = restTemplate
                .getForEntity(userUrl, User.class, id);

        User user = response.getBody();

        return Optional.of(user);
    }
}
