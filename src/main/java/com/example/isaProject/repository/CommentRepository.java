package com.example.isaProject.repository;

import com.example.isaProject.model.Comment;
import com.example.isaProject.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT (COUNT(c) <= 60) FROM Comment c WHERE c.user.id = :userId AND c.commentCreation >= :oneHourAgo")
    boolean canUserComment(@Param("userId") Long userId, @Param("oneHourAgo") LocalDateTime oneHourAgo);

}
