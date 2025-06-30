package com.example.isaProject.controller;

import com.example.isaProject.dto.*;
import com.example.isaProject.model.Follow;
import com.example.isaProject.model.User;
import com.example.isaProject.securityAuth.TokenBasedAuthentication;
import com.example.isaProject.service.FollowService;
import com.example.isaProject.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/follows", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class FollowController {
    @Autowired
    private FollowService followService;

    @PostMapping("/{followeeId}")
    public ResponseEntity<FollowDto> createFollow(@PathVariable Long followeeId, Principal principal){
        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
        Follow follow = followService.createFollow(loggedUser, followeeId);
        if(follow == null){
            return new ResponseEntity<FollowDto>(HttpStatus.BAD_REQUEST);
        }
        FollowDto dto = new FollowDto(follow);
        return new ResponseEntity<FollowDto>(dto, HttpStatus.OK);
    }

    @PutMapping("/removeFollow/{followeeId}")
    public ResponseEntity<?> deleteFollow(@PathVariable Long followeeId, Principal principal){
        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
        followService.unFollow(loggedUser, followeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //zavrsti

    @GetMapping("/folowers")
    public ResponseEntity<List<UserProfileDto>> followers(@RequestParam Long userId,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "30") int size) {

        List<User> followers = followService.allFollowersOfUser2(userId, page, size);
        List<UserProfileDto> followersDto = new ArrayList<>();
        for(User iter : followers){
            followersDto.add(new UserProfileDto(iter));
        }
        return new ResponseEntity<List<UserProfileDto>>(followersDto, HttpStatus.OK);
    }

    @GetMapping("/folowee")
    public ResponseEntity<List<UserProfileDto>> folowee(@RequestParam Long userId,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "30") int size) {

        List<User> followers = followService.allFollowOfUser2(userId, page, size);
        List<UserProfileDto> followersDto = new ArrayList<>();
        for(User iter : followers){
            followersDto.add(new UserProfileDto(iter));
        }
        return new ResponseEntity<List<UserProfileDto>>(followersDto, HttpStatus.OK);
    }

    @GetMapping("/isFollowing/{followeeId}")
    public ResponseEntity<FollowingResponse> isFollowing(@PathVariable Long followeeId, Principal principal) {
        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
        boolean follows = followService.existsByFollowerIdAndFolloweeId(loggedUser.getId(), followeeId);

        FollowingResponse followingResponse = new FollowingResponse(follows);

        return new ResponseEntity<FollowingResponse>(followingResponse, HttpStatus.OK);
    }

}