package com.example.isaProject.service;

import com.example.isaProject.dto.FollowDto;
import com.example.isaProject.model.Follow;
import com.example.isaProject.model.User;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface FollowService {

    public Follow createFollow(User loggedUser, Long followeeId);

    public void unFollow(User loggedUser, Long followeeId);


}
