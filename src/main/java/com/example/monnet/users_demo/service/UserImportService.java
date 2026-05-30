package com.example.monnet.users_demo.service;

import com.example.monnet.users_demo.client.JsonPlaceholderClient;
import com.example.monnet.users_demo.client.dto.JsonPlaceholderUserDto;
import com.example.monnet.users_demo.domain.Address;
import com.example.monnet.users_demo.domain.Company;
import com.example.monnet.users_demo.domain.User;
import com.example.monnet.users_demo.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserImportService {

    private final JsonPlaceholderClient client;
    private final UserRepository userRepository;

    @Transactional
    public void importIfEmpty() {
        long existing = userRepository.count();
        if (existing > 0) {
            log.info("Skipping import: users table already has {} rows", existing);
            return;
        }
        List<JsonPlaceholderUserDto> dtos = client.fetchUsers();
        List<User> users = dtos.stream().map(this::toEntity).toList();
        userRepository.saveAll(users);
        log.info("Imported {} users from jsonplaceholder", users.size());
    }

    private User toEntity(JsonPlaceholderUserDto dto) {
        Address address = Address.builder()
                .street(dto.address().street())
                .suite(dto.address().suite())
                .city(dto.address().city())
                .zipcode(dto.address().zipcode())
                .geoLat(dto.address().geo().lat())
                .geoLng(dto.address().geo().lng())
                .build();
        Company company = Company.builder()
                .name(dto.company().name())
                .catchPhrase(dto.company().catchPhrase())
                .bs(dto.company().bs())
                .build();
        return User.builder()
                .id(dto.id())
                .name(dto.name())
                .username(dto.username())
                .email(dto.email())
                .phone(dto.phone())
                .website(dto.website())
                .address(address)
                .company(company)
                .build();
    }
}
