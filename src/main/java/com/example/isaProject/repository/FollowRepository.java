package com.example.isaProject.repository;

import com.example.isaProject.model.Follow;
import com.example.isaProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END " +
            "FROM Follow f WHERE f.follower.id = :followerId AND f.followee.id = :followeeId")
    boolean existsByFollowerIdAndFolloweeId(@Param("followerId") Long followerId, @Param("followeeId") Long followeeId);

    @Query("SELECT f FROM Follow f WHERE f.follower.id = :followerId AND f.followee.id = :followeeId")
    Follow findByFollowerIdAndFolloweeId(@Param("followerId") Long followerId, @Param("followeeId") Long followeeId);

    //Follow deleteByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
    //@Query("DELETE FROM Follow f WHERE f.follower.id = :followerId AND f.followee.id = :followeeId")
    //void deleteByFollowerIdAndFolloweeId(@Param("followerId") Long followerId, @Param("followeeId") Long followeeId);

    //@Modifying // Oznaka za modifikovanje podataka (DML operacija)
    //@Transactional // Potrebno za DML operacije
   // @Query("DELETE FROM Follow f WHERE f.follower.id = :followerId AND f.followee.id = :followeeId")
    //void deleteByFollowerIdAndFolloweeId(@Param("followerId") Long followerId, @Param("followeeId") Long followeeId);


    @Query("SELECT COUNT(f) FROM Follow f WHERE f.follower.id = :userId AND f.followCreation >= :oneMinuteAgo")
    long countFollowsInLastMinute(@Param("userId") Long userId, @Param("oneMinuteAgo") LocalDateTime oneMinuteAgo);
    //domaci koga user prati
    @Query("SELECT f.followee FROM Follow f WHERE f.follower.id = :userId")
    List<User> allFollowersOfUser(@Param("userId") Long userId);

    //domaci pratioci od usera
    @Query("SELECT f.follower FROM Follow f WHERE f.follower.id = :userId")
    List<User> allFollowOfUser(@Param("userId") Long userId);


    @Query("SELECT COUNT(f) FROM Follow f WHERE f.followee.id = :userId AND f.followCreation >= :sevenDaysAgo")
    long countNewFollowersInLast7Days(@Param("userId") Long userId, @Param("sevenDaysAgo") LocalDateTime sevenDaysAgo);

  /*  @Query("SELECT p.user.id FROM Post p " + "WHERE p.user.id = :userId " + "GROUP BY p.user.id " + "HAVING COUNT(p) BETWEEN :from AND :to")
    Long countFolloweeForUser(@Param("userId") Long userId, @Param("from") Long from,@Param("to") Long to);
*/

    @Query("SELECT COUNT(f.followee) FROM Follow f WHERE f.follower.id = :userId")
    Long countFollowersOfUser(@Param("userId") Long userId);


}
