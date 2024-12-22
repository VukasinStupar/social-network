package com.example.isaProject.controller;

import com.example.isaProject.dto.PostDto;
import com.example.isaProject.dto.TrendingDto;
import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;
import com.example.isaProject.securityAuth.TokenBasedAuthentication;
import com.example.isaProject.service.PostService;
import com.example.isaProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class PostController {
    @Autowired
    private PostService postService;


    @PostMapping(consumes = "application/json")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, Principal principal) {
        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();

        Post post = postService.create(postDto, loggedUser);

        if (post == null) {
            return new ResponseEntity<PostDto>(HttpStatus.BAD_REQUEST);
        }
        PostDto dto = new PostDto(post);

        return new ResponseEntity<PostDto>(dto, HttpStatus.OK);

    }
    @GetMapping("displayUserPosts/{userId}")
    public ResponseEntity<ArrayList<PostDto>> displayUserPosts(@PathVariable Long userId){
        List<Post> posts = postService.displayPostByUser(userId);
        if(posts == null){
            return new ResponseEntity<ArrayList<PostDto>>(HttpStatus.BAD_REQUEST);
        }

        ArrayList<PostDto> postDto = new ArrayList<PostDto>();
        for(Post post : posts){
            postDto.add(new PostDto(post));
        }
        return new ResponseEntity<ArrayList<PostDto>>(postDto, HttpStatus.OK);
    }

    @GetMapping("displayAllPostByDesc")
    public ResponseEntity<ArrayList<PostDto>> displayAllPostByDesc(){
        List<Post> posts = postService.displayAllPostsDesc();
        if(posts == null){
            return new ResponseEntity<ArrayList<PostDto>>(HttpStatus.BAD_REQUEST);
        }

        ArrayList<PostDto> postDto = new ArrayList<PostDto>();
        for(Post post : posts){
            postDto.add(new PostDto(post));
        }
        return new ResponseEntity<ArrayList<PostDto>>(postDto, HttpStatus.OK);
    }

    public ResponseEntity<TrendingDto> getTrending(){
        TrendingDto trendingDto = postService.getAppTrending();
        return new ResponseEntity<TrendingDto>(trendingDto, HttpStatus.OK);

    }




}
