package com.example.isaProject.dto;

import com.example.isaProject.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostDetailsDto {

    private Long id;
    private String description;
    private String bunnyImage;
    private int likes;
    private List<CommentDto> comments;
    private LocalDateTime createdAt;
    private String username;


    public PostDetailsDto(Post post, List<CommentDto> commentsDto) {
        this.id = post.getId();
        this.description = post.getDescription();
        this.createdAt = post.getCreatedAt();
        this.username = post.getUser().getUsername();
        this.bunnyImage = post.getBunnyImage();
        this.likes = post.getLikes();
        this.comments = commentsDto;
    }

}