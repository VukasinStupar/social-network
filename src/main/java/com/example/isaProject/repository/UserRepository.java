package com.example.isaProject.repository;

import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findUserByName(String name);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId AND p.creationDate >= :sevenDaysAgo")
    Long numberOfPostsLast7Days(@Param("userId") Long userId, @Param("sevenDaysAgo") LocalDateTime sevenDaysAgo);

    @Query("SELECT u FROM User u WHERE u.lastPasswordResetDate <= :sevenDays")
    List<User> findUsersWithLastLoginBefore(@Param("sevenDays") LocalDateTime sevenDays);










}
