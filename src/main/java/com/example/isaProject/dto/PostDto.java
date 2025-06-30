package com.example.isaProject.dto;

import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;

    private String description;

    private String createdAt;

    private String username;

    private MultipartFile bunnyImage;

    private int likes;

    private Long userId;



    public PostDto(Post post) {
        this.id= post.getId();
        this.description= post.getDescription();
        this.createdAt = post.getCreatedAt().toString();
        this.username = post.getUser().getUsername();
        this.likes = post.getLikes();
        this.userId = post.getUser().getId();
    }

    public Post mapTo() {
        Post post = new Post();
        post.setDescription(description);
        return post;
    }

    public static List<PostDto> convertPostsToPostDtos(List<Post> posts) {
        return posts.stream()
                .map(PostDto::new)
                .collect(Collectors.toList());
        /*
            isto kao ovo
            List<PostDto> postDtos = new ArrayList<PostDto>();
            for(Post post : posts){
                postDtos.add(new PostDto(post));
            }
            return postDtos;

         */
    }


}
