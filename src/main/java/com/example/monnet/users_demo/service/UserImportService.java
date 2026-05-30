package com.example.monnet.users_demo.service;

import com.example.monnet.users_demo.client.JsonPlaceholderClient;
import com.example.monnet.users_demo.client.dto.JsonPlaceholderUserDto;
import com.example.monnet.users_demo.domain.User;
import com.example.monnet.users_demo.mapper.JsonPlaceholderUserMapper;
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
    private final JsonPlaceholderUserMapper mapper;

    @Transactional
    public void importIfEmpty() {
        long existing = userRepository.count();
        if (existing > 0) {
            log.info("Skipping import: users table already has {} rows", existing);
            return;
        }
        List<JsonPlaceholderUserDto> dtos = client.fetchUsers();
        List<User> users = dtos.stream().map(mapper::toEntity).toList();
        userRepository.saveAll(users);
        log.info("Imported {} users from jsonplaceholder", users.size());
    }
}
