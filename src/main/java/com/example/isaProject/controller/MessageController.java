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




    @GetMapping("/chatted-users")
    public ResponseEntity<ArrayList<UserDto>> getChattedUsers(Principal principal) {
        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();

        Long userId = loggedUser.getId();
        List<User> users = messageService.findAllChatUsers(userId);


        ArrayList<UserDto> userDtos = new ArrayList<>();

        for (User us : users) {
            userDtos.add(new UserDto(us));
        }

        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("messagesWithUser/{userId}")
    public ResponseEntity<ArrayList<MessageDto>> getAllMessagesWithUser(Principal principal, @PathVariable Long userId){
        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();

        List<Message> messages = messageService.findAllMessagesForUsers(loggedUser.getId(), userId);

        ArrayList<MessageDto> messageDtos = new ArrayList<MessageDto>();

        for(Message iter : messages){
            messageDtos.add(new MessageDto(iter));
        }
        return new ResponseEntity<ArrayList<MessageDto>>(messageDtos, HttpStatus.OK);
    }

}