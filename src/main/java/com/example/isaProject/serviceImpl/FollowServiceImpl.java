package com.example.isaProject.serviceImpl;

import com.example.isaProject.dto.FollowDto;
import com.example.isaProject.model.Follow;
import com.example.isaProject.model.User;
import com.example.isaProject.repository.FollowRepository;
import com.example.isaProject.repository.MessageRepository;
import com.example.isaProject.repository.UserRepository;
import com.example.isaProject.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    //domaci
    public Follow createFollow(User loggedUser, Long followeeId) {

        User user = userRepository.findById(loggedUser.getId()).orElse(null);
        if (user == null) {
            return null;
        }

        User followee = userRepository.findById(followeeId).orElse(null);
        if (followee == null) {
            return null;
        }

        Follow existingFollow = followRepository.findByFollowerIdAndFolloweeId(user.getId(), followeeId);
        if (existingFollow != null) {
            return null; // Already following
        }

        LocalDateTime oneMinuteAgo = LocalDateTime.now().minusMinutes(1);
        long followCount = followRepository.countFollowsInLastMinute(user.getId(), oneMinuteAgo);

        if (followCount >= 50) {
            throw new RuntimeException("You can only follow up to 50 users per minute.");
        }

        Follow follow = new Follow();
        follow.setFollower(user);
        follow.setFollowee(followee);
        follow.setFollowCreation(LocalDateTime.now());

        user.setNumberOfFollowees(user.getNumberOfFollowees() + 1);
        followee.setNumberOfFollowers(followee.getNumberOfFollowers() + 1);
        userRepository.save(user);
        userRepository.save(followee);

        followRepository.save(follow);
        return follow;
    }



    @Override
    public void unFollow(User loggedUser, Long followeeId) {
        User user = userRepository.findById(loggedUser.getId()).orElse(null);
        if (user == null) {
            return;
        }

        User followee = userRepository.findById(followeeId).orElse(null);
        if (followee == null) {
            return;
        }
        Follow existingFollow = followRepository.findByFollowerIdAndFolloweeId(user.getId(), followeeId);
        if (existingFollow == null) {
            return;
        }
        user.setNumberOfFollowees(user.getNumberOfFollowees() - 1);
        followee.setNumberOfFollowers(followee.getNumberOfFollowers() - 1);
        userRepository.save(user);
        userRepository.save(followee);


        followRepository.delete(existingFollow);
    }

    @Override
    public List<User> allFollowersOfUser2(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return followRepository.allFollowersOfUser2(userId, pageable);
    }

    @Override
    public List<User> allFollowOfUser2(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return followRepository.allFollowOfUser2(userId, pageable);
    }

    @Override
    public boolean existsByFollowerIdAndFolloweeId(Long followerId, Long followeeId) {
        return followRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId);
    }



}