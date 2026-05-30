package com.example.monnet.users_demo.service;

import com.example.monnet.users_demo.api.dto.UserResponse;
import com.example.monnet.users_demo.mapper.UserMapper;
import com.example.monnet.users_demo.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getUsers() {
        return userMapper.toResponseList(userRepository.findAll());
    }
}
