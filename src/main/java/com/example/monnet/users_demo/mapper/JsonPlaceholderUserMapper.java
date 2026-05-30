package com.example.monnet.users_demo.mapper;

import com.example.monnet.users_demo.client.dto.AddressDto;
import com.example.monnet.users_demo.client.dto.CompanyDto;
import com.example.monnet.users_demo.client.dto.JsonPlaceholderUserDto;
import com.example.monnet.users_demo.domain.Address;
import com.example.monnet.users_demo.domain.Company;
import com.example.monnet.users_demo.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JsonPlaceholderUserMapper {

    User toEntity(JsonPlaceholderUserDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "geoLat", source = "geo.lat")
    @Mapping(target = "geoLng", source = "geo.lng")
    Address toAddress(AddressDto dto);

    @Mapping(target = "id", ignore = true)
    Company toCompany(CompanyDto dto);
}
