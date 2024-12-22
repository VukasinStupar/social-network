package com.example.isaProject.repository;

import com.example.isaProject.model.Post;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId ORDER BY p.createdAt DESC")
    List<Post> findAllByUserSortData(@Param("userId") Long userId);

    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    List<Post> findAllPostsSortedDesc();


    @Query("SELECT COUNT(p) FROM Post p")
    int countAllPosts();

    //@Query("SELECT p FROM Post p WHERE p.createdAt >= :lastMonth")
    //List<Post> findAllPostsLastMonth(@Param("lastMonth") LocalDateTime lastMonth);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.createdAt >= :lastMonth")
    int countPostsLastMonth(@Param("lastMonth") LocalDateTime lastMonth);

    //@Query("SELECT p FROM Post p WHERE p.createdAt >= :sevenDaysAgo ORDER BY p.likes DESC")
    //List<Post> findTop5MostLikedLast7Days1(@Param("sevenDaysAgo") LocalDateTime sevenDaysAgo);

    @Query(value = "SELECT * FROM post WHERE created_at >= :sevenDaysAgo ORDER BY likes DESC LIMIT 5", nativeQuery = true)
    List<Post> findTop5MostLikedLast7Days(@Param("sevenDaysAgo") LocalDateTime sevenDaysAgo);

    @Query(value = "SELECT * FROM post ORDER BY likes DESC LIMIT 10", nativeQuery = true)
    List<Post> findTop10PostsAllTime();

    @Query("SELECT SUM(p.likes) FROM Post p WHERE p.user.id = :userId")
    Long countLikesByUser(@Param("userId") Long userId);

    //@Query("SELECT p FROM Post p ORDER BY p.likes DESC")
    //List<Post> findTop10PostsAllTime(Pageable pageable);



}