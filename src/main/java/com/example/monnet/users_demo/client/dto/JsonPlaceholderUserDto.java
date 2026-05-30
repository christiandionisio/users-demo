package com.example.monnet.users_demo.client.dto;

public record JsonPlaceholderUserDto(
        Long id,
        String name,
        String username,
        String email,
        String phone,
        String website,
        AddressDto address,
        CompanyDto company
) {
}
