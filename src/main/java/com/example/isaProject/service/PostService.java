package com.example.isaProject.service;

import com.example.isaProject.dto.PostDetailsDto;
import com.example.isaProject.dto.PostDto;
import com.example.isaProject.dto.TrendingDto;
import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface PostService {

    public Post create(PostDto postDto, User loggedUser);

    public List<Post> displayPostByUser(Long userId);


    List<Post> displayAllPostsDesc(int page, int size);

    public List<Post> allTrends();

    public long allPostsCount();

    public long lastMonthPostsCount();

    List<Post> findTop5MostLiked();

    List<Post> findTop10PostsAllTime();

    public TrendingDto getAppTrending();

    List<PostDetailsDto> displayDetailedPosts(int page, int size);

    public Post update(Long id, PostDto updateRequest);

    public Post deletePost(Long id);


    public PostDetailsDto displayPostById(Long postId);
}
