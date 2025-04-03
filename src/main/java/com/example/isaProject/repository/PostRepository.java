package com.example.isaProject.repository;

import com.example.isaProject.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId ORDER BY p.createdAt DESC")
    List<Post> findAllByUserSortData(@Param("userId") Long userId);

    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    List<Post> findAllPostsSortedDesc(Pageable pageable);


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

   /* @Query("SELECT COALESCE(COUNT(p), 0) " +
            "FROM Post p " +
            "WHERE p.user.id = :userId " +
            "GROUP BY p.user.id " +
            "HAVING COUNT(p) BETWEEN :from AND :to")
    Long countPostsForUser(@Param("userId") Long userId,
                           @Param("from") Long from,
                           @Param("to") Long to);
*/
   @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId")
   Long countPostsByUser(@Param("userId") Long userId);


    Post findPostById(Long id);

    //domaci
    @Query("SELECT COUNT(p) FROM Post p WHERE p.createdAt >= :time")
    Long countPostByData(@Param("time") LocalDateTime time);



    @Query("SELECT COUNT(DISTINCT p.user.id) FROM Post p")
    Long countUsersWithPosts();

}