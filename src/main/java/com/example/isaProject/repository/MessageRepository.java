package com.example.isaProject.repository;

import com.example.isaProject.model.Like;
import com.example.isaProject.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE (m.sender.id = :user1Id AND m.recipient.id = :user2Id) OR (m.sender.id = :user2Id AND m.recipient.id = :user1Id)")
    List<Message> findAllChatMessages(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);

    //domaci
    @Query("SELECT DISTINCT m.sender.id FROM Message m WHERE m.recipient.id = :userId")
    List<Long> findAllUsersByRecipient(@Param("userId") Long userId);

    @Query("SELECT DISTINCT m.recipient.id FROM Message m WHERE m.sender.id = :userId")
    List<Long> findAllUsersBySender(@Param("userId") Long userId);

}
