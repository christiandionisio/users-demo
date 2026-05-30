package com.example.monnet.users_demo.mapper;

import com.example.monnet.users_demo.api.dto.AddressResponse;
import com.example.monnet.users_demo.api.dto.CompanyResponse;
import com.example.monnet.users_demo.api.dto.UserResponse;
import com.example.monnet.users_demo.domain.Address;
import com.example.monnet.users_demo.domain.Company;
import com.example.monnet.users_demo.domain.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);

    AddressResponse toAddressResponse(Address address);

    CompanyResponse toCompanyResponse(Company company);

    List<UserResponse> toResponseList(List<User> users);
}
