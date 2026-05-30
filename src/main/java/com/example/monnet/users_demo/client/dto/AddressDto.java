package com.example.monnet.users_demo.client.dto;

public record AddressDto(
        String street,
        String suite,
        String city,
        String zipcode,
        GeoDto geo
) {
}
