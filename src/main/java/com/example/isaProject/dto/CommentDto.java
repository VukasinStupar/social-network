package com.example.isaProject.dto;

import com.example.isaProject.model.Comment;
import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;

    private Long userId;

    private Long postId;

    private String text;

    private LocalDateTime commentCreation;

    public CommentDto(Comment comment){
        this.id= comment.getId();
        this.userId= comment.getUser().getId();
        this.postId= comment.getPost().getId();
        this.text= comment.getText();
        this.commentCreation= comment.getCommentCreation();
    }
}
