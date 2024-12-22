package com.example.isaProject.repository;

import com.example.isaProject.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("SELECT l FROM Like l WHERE l.user.id = :userId and l.post.id = :postId")
    List<Like> findLikesByUserAndPost(@Param("userId") Long userId, @Param("postId") Long postId);
}
