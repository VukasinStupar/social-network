package com.example.isaProject.controller;

import com.example.isaProject.dto.GroupChatDto;
import com.example.isaProject.dto.GroupMessageDto;
import com.example.isaProject.model.*;
import com.example.isaProject.securityAuth.TokenBasedAuthentication;
import com.example.isaProject.service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/groupChat", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class GroupChatController {

    @Autowired
    private GroupChatService groupChatService;


    @PostMapping
    public ResponseEntity<GroupChatDto> create(@RequestBody GroupChatDto groupChatDto, Principal principal){

        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
        GroupChat groupChat = groupChatService.create(loggedUser, groupChatDto);
        if(groupChat == null){
            return new ResponseEntity<GroupChatDto>(HttpStatus.BAD_REQUEST);
        }
        GroupChatDto dto = new GroupChatDto(groupChat);

        return new ResponseEntity<GroupChatDto>(dto, HttpStatus.OK);
    }

    @PutMapping("addUserGroup/{groupChatId}/{newUserId}")
    public ResponseEntity<GroupChatDto> addUserGroup(Principal principal, @PathVariable Long groupChatId, @PathVariable Long newUserId){

        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();

        GroupChat groupChat = groupChatService.addUser(groupChatId, newUserId, loggedUser.getId());
        if(groupChat == null){
            return new ResponseEntity<GroupChatDto>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("removeUserGroup/{groupChatId}/{removeUserId}")
    public ResponseEntity<GroupChatDto> removeUserGroup(Principal principal, @PathVariable Long groupChatId, @PathVariable Long removeUserId){

        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();

        groupChatService.deleteUserGroup(groupChatId, removeUserId, loggedUser.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("sentMessageGroup")
    public ResponseEntity<GroupMessageDto> sentMessageGroup(@RequestBody GroupMessageDto groupMessageDto, Principal principal){

        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();

        GroupMessage groupMessage = groupChatService.sentMessageGroup( groupMessageDto);
        if(groupMessage == null){
            return new ResponseEntity<GroupMessageDto>(HttpStatus.BAD_REQUEST);
        }
        GroupMessageDto groupMesagesDto = new GroupMessageDto(groupMessage);

        return new ResponseEntity<GroupMessageDto>(groupMesagesDto, HttpStatus.OK);
    }

    @GetMapping("getLoggedUserGroups")
    public ResponseEntity<ArrayList<GroupChatDto>> getAllGroupsForUser(Principal principal){
        ArrayList<GroupChatDto> groupChatsDto = new ArrayList<GroupChatDto>();

        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();

        List<GroupUser> groupUsers = groupChatService.allGroupUser(loggedUser.getId());

        for(GroupUser us : groupUsers){
            groupChatsDto.add(new GroupChatDto(us.getGroup()));
        }

        return new ResponseEntity<ArrayList<GroupChatDto>>(groupChatsDto, HttpStatus.OK);
    }

    @GetMapping("allMessagesForGroup/{groupChatId}")
    public ResponseEntity<List<GroupMessageDto>> allMessagesForGroup(@PathVariable Long groupChatId, Principal principal){

        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();

        List<GroupMessage> groupMessages = groupChatService.getGroupMessages(groupChatId, loggedUser.getId());

        List<GroupMessageDto> groupMessageDtos = new ArrayList<>();
        for(GroupMessage groupMessage : groupMessages){
            groupMessageDtos.add(new GroupMessageDto(groupMessage));
        }

        return new ResponseEntity<List<GroupMessageDto>>(groupMessageDtos, HttpStatus.OK);
    }


}