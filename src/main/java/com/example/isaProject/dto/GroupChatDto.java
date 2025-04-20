package com.example.isaProject.dto;

import com.example.isaProject.model.GroupChat;
import com.example.isaProject.model.Message;
import com.example.isaProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class GroupChatDto {
    private Long id;
    private Long adminId;
    private LocalDateTime createTime;
    private String name;

    public GroupChatDto(GroupChat groupChat){
        this.id= groupChat.getId();
        this.adminId= groupChat.getAdmin().getId();
        this.createTime= groupChat.getCreateTime();
        this.name = groupChat.getName();

    }
}