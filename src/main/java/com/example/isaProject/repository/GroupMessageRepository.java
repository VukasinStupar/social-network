package com.example.isaProject.repository;

import com.example.isaProject.model.GroupMessage;
import com.example.isaProject.model.GroupUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface GroupMessageRepository extends JpaRepository<GroupMessage, Long> {

    @Query("SELECT gm FROM GroupMessage gm WHERE gm.groupChat.id = :groupId")
    List<GroupMessage> groupMessageGroup(@Param("groupId") Long groupId);

    @Query("SELECT gm FROM GroupMessage gm WHERE gm.groupChat.id = :groupId ORDER BY gm.sendTime DESC")
    Page<GroupMessage> groupMessageGroup(@Param("groupId") Long groupId, Pageable pageable);

    @Query(value = "SELECT * FROM group_message gm WHERE gm.group_chat_id = :groupChatId AND gm.send_time < :pointInTime ORDER BY gm.send_time DESC LIMIT 10", nativeQuery = true)
    List<GroupMessage> findLast10MessagesBeforeNative(
            @Param("groupChatId") Long groupChatId,
            @Param("pointInTime") LocalDateTime pointInTime
    );

    @Query("SELECT gm FROM GroupMessage gm WHERE gm.groupChat.id = :groupChatId AND gm.sendTime > :pointInTime ORDER BY gm.sendTime ASC")
    List<GroupMessage> findAllMessagesAfter(
            @Param("groupChatId") Long groupChatId,
            @Param("pointInTime") LocalDateTime pointInTime
    );

   @Query(value = "(SELECT * FROM group_message gm WHERE gm.group_chat_id = :groupChatId AND gm.send_time < :pointInTime ORDER BY gm.send_time DESC LIMIT 10) " +
           " UNION ALL " +
            "(SELECT * FROM group_message gm WHERE gm.group_chat_id = :groupChatId AND gm.send_time > :pointInTime ORDER BY gm.send_time ASC)",
            nativeQuery = true)
    List<GroupMessage> findMessagesAroundPoint(
            @Param("groupChatId") Long groupChatId,
            @Param("pointInTime") LocalDateTime pointInTime
    );



}
