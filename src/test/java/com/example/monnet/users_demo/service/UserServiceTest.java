package com.example.monnet.users_demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.monnet.users_demo.api.dto.UserResponse;
import com.example.monnet.users_demo.domain.User;
import com.example.monnet.users_demo.mapper.UserMapper;
import com.example.monnet.users_demo.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    @Test
    void getUsers_returnsMappedResponses_whenRepositoryHasUsers() {
        List<User> entities = List.of(
                User.builder().id(1L).name("Alice").build(),
                User.builder().id(2L).name("Bob").build()
        );
        List<UserResponse> expected = List.of(
                new UserResponse(1L, "Alice", null, null, null, null, null, null),
                new UserResponse(2L, "Bob", null, null, null, null, null, null)
        );
        when(userRepository.findAll()).thenReturn(entities);
        when(userMapper.toResponseList(entities)).thenReturn(expected);

        List<UserResponse> result = userService.getUsers();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getUsers_returnsEmpty_whenRepositoryIsEmpty() {
        when(userRepository.findAll()).thenReturn(List.of());
        when(userMapper.toResponseList(List.of())).thenReturn(List.of());

        List<UserResponse> result = userService.getUsers();

        assertThat(result).isEmpty();
    }
}
