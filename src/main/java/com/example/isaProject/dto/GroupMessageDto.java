package com.example.isaProject.dto;

import com.example.isaProject.model.GroupChat;
import com.example.isaProject.model.GroupMessage;
import com.example.isaProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageDto {

    private Long id;

    private Long groupChatId;

    private Long senderId;
    private String senderUsername;

    private String text;

    private LocalDateTime sendTime;

    public GroupMessageDto(GroupMessage groupMessage){
        this.id = groupMessage.getId();
        this.groupChatId = groupMessage.getGroupChat().getId();
        this.senderId = groupMessage.getSender().getId();
        this.senderUsername = groupMessage.getSender().getUsername();
        this.text = groupMessage.getText();
        this.sendTime = groupMessage.getSendTime();
    }
}