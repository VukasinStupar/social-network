package com.example.isaProject.serviceImpl;

import com.example.isaProject.dto.*;
import com.example.isaProject.model.Comment;
import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;
import com.example.isaProject.repository.CommentRepository;
import com.example.isaProject.repository.LikeRepository;
import com.example.isaProject.repository.PostRepository;
import com.example.isaProject.repository.UserRepository;
import com.example.isaProject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.*;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;



    //dodati logiku slika
    public Post create(PostDto postDto, User loggedUser){
        User user = userRepository.findById(loggedUser.getId()).orElse(null);
        if(user == null){
            return null;
        }

        Post post = postDto.mapTo();
        post.setLikes(0);
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());

        user.setNumberOfPosts(user.getNumberOfPosts() +1);


        userRepository.save(user);
        postRepository.save(post);

        return post;
    }

    public List<Post> displayPostByUser(Long userId) {
        return postRepository.findAllByUserSortData(userId);
    }





    @Override
    public List<Post> displayAllPostsDesc(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAllPostsSortedDesc(pageable);
    }

    public List<Post> allTrends() {

        return postRepository.findAll();
    }
    @Override
    public long allPostsCount() {
        return postRepository.countAllPosts();
    }
    @Override
    public long lastMonthPostsCount() {
        LocalDateTime monthAgo = LocalDateTime.now().minusMonths(1);
        return postRepository.countPostsLastMonth(monthAgo);
    }
    @Override
    public List<Post> findTop5MostLiked(){
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        List<Post> post = postRepository.findTop5MostLikedLast7Days(sevenDaysAgo);
        return post;
    }
    @Override
    public List<Post> findTop10PostsAllTime() {
        List<Post> post = postRepository.findTop10PostsAllTime();

        return post;
    }

    public TrendingDto getAppTrending(){
        TrendingDto trendingDto = new TrendingDto();
        trendingDto.setCountAllPosts(allPostsCount());
        trendingDto.setCountAllPostsMonth(lastMonthPostsCount());

        List<PostDto> fiveMostPopularPosts7DaysDto = PostDto.convertPostsToPostDtos(findTop5MostLiked());
        List<PostDto> tenMostPopularAllTimeDto = PostDto.convertPostsToPostDtos(findTop10PostsAllTime());

        trendingDto.setFiveMostPopularPosts7Days(fiveMostPopularPosts7DaysDto);
        trendingDto.setTenMostPopularAllTime(tenMostPopularAllTimeDto);

        return trendingDto;
    }

    @Override
    public List<PostDetailsDto> displayDetailedPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Post> posts = postRepository.findAllPostsSortedDesc(pageable);

        List<PostDetailsDto> detailedPosts = new ArrayList<>();

        for(Post post : posts){
            List<Comment> comments = commentRepository.commentByPost(post.getId());
            List<CommentDto> commentDto = CommentDto.convertListToDto(comments);

            PostDetailsDto postDetailsDto = new PostDetailsDto(post, commentDto);
            detailedPosts.add(postDetailsDto);
        }
        return detailedPosts;
    }

    public Post update(Long id, PostDto updateRequest){
        Post post = postRepository.findPostById(id);

        if(post == null){
            return null;
        }

        post.setDescription(updateRequest.getDescription());
        post.setBunnyImage(updateRequest.getBunnyImage());

        postRepository.save(post);
        return post;
    }

    public Post deletePost(Long id){
        Post post = postRepository.findPostById(id);

        if(post == null){
            return null;
        }

        likeRepository.deleteAllByPostId(post.getId());

        commentRepository.deleteAllByPostId(post.getId());

        postRepository.delete(post);
        return post;

    }

    @Override
    public PostDetailsDto displayPostById(Long postId){
        Post post = postRepository.findPostById(postId);


        List<Comment> comments = commentRepository.commentByPost(post.getId());
        List<CommentDto> commentDto = CommentDto.convertListToDto(comments);

        PostDetailsDto postDetailsDto = new PostDetailsDto(post, commentDto);


        return postDetailsDto;
    }


}