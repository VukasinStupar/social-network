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

    private LocalDateTime sendTime;

    public MessageDto(Message message) {
        this.id = message.getId();
        this.senderId = message.getSender().getId();
        this.text = message.getText();
        this.sendTime = message.getSendTime();
        this.recipientId = message.getRecipient().getId();
    }

    public Message mapTo() {
        Message message = new Message();
        message.getSender().setId(id);
        message.setText(text);
        message.setSendTime(sendTime);
        message.getRecipient().setId(recipientId);
        return message;

    }

}
