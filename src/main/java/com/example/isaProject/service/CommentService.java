package com.example.isaProject.service;

import com.example.isaProject.dto.CommentDto;
import com.example.isaProject.model.Comment;
import com.example.isaProject.model.User;
import org.springframework.data.repository.query.Param;

public interface CommentService {
    public Comment create(CommentDto commentDto, User loggedUser);


}
