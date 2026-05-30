package com.example.monnet.users_demo.api.dto;

public record UserResponse(
        Long id,
        String name,
        String username,
        String email,
        String phone,
        String website,
        AddressResponse address,
        CompanyResponse company
) {
}
