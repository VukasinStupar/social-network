package com.example.isaProject.serviceImpl;

import com.example.isaProject.dto.PostDto;
import com.example.isaProject.dto.TrendingDto;
import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;
import com.example.isaProject.repository.PostRepository;
import com.example.isaProject.repository.RoleRepository;
import com.example.isaProject.repository.UserRepository;
import com.example.isaProject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;



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

        postRepository.save(post);

        return post;
    }

    public List<Post> displayPostByUser(Long userId){
        return postRepository.findAllByUserSortData(userId);

    }
    public List<Post> displayAllPostsDesc() {
        return postRepository.findAllPostsSortedDesc();
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


}
