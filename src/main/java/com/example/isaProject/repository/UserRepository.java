package com.example.isaProject.repository;

import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findUserByName(String name);

    User findUserByActivationToken(String activationToken);



    @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId AND p.createdAt >= :fromDate")
    Long numberOfPostsLast7Days(@Param("userId") Long userId, @Param("fromDate") LocalDateTime fromDate);


    @Query("SELECT u FROM User u WHERE u.lastPasswordResetDate <= :sevenDays")
    List<User> findUsersWithLastLoginBefore(@Param("sevenDays") LocalDateTime sevenDays);

    @Query("SELECT u FROM User u WHERE u.lastLogin IS NULL OR u.lastLogin <= :sevenDays")
    List<User> findUsersLogged7DaysAgo(@Param("sevenDays") LocalDateTime sevenDays);

    //@Query("SELECT u FROM User u, Post p, Follow f, WHERE u.name =: name or u.surname =:surname or u.email =: email or p.user.username =: username or f.follower.username =: username")
    //List<User> registredUsers(@Param("name") String name, @Param("surname") String surname, @Param("email") String userEmail, @Param("username") String username);


   /* @Query("SELECT u " +
            "FROM User u " +
            "LEFT JOIN Post p ON u.id = p.user.id " +
            "WHERE (:name IS NULL OR u.name LIKE CONCAT('%', :name, '%')) " +
            "AND (:surname IS NULL OR u.surname LIKE CONCAT('%', :surname, '%')) " +
            "AND (:email IS NULL OR u.email LIKE CONCAT('%', :email, '%')) " +
            "GROUP BY u.id " +
            "HAVING (:minPosts IS NULL OR COUNT(p.id) >= :minPosts) " +
            "AND (:maxPosts IS NULL OR COUNT(p.id) <= :maxPosts)")
    List<User> registredUsers(@Param("name") String name,
                              @Param("surname") String surname,
                              @Param("email") String email,
                              @Param("minPosts") Long minPosts,
                              @Param("maxPosts") Long maxPosts,
                              Pageable pageable);
*/
   @Query("SELECT u " +
           "FROM User u " +
           "WHERE (:name IS NULL OR u.name LIKE CONCAT('%', :name, '%')) " +
           "AND (:surname IS NULL OR u.surname LIKE CONCAT('%', :surname, '%')) " +
           "AND (:email IS NULL OR u.email LIKE CONCAT('%', :email, '%')) " +
           "AND (:minPosts IS NULL OR u.numberOfPosts >= :minPosts) " +
           "AND (:maxPosts IS NULL OR u.numberOfPosts <= :maxPosts) " +
           "ORDER BY " +
           "CASE WHEN :sortParam = 'postCount' THEN u.numberOfPosts " +
           "     ELSE u.name END DESC")
   List<User> searchUsers(@Param("name") String name,
                          @Param("surname") String surname,
                          @Param("email") String email,
                          @Param("minPosts") Long minPosts,
                          @Param("maxPosts") Long maxPosts,
                          @Param("sortParam") String sortParam,
                          Pageable pageable);


    @Query("SELECT COUNT(DISTINCT u.id) FROM User u " +
            "WHERE u.id NOT IN (SELECT p.user.id FROM Post p) and u.id NOT IN (SELECT c.user.id FROM Comment c)")
    Long findUsersWithZeroActivityLong();

    @Query("SELECT u FROM User u ORDER BY u.name DESC")
    List<User> findAllUsersSortedDesc(Pageable pageable);

}