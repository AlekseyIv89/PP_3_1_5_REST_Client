package ru.ivanov.rest.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.ivanov.rest.entity.User;

import java.util.List;

@Component
public class Communication {
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public Communication(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET,
                new HttpEntity<>(httpHeaders), new ParameterizedTypeReference<>() { });
        httpHeaders.set("Cookie" , String.join("", responseEntity.getHeaders().get("Set-Cookie")));
        return responseEntity.getBody();
    }

    public String saveUser(User user) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST,
                new HttpEntity<>(user, httpHeaders), String.class);
        return responseEntity.getBody();
    }

    public String updateUser(User user) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT,
                new HttpEntity<>(user, httpHeaders), String.class);
        return responseEntity.getBody();
    }

    public String deleteUser(Long id) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE,
                new HttpEntity<>(3L, httpHeaders), String.class);
        return responseEntity.getBody();
    }
}
