package com.example.monnet.users_demo.api.dto;

public record LoginResponse(
        String token,
        long expiresInMinutes
) {
}
