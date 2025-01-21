package com.example.isaProject.service;

import com.example.isaProject.dto.UserDisplayDto;
import com.example.isaProject.dto.UserRequest;
import com.example.isaProject.dto.UserSearchDto;
import com.example.isaProject.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    User findByUsername(String name);

    List<User> findAll();

    User findById(Long userId);

    User save(UserRequest userRequest);

    User save(User user);

     Long getPostNumber7Days(Long userId);

    User setLastLogin(Long userId);

    void sendNotificationsToInactiveUsers();


    List<UserDisplayDto> findUsersByAttributes(UserSearchDto userSearchDto);

    boolean activateUser(String token);

}
