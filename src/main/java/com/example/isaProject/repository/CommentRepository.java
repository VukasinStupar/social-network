package com.example.isaProject.repository;

import com.example.isaProject.model.Comment;
import com.example.isaProject.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT (COUNT(c) <= 60) FROM Comment c WHERE c.user.id = :userId AND c.commentCreation >= :oneHourAgo")
    boolean canUserComment(@Param("userId") Long userId, @Param("oneHourAgo") LocalDateTime oneHourAgo);

    @Query("SELECT C FROM Comment C WHERE C.post.id = :postId ORDER BY C.commentCreation DESC")
    List<Comment> commentByPost( @Param("postId") Long postId);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.commentCreation >= :time")
    Long countCommentByData(@Param("time") LocalDateTime time);

    @Query("SELECT COUNT(c.user.id) FROM Comment c")
    Long countUserOnCommentAllTime();



    @Query("SELECT COUNT(DISTINCT c.user.id) FROM Comment c")
    Long countUsersWithComments();

    @Query("SELECT COUNT(DISTINCT c.user.id) FROM Comment c " +
            "WHERE c.user.id NOT IN (SELECT p.user.id FROM Post p)")
    Long findUsersWithCommentsButNoPosts();




    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.post.id = :postId")
    void deleteAllByPostId(@Param("postId") Long postId);


}