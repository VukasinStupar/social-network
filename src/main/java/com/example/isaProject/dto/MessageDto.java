package com.example.isaProject.dto;

import com.example.isaProject.model.Message;
import com.example.isaProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Long id;

    private long senderId;

    private long recipientId;

    private String text;


    private String sendTime;

    public MessageDto(Message message) {
        this.id = message.getId();
        this.senderId = message.getSender().getId();
        this.text = message.getText();
        this.recipientId = message.getRecipient().getId();
        this.sendTime = message.getSendTime().toString();
    }


}
