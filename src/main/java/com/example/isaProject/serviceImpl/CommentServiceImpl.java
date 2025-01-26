package com.example.isaProject.serviceImpl;

import com.example.isaProject.dto.ApplicationAnalyticsDTO;
import com.example.isaProject.dto.CommentDto;
import com.example.isaProject.model.*;
import com.example.isaProject.repository.*;
import com.example.isaProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FollowRepository followRepository;

    public Comment create(CommentDto commentDto, User loggedUser){
        User user = userRepository.findById(loggedUser.getId()).orElse(null);
        if(user == null){
            return null;
        }

        Post post = postRepository.findById(commentDto.getPostId()).orElse(null);
        if(post == null){
            return null;
        }
        Long followeeId = post.getUser().getId();

        if(!followRepository.existsByFollowerIdAndFolloweeId(user.getId(), followeeId)){
            return null;
        }
        LocalDateTime time = LocalDateTime.now().minusHours(1);
        //domaci
        if(!commentRepository.canUserComment(user.getId(), time)){
            return null;
        }
        Comment comment = new Comment();

        comment.setText(commentDto.getText());
        comment.setCommentCreation(LocalDateTime.now());

        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);

        return comment;

    }
    //domaci
    public ApplicationAnalyticsDTO applicationAnalytics(){

        ApplicationAnalyticsDTO aaDto = new ApplicationAnalyticsDTO();

        LocalDateTime timeWeek = LocalDateTime.now().minusDays(7);
        LocalDateTime timeMonth = LocalDateTime.now().minusMonths(1);
        LocalDateTime timeYear = LocalDateTime.now().minusYears(1);

        Long numberOfPostByWeek = postRepository.countPostByData(timeWeek);
        Long numberOfPostByMonth = postRepository.countPostByData(timeMonth);
        Long numberOfPostByYear  = postRepository.countPostByData(timeYear);

        Long numberOfCommentByWeek = commentRepository.countCommentByData(timeWeek);
        Long numberOfCommentByMonth = commentRepository.countCommentByData(timeMonth);
        Long numberOfCommentByYear = commentRepository.countCommentByData(timeYear);

        Long UserWithPost =postRepository.countUsersWithPosts();
        Long UserWithComment = commentRepository.countUsersWithComments();
        long totalUsers = userRepository.count();

        Long userWithZeroActivity = userRepository.findUsersWithZeroActivityLong();


        aaDto.setNumberOfPostByWeek(numberOfPostByWeek);
        aaDto.setNumberOfPostByMonth(numberOfPostByMonth);
        aaDto.setNumberOfPostByYear(numberOfPostByYear);

        aaDto.setNumberOfCommentByWeek(numberOfCommentByWeek);
        aaDto.setNumberOfCommentByMonth(numberOfCommentByMonth);
        aaDto.setNumberOfCommentByYear(numberOfCommentByYear);

        aaDto.setCountUserWithPostLong(UserWithPost);
        aaDto.setCountUserWithCommentLong(UserWithComment);
        //aaDto.setUsersWithZeroActivityLong(totalUsers - (UserWithPost + UserWithComment));
        aaDto.setUsersWithZeroActivityLong(userWithZeroActivity);

        double usersWithPostPercentage = (double) UserWithComment / totalUsers * 100;
        double usersWithCommentOnlyPercentage = (double) UserWithPost / totalUsers * 100;
        double usersWithNoPostAndNoCommentPercentage = (double) userWithZeroActivity / totalUsers * 100;

        aaDto.setPercentUserWithPost(usersWithPostPercentage);
        aaDto.setPercentUserWithComment(usersWithCommentOnlyPercentage);
        aaDto.setPercentUsersWithZeroActivity(usersWithNoPostAndNoCommentPercentage);

        return aaDto;
    }



}