package com.example.isaProject.service;

import com.example.isaProject.dto.UserRequest;
import com.example.isaProject.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    User findByUsername(String name);

    List<User> findAll();

    User findById(Long userId);

    User save(UserRequest userRequest);

     Long getPostNumber7Days(Long userId);



}
