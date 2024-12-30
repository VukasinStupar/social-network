package com.example.isaProject.dto;

import com.example.isaProject.model.Comment;
import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public static List<CommentDto> convertListToDto(List<Comment> comments){
        List<CommentDto> dtos = new ArrayList<>();
        for(Comment comment : comments){
            dtos.add(new CommentDto(comment));
        }
        return dtos;
    }



    public Comment mapTo(){
        Comment comment = new Comment();
        comment.getUser().setId(userId);
        comment.getPost().setId(postId);
        comment.setText(text);
        return comment;
    }
}