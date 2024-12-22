package com.example.isaProject.dto;

import com.example.isaProject.model.GroupChat;
import com.example.isaProject.model.GroupUser;
import com.example.isaProject.model.User;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class GroupUserDto {
    private Long id;

    private Long userId;

    private Long groupChatId;

    private LocalDateTime addedAt;

    public GroupUserDto(GroupUser groupUser){
        this.id = groupUser.getId();
        this.userId = groupUser.getUser().getId();
        this.groupChatId = groupUser.getId();
        this.addedAt = groupUser.getAddedAt();
    }
}
