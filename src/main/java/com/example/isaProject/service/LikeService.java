package com.example.isaProject.service;

import com.example.isaProject.dto.LikeDto;
import com.example.isaProject.model.Like;
import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;

import java.util.List;

public interface LikeService {

    public Like like(LikeDto likeDto, User loggedUser);

    public Like removeLike(LikeDto likeDto, User loggedUser);
}
