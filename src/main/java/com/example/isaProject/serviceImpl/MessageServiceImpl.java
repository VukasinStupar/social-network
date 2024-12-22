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

    public Message create(User loggedUser, MessageDto messageDto){
        User userSender = userRepository.findById(loggedUser.getId()).orElse(null);
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


    @Override
    public List<Message> getAllFromChat(Long senderId, Long recepientId){
        List<Message> messages = messageRepository.findAllChatMessages(senderId, recepientId);
        return messages;
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
    public List<User> findAllUsersByUser(Long userId) {
        List<Long> usersIdByRecipient = messageRepository.findAllUsersByRecipient(userId);
        List<Long> usersIdBySender = messageRepository.findAllUsersBySender(userId);

        List<Long> allUsersId = new ArrayList<>();

        allUsersId.addAll(usersIdByRecipient);
        allUsersId.addAll(usersIdBySender);

        List<User> userList = new ArrayList<>();

        for(Long id : allUsersId){
           User users = userRepository.findById(id).orElse(null);
            userList.add(users);
        }
        return userList;
    }
}
