package com.example.isaProject.dto;

import com.example.isaProject.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.query.Param;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationAnalyticsDTO {

    private Long id;
    private Long numberOfPostByWeek;
    private Long numberOfPostByYear;
    private Long numberOfPostByMonth;

    private Long numberOfCommentByWeek;
    private Long numberOfCommentByMonth;
    private Long numberOfCommentByYear;

    private Long countUserOnPostAllTime;
    private Long countUserOnCommentAllTime;


    private double percentUserWithPost;
    private double percentUserWithComment;
    private double percentUsersWithZeroActivity;

    private Long countUserWithPostLong;
    private Long countUserWithCommentLong;
    private Long UsersWithZeroActivityLong;

}
