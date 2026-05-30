package com.example.monnet.users_demo.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.monnet.users_demo.api.dto.AddressResponse;
import com.example.monnet.users_demo.api.dto.CompanyResponse;
import com.example.monnet.users_demo.api.dto.UserResponse;
import com.example.monnet.users_demo.security.JwtService;
import com.example.monnet.users_demo.service.UserService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    @MockitoBean
    JwtService jwtService;

    @Test
    void getUsersWithoutAuth() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void getUsersWithMockUser() throws Exception {
        UserResponse u = new UserResponse(
                1L, "Alice", "alice", "alice@example.com", "555-1", "alice.dev",
                new AddressResponse(10L, "Main", "1A", "Springfield", "00000", "0.0", "0.0"),
                new CompanyResponse(20L, "Acme", "We make stuff", "stuff")
        );
        when(userService.getUsers()).thenReturn(List.of(u));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[0].address.city").value("Springfield"))
                .andExpect(jsonPath("$[0].company.name").value("Acme"));
    }
}
