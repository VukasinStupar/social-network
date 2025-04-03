package com.example.isaProject.service;

import com.example.isaProject.dto.MessageDto;
import com.example.isaProject.model.Message;
import com.example.isaProject.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface MessageService {
    public Message create(User loggedUser, MessageDto messageDto);


    //domaci
    List<Long> findAllUsersByRecipient(Long userId);

    List<Long> findAllUsersBySender(Long userId);

    List<User> findAllChatUsers(Long userId);

    List<Message> findAllMessagesForUsers(Long userId1, Long userId2);
}
