package com.example.isaProject.controller;

import com.example.isaProject.dto.LikeDto;
import com.example.isaProject.dto.MessageDto;
import com.example.isaProject.dto.PostDto;
import com.example.isaProject.dto.UserDto;
import com.example.isaProject.model.Like;
import com.example.isaProject.model.Message;
import com.example.isaProject.model.Post;
import com.example.isaProject.model.User;
import com.example.isaProject.securityAuth.TokenBasedAuthentication;
import com.example.isaProject.service.MessageService;
import com.example.isaProject.service.PostService;
import com.example.isaProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/messages", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;



    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestBody MessageDto messageDto, Principal principal){

        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
        Message message = messageService.create(loggedUser, messageDto);
        if(message == null){
            return new ResponseEntity<MessageDto>(HttpStatus.BAD_REQUEST);
        }
        MessageDto dto = new MessageDto(message);

        return new ResponseEntity<MessageDto>(dto, HttpStatus.OK);
    }

    @GetMapping("getAllFromChat/{senderId}/{recepientId}")
    public ResponseEntity<ArrayList<MessageDto>> getAllFromChat(@PathVariable Long senderId, @PathVariable Long recepientId){
        List<Message> messages = messageService.getAllFromChat(senderId, recepientId);
        if(messages == null){
            return new ResponseEntity<ArrayList<MessageDto>>(HttpStatus.BAD_REQUEST);
        }

        ArrayList<MessageDto> messageDtos = new ArrayList<MessageDto>();
        for(Message message : messages){
            messageDtos.add(new MessageDto(message));
        }
        return new ResponseEntity<ArrayList<MessageDto>>(messageDtos, HttpStatus.OK);
    }

    //domaci
    @GetMapping("getAllUsersForUser")
    public ResponseEntity<ArrayList<UserDto>> getAllUsersForUser(Principal principal){
        ArrayList<UserDto> userDtos = new ArrayList<UserDto>();

        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
        if(loggedUser == null){
            return new ResponseEntity<ArrayList<UserDto>>(HttpStatus.BAD_REQUEST);
        }
        Long userId = loggedUser.getId();

        List<User> users = messageService.findAllUsersByUser(userId);

        for(User us : users){
            userDtos.add(new UserDto(us));

        }
        return new ResponseEntity<ArrayList<UserDto>>(userDtos, HttpStatus.OK);
    }


}
