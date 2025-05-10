package com.example.isaProject.serviceImpl;

import com.example.isaProject.dto.GroupChatDto;
import com.example.isaProject.dto.GroupMessageDto;
import com.example.isaProject.model.*;
import com.example.isaProject.repository.*;
import com.example.isaProject.service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class GroupChatServiceImpl implements GroupChatService {
    @Autowired
    private GroupChatRepository groupChatRepository;

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private GroupMessageRepository groupMessageRepository;

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Override
    public GroupChat create(User loggedUser, GroupChatDto groupChatDto){

        if (loggedUser == null) {
            return null;
        }

        GroupChat groupChat = new GroupChat();
        groupChat.setAdmin(loggedUser);
        groupChat.setCreateTime(LocalDateTime.now());
        groupChat = groupChatRepository.save(groupChat);

        GroupUser groupUser = new GroupUser();
        groupUser.setUser(loggedUser);
        groupUser.setGroup(groupChat);
        groupUser.setAddedAt(LocalDateTime.now());

        groupUserRepository.save(groupUser);

        return groupChat;
    }

    @Override
    public GroupChat addUser(Long groupChatId, Long newUserId, Long loggedUserId){

        GroupChat groupChat = groupChatRepository.findById(groupChatId).orElse(null);
        if(groupChat == null){
            return null;
        }

        Long adminId = groupChat.getAdmin().getId();
        if (!adminId.equals(loggedUserId)) {
            return null;
        }
        User user = userRepository.findById(newUserId).orElse(null);
        if(user == null){
            return null;
        }

        GroupUser foundGroupUser = groupUserRepository.findByUserIdGroupId(newUserId, groupChatId);
        if(foundGroupUser != null){
            return null;
        }

        GroupUser groupUser = new GroupUser();
        groupUser.setUser(user);
        groupUser.setGroup(groupChat);
        groupUser.setAddedAt(LocalDateTime.now());

        groupUserRepository.save(groupUser);

        return groupChat;
    }

    @Override
    public GroupUser deleteUserGroup(Long groupChatId, Long removeUserId, Long loggedUserId){
        GroupChat groupChat = groupChatRepository.findById(groupChatId).orElse(null);
        if(groupChat == null){
            return null;
        }

        Long adminId = groupChat.getAdmin().getId();
        if (adminId != loggedUserId) {
            return null;
        }

        GroupUser groupUser = groupUserRepository.findByUserIdGroupId(removeUserId, groupChatId);
        if(groupUser == null){
            return null;
        }

        groupUserRepository.delete(groupUser);

        return groupUser;
    }


    @Override
    public GroupMessage sentMessageGroup(GroupMessageDto groupMessageDto) {
        User user = userRepository.findById(groupMessageDto.getSenderId()).orElse(null);
        if(user == null){
            return null;
        }

        GroupChat groupChat = groupChatRepository.findById(groupMessageDto.getGroupChatId()).orElse(null);

        if(groupChat == null){
            return null;
        }
        GroupUser groupUser = groupUserRepository.findByUserIdGroupId(groupMessageDto.getSenderId(), groupMessageDto.getGroupChatId());
        if(groupUser == null){
            return null;
        }

        GroupMessage groupMessage = new GroupMessage();
        groupMessage.setGroupChat(groupChat);
        groupMessage.setText(groupMessageDto.getText());
        groupMessage.setSender(user);
        groupMessage.setSendTime(LocalDateTime.now());

        groupMessageRepository.save(groupMessage);

        return groupMessage;
    }

    @Override
    public List<GroupMessage> getGroupMessages(Long groupId, Long loggedUserId) {
        GroupUser groupUser = groupUserRepository.findByUserIdGroupId(loggedUserId, groupId);
        if(groupUser == null){
            return null;
        }
        LocalDateTime enteringGroupTime = groupUser.getAddedAt();

        List<GroupMessage> groupMessagesBefore = groupMessageRepository.findLast10MessagesBeforeNative(groupId, enteringGroupTime);
        List<GroupMessage> groupMessagesAfter = groupMessageRepository.findAllMessagesAfter(groupId, enteringGroupTime);

        ArrayList<GroupMessage> groupMessages = new ArrayList<GroupMessage>();
        groupMessages.addAll(groupMessagesBefore);
        groupMessages.addAll(groupMessagesAfter);

        return groupMessages;
    }



    public List<GroupMessage> getGroupMessagesEfficientWay(Long groupId, Long loggedUserId) {
        GroupUser groupUser = groupUserRepository.findByUserIdGroupId(loggedUserId, groupId);
        if(groupUser == null){
            return null;
        }
        LocalDateTime enteringGroupTime = groupUser.getAddedAt();

        List<GroupMessage> groupMessages = groupMessageRepository.findMessagesAroundPoint(groupId, enteringGroupTime);
        return groupMessages;
   }

   //pageable da se ubaci u citanje ceta, necemo za sad imati
    public Page<GroupMessage> getPaginatedGroupMessages(Long groupId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return groupMessageRepository.groupMessageGroup(groupId, pageable);
    }

    @Override
    public List<GroupUser> allGroupUser(Long userId) {
        return groupUserRepository.allGroupUser(userId);
    }

    //DOMACI
    @Override
    public List<User> findAllUsersInGroup(Long groupChatId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return groupUserRepository.findAllUsersInGroup(groupChatId, pageable);
    }

}
