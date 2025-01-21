package com.example.isaProject.controller;

import com.example.isaProject.dto.ApplicationAnalyticsDTO;
import com.example.isaProject.dto.CommentDto;
import com.example.isaProject.dto.FollowDto;
import com.example.isaProject.model.Comment;
import com.example.isaProject.model.Follow;
import com.example.isaProject.model.User;
import com.example.isaProject.securityAuth.TokenBasedAuthentication;
import com.example.isaProject.service.CommentService;
import com.example.isaProject.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/comments", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, Principal principal){
        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
        Comment comment = commentService.create(commentDto, loggedUser);
        if(comment == null){
            return new ResponseEntity<CommentDto>(HttpStatus.BAD_REQUEST);
        }
        CommentDto dto = new CommentDto(comment);
        return new ResponseEntity<CommentDto>(dto, HttpStatus.OK);
    }
//domaci
    @GetMapping("ApplicationAnalytics")
    public ResponseEntity<ApplicationAnalyticsDTO>ApplicationAnalytics(){
        ApplicationAnalyticsDTO newAppAnalystics = commentService.ApplicationAnalytics();

        if(newAppAnalystics == null){
            return new ResponseEntity<ApplicationAnalyticsDTO>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ApplicationAnalyticsDTO>(newAppAnalystics, HttpStatus.OK);
    }
}


