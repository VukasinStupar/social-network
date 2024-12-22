package com.example.isaProject.service;

import com.example.isaProject.dto.MessageDto;
import com.example.isaProject.model.Message;
import com.example.isaProject.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    public Message create(User loggedUser, MessageDto messageDto);

    public List<Message> getAllFromChat(Long senderId, Long recepientId);

    //domaci
    List<Long> findAllUsersByRecipient(Long userId);

    List<Long> findAllUsersBySender(Long userId);

    List<User> findAllUsersByUser(Long userId);
}
