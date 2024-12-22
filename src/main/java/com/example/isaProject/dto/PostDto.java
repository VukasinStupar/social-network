package com.example.isaProject.dto;

import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;
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
public class PostDto {

    private Long id;

    private String description;

    private LocalDateTime createdAt;

    private String username;

    private String bunnyImage;

    private int likes;


    public PostDto(Post post) {
        this.id= post.getId();
        this.description= post.getDescription();
        this.createdAt = post.getCreatedAt();
        this.username = post.getUser().getUsername();
        this.bunnyImage = post.getBunnyImage();
        this.likes = post.getLikes();
    }

    public Post mapTo() {
        Post post = new Post();
        post.setDescription(description);
        post.setBunnyImage(bunnyImage);
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
