package com.example.isaProject.serviceImpl;

import com.example.isaProject.dto.LikeDto;
import com.example.isaProject.model.Like;
import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;
import com.example.isaProject.repository.LikeRepository;
import com.example.isaProject.repository.MessageRepository;
import com.example.isaProject.repository.PostRepository;
import com.example.isaProject.repository.UserRepository;
import com.example.isaProject.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public Like like(LikeDto likeDto, User loggedUser){
        User user = userRepository.findById(loggedUser.getId()).orElse(null);
        if(user == null){
            return null;
        }

        Post post = postRepository.findById(likeDto.getPostId()).orElse(null);
        if(post == null){
            return null;
        }

        if(!likeRepository.findLikesByUserAndPost(user.getId(), post.getId()).isEmpty()){
            return null;
        }
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);

        likeRepository.save(like);

        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);


        return like;
    }

    public Like removeLike(LikeDto likeDto, User loggedUser){
        User user = userRepository.findById(loggedUser.getId()).orElse(null);
        if(user == null){
            return null;
        }

        Post post = postRepository.findById(likeDto.getPostId()).orElse(null);
        if(post == null){
            return null;
        }

        List<Like> likes = likeRepository.findLikesByUserAndPost(user.getId(), post.getId());
        if(likes.size() != 1){
            return null;
        }
        Like like = likes.get(0);

        likeRepository.deleteById(like.getId());

        post.setLikes(post.getLikes() - 1);
        postRepository.save(post);

        return like;
    }

}
