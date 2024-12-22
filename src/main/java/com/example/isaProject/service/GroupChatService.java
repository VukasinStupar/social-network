package com.example.isaProject.service;

import com.example.isaProject.dto.GroupChatDto;
import com.example.isaProject.dto.GroupMessageDto;
import com.example.isaProject.model.GroupChat;
import com.example.isaProject.model.GroupMessage;
import com.example.isaProject.model.GroupUser;
import com.example.isaProject.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupChatService {
    GroupChat create(User loggedUser, GroupChatDto groupChatDto);

    GroupChat addUser(Long groupChatId, Long newUserId, Long loggedUserId);

    GroupUser deleteUserGroup(Long groupChatId, Long removeUserId, Long loggedUserId);
    List<GroupUser> allGroupUser(Long userId);

    GroupMessage sentMessageGroup(User loggedUser, GroupMessageDto groupMessageDto);

    List<GroupMessage> getGroupMessages(Long groupId,  Long loggedUserId);



}
