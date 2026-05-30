package com.example.monnet.users_demo.api.dto;

public record AddressResponse(
        Long id,
        String street,
        String suite,
        String city,
        String zipcode,
        String geoLat,
        String geoLng
) {
}
