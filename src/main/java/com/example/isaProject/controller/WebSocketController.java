package com.example.isaProject.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.isaProject.dto.GroupChatDto;
import com.example.isaProject.dto.GroupMessageDto;
import com.example.isaProject.dto.MessageDto;
import com.example.isaProject.model.GroupChat;
import com.example.isaProject.model.GroupMessage;
import com.example.isaProject.model.Message;
import com.example.isaProject.model.User;
import com.example.isaProject.securityAuth.TokenBasedAuthentication;
import com.example.isaProject.service.GroupChatService;
import com.example.isaProject.service.MessageService;
import com.example.isaProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class WebSocketController {
    @Autowired
    private final GroupChatService groupChatService;

    @Autowired
    private final UserService userService;

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;



    public WebSocketController(SimpMessagingTemplate messagingTemplate, MessageService messageService, GroupChatService groupChatService, UserService userService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.groupChatService = groupChatService;
        this.userService = userService;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload MessageDto messageDto) {
        // Save the message to database
        Message createdMessage = messageService.create(messageDto);
        MessageDto dto = new MessageDto(createdMessage);

        // Send to specific user
        String destination = "/topic/" + dto.getRecipientId();
        messagingTemplate.convertAndSend(destination, dto);
    }
    //domaci
    @MessageMapping("/sentMessageGroup")
    public void sentMessageGroup(@Payload GroupMessageDto groupMessageDto){

        GroupMessage groupMessage = groupChatService.sentMessageGroup(groupMessageDto);
        GroupMessageDto dto = new GroupMessageDto(groupMessage);

        String destination = "/groupTopic/" + dto.getGroupChatId();
        messagingTemplate.convertAndSend(destination, dto);
    }

}