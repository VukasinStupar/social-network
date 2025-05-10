package com.example.isaProject.repository;

import com.example.isaProject.model.GroupMessage;
import com.example.isaProject.model.GroupUser;
import com.example.isaProject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {



    @Query("SELECT gu FROM GroupUser gu WHERE gu.group.id = :groupId and gu.user.id = :userId")
    GroupUser findByUserIdGroupId(@Param("userId") Long userId, @Param("groupId") Long groupId);


    @Query("SELECT DISTINCT gu FROM GroupUser gu WHERE gu.user.id = :userId")
    List<GroupUser> allGroupUser(@Param("userId") Long userId);

    //DOMACI
    @Query("SELECT DISTINCT gu.user FROM GroupUser gu WHERE gu.group.id = :groupChatId")
    List<User> findAllUsersInGroup(@Param("groupChatId") Long groupChatId, Pageable pageable);




}
