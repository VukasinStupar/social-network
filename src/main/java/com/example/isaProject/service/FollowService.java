package com.example.isaProject.service;

import com.example.isaProject.dto.FollowDto;
import com.example.isaProject.model.Follow;
import com.example.isaProject.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FollowService {

     Follow createFollow(User loggedUser, Long followeeId);

     void unFollow(User loggedUser, Long followeeId);

    List<User> allFollowersOfUser2(Long userId, int page, int size);

    List<User> allFollowOfUser2(Long userId, int page, int size);

    boolean existsByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
}
