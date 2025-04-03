package com.example.isaProject.repository;

import com.example.isaProject.model.Like;
import com.example.isaProject.model.Message;
import com.example.isaProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE (m.sender.id = :user1Id AND m.recipient.id = :user2Id) OR (m.sender.id = :user2Id AND m.recipient.id = :user1Id)")
    List<Message> findAllChatMessages(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);

    @Query("SELECT DISTINCT m.sender.id FROM Message m WHERE m.recipient.id = :userId")
    List<Long> findAllUsersByRecipient(@Param("userId") Long userId);

    @Query("SELECT DISTINCT m.recipient.id FROM Message m WHERE m.sender.id = :userId")
    List<Long> findAllUsersBySender(@Param("userId") Long userId);

    //TODO: redosled korisnika sa kojima smo se dopisivali je problem
    @Query("SELECT DISTINCT u FROM User u " +
            "WHERE u.id IN (SELECT m.sender.id FROM Message m WHERE m.recipient.id = :userId) " +
            "OR u.id IN (SELECT m.recipient.id FROM Message m WHERE m.sender.id = :userId)")
    List<User> findAllChatUsers(@Param("userId") Long userId);

}
