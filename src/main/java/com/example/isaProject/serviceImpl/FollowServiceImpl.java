package com.example.isaProject.serviceImpl;

import com.example.isaProject.dto.FollowDto;
import com.example.isaProject.model.Follow;
import com.example.isaProject.model.User;
import com.example.isaProject.repository.FollowRepository;
import com.example.isaProject.repository.MessageRepository;
import com.example.isaProject.repository.UserRepository;
import com.example.isaProject.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    //domaci
    public Follow createFollow(User loggedUser, Long followeeId) {
        // Check if the user exists
        User user = userRepository.findById(loggedUser.getId()).orElse(null);
        if (user == null) {
            return null;
        }

        // Check if the followee exists
        User followee = userRepository.findById(followeeId).orElse(null);
        if (followee == null) {
            return null;
        }

        // Check if the user is already following this followee
        if (followRepository.existsByFollowerIdAndFolloweeId(user.getId(), followeeId)) {
            return null; // Return null if already following
        }

        // domaci
        LocalDateTime oneMinuteAgo = LocalDateTime.now().minusMinutes(1);
        long followCount = followRepository.countFollowsInLastMinute(user.getId(), oneMinuteAgo);

        // domaci
        if (followCount >= 50) {
            throw new RuntimeException("You can only follow up to 50 users per minute.");
        }

        // Create and save the new follow
        Follow follow = new Follow();
        follow.setFollower(user);
        follow.setFollowee(followee);
        follow.setFollowCreation(LocalDateTime.now()); // Set the follow creation time

        followRepository.save(follow);
        return follow;
    }
    //domaci


    @Override
    public void unFollow(User loggedUser, Long followeeId) {
        User user = userRepository.findById(loggedUser.getId()).orElse(null);
        if (user == null) {
            return ;
        }

        User followee = userRepository.findById(followeeId).orElse(null);
        if (followee == null) {
            return ;
        }
        //ispravio sam ovu funkciju
        if (!followRepository.existsByFollowerIdAndFolloweeId(user.getId(), followeeId)) {
            return ;
        }
        followRepository.deleteByFollowerIdAndFolloweeId(user.getId(), followeeId);
    }



}
