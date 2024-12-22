package com.example.isaProject.controller;

import com.example.isaProject.dto.LikeDto;
import com.example.isaProject.dto.PostDto;
import com.example.isaProject.model.Like;
import com.example.isaProject.model.User;
import com.example.isaProject.securityAuth.TokenBasedAuthentication;
import com.example.isaProject.service.LikeService;
import com.example.isaProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/likes", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping
    public ResponseEntity<LikeDto> create(@RequestBody LikeDto likeDto, Principal principal){

        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
        Like like = likeService.like(likeDto, loggedUser);
        if(like == null){
            return new ResponseEntity<LikeDto>(HttpStatus.BAD_REQUEST);
        }
        LikeDto dto = new LikeDto(like);

        return new ResponseEntity<LikeDto>(dto, HttpStatus.OK);
    }

    @PostMapping("/removeLike")
    public ResponseEntity<LikeDto> removeLike(@RequestBody LikeDto likeDto, Principal principal){
        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
        Like like = likeService.removeLike(likeDto, loggedUser);
        if(like == null){
            return new ResponseEntity<LikeDto>(HttpStatus.BAD_REQUEST);
        }
        LikeDto dto = new LikeDto(like);

        return new ResponseEntity<LikeDto>(dto, HttpStatus.OK);
    }
}
