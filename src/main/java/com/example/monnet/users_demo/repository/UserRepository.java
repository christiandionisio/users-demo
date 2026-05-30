package com.example.monnet.users_demo.repository;

import com.example.monnet.users_demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
