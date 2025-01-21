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

    private double countUserWithNoComment;
    private double countUserWithNoPost;
    private double usersWithNoPostAndNoComment;

}
