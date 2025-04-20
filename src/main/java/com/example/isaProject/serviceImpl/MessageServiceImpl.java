package com.example.isaProject.serviceImpl;

import com.example.isaProject.dto.MessageDto;
import com.example.isaProject.model.Message;
import com.example.isaProject.model.User;
import com.example.isaProject.repository.MessageRepository;
import com.example.isaProject.repository.UserRepository;
import com.example.isaProject.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public Message create(MessageDto messageDto){
        User userSender = userRepository.findById(messageDto.getSenderId()).orElse(null);
        if (userSender == null) {
            return null;

        }

        User userRecipient = userRepository.findById(messageDto.getRecipientId()).orElse(null);
        if (userRecipient == null) {
            return null;
        }

        Message message = new Message();
        message.setSender(userSender);
        message.setRecipient(userRecipient);
        message.setText(messageDto.getText());
        message.setSendTime(LocalDateTime.now());

        messageRepository.save(message);
        return message;

    }


    //domaci
    @Override
    public List<Long> findAllUsersByRecipient(Long userId) {
        return messageRepository.findAllUsersByRecipient(userId);
    }
    //domaci
    @Override
    public List<Long> findAllUsersBySender(Long userId) {
        return messageRepository.findAllUsersBySender(userId);
    }

    @Override
    public List<User> findAllChatUsers(Long userId) {
        return messageRepository.findAllChatUsers(userId);
    }

    @Override
    public List<Message> findAllMessagesForUsers(Long userId1, Long userId2) {
        return messageRepository.findAllChatMessages(userId1, userId2);
    }
}
