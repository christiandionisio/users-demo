package com.example.monnet.users_demo.client;

import com.example.monnet.users_demo.client.dto.JsonPlaceholderUserDto;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class JsonPlaceholderClient {

    private final RestClient restClient;

    public JsonPlaceholderClient(@Value("${app.jsonplaceholder.base-url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public List<JsonPlaceholderUserDto> fetchUsers() {
        JsonPlaceholderUserDto[] users = restClient.get()
                .uri("/users")
                .retrieve()
                .body(JsonPlaceholderUserDto[].class);
        return users == null ? List.of() : Arrays.asList(users);
    }
}
