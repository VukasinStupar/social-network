package com.example.isaProject.serviceImpl;

import com.example.isaProject.dto.ApplicationAnalyticsDTO;
import com.example.isaProject.dto.CommentDto;
import com.example.isaProject.model.*;
import com.example.isaProject.repository.*;
import com.example.isaProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public ApplicationAnalyticsDTO ApplicationAnalytics(){

        ApplicationAnalyticsDTO aaDto = new ApplicationAnalyticsDTO();

        LocalDateTime timeWeek = LocalDateTime.now().minusDays(7);
        LocalDateTime timeMonth = LocalDateTime.now().minusMonths(1);
        LocalDateTime timeYear = LocalDateTime.now().minusYears(1);

        Long numberOfPostByWeek = postRepository.countPostByData(timeWeek);
        Long numberOfPostByYear = postRepository.countPostByData(timeMonth);
        Long numberOfPostByMonth = postRepository.countPostByData(timeYear);

        Long numberOfCommentByWeek = postRepository.countPostByData(timeWeek);
        Long numberOfCommentByMonth = postRepository.countPostByData(timeMonth);
        Long numberOfCommentByYear = postRepository.countPostByData(timeYear);

        Long countUserWithNoComment =commentRepository.countUserWithNoComments();
        Long countUserWithNoPost = postRepository.countUserWithNoPosts();
        Long totalUsers = userRepository.countTotalUsers();


        aaDto.setNumberOfPostByWeek(numberOfPostByWeek);
        aaDto.setNumberOfPostByMonth(numberOfPostByYear);
        aaDto.setNumberOfPostByYear(numberOfPostByMonth);

        aaDto.setNumberOfCommentByWeek(numberOfCommentByWeek);
        aaDto.setNumberOfCommentByMonth(numberOfCommentByMonth);
        aaDto.setNumberOfCommentByYear(numberOfCommentByYear);

        aaDto .setCountUserWithNoComment(countUserWithNoComment);
        aaDto.setCountUserWithNoPost(countUserWithNoPost);

        double usersWithPostPercentage = (double) countUserWithNoPost / totalUsers * 100;
        double usersWithCommentOnlyPercentage = (double) countUserWithNoComment / totalUsers * 100;
        double usersWithNoPostAndNoCommentPercentage = (double) (countUserWithNoComment + countUserWithNoPost) / totalUsers * 100;

        return aaDto;
    }

}