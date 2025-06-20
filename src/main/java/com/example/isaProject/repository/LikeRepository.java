package com.example.isaProject.repository;

import com.example.isaProject.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("SELECT l FROM Like l WHERE l.user.id = :userId and l.post.id = :postId")
    Like findLikesByUserAndPost(@Param("userId") Long userId, @Param("postId") Long postId);


    @Modifying
    @Transactional
    @Query("DELETE FROM Like l WHERE l.post.id = :postId")
    void deleteAllByPostId(@Param("postId") Long postId);
}
