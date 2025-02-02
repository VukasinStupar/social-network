package com.example.isaProject.controller;

import com.example.isaProject.dto.FollowDto;
import com.example.isaProject.dto.PostDto;
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

    @PutMapping("removeFollow/{followeeId}")
    public ResponseEntity<?> deleteFollow(@PathVariable Long followeeId, Principal principal){
        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
        followService.unFollow(loggedUser, followeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}