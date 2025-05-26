package com.example.isaProject.controller;

import com.example.isaProject.dto.*;
import com.example.isaProject.model.User;
import com.example.isaProject.securityAuth.TokenBasedAuthentication;
import com.example.isaProject.service.GroupChatService;
import com.example.isaProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupChatService groupChatService;


    @GetMapping("user/{userId}")
    // @PreAuthorize("hasRole('ADMIN')")
    public User loadById(@PathVariable Long userId) {
        return this.userService.findById(userId);
    }

    @GetMapping("userProfile/{userId}")
    public ResponseEntity<UserProfileDto> userProfile(@PathVariable Long userId) {
        User user = userService.findById(userId);
        UserProfileDto updto = new UserProfileDto(user);

        return new ResponseEntity<UserProfileDto>(updto, HttpStatus.OK);
    }



    @GetMapping("/whoami")
    @PreAuthorize("hasRole('USER')")
    public User user(Principal user) {
        return this.userService.findByUsername(user.getName());
    }

    @GetMapping("/foo")
    public Map<String, String> getFoo() {
        Map<String, String> fooObj = new HashMap<>();
        fooObj.put("foo", "bar");
        return fooObj;
    }

    @GetMapping
    public ResponseEntity<ArrayList<UserDto>> getAll(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "30") int size) {
        List<User> user = userService.findAll(page, size);

        ArrayList<UserDto> dtos = new ArrayList<UserDto>();
        for (User us : user) {
            dtos.add(new UserDto(us));
        }
        return new ResponseEntity<ArrayList<UserDto>>(dtos, HttpStatus.OK);

    }
    @GetMapping("/getLoggedUser")
    public ResponseEntity<UserDto> getLoggedUser(Principal user) {

        User loggedUser = this.userService.findByUsername(user.getName());
        UserDto userDto = new UserDto(loggedUser);

        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @PostMapping("/send-notifications")
    public ResponseEntity<String> sendNotificationsToInactiveUsers() {
        try {
            userService.sendNotificationsToInactiveUsers();
            return ResponseEntity.ok("Notifications sent to inactive users successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send notifications: " + e.getMessage());
        }

    }


    @GetMapping("/findUsersByAttributes")
    public ResponseEntity<List<UserDisplayDto>> findUsersByAttributes(@RequestBody UserSearchDto userSearchDto) {

        List<UserDisplayDto> dtos = userService.findUsersByAttributes(userSearchDto);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/allUsersGroup/{groupChatId}")
    public ResponseEntity<List<UserDto>> findUsersByAttributes(
            @PathVariable Long groupChatId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        List<User> users = groupChatService.findAllUsersInGroup(groupChatId, page, size);
        List<UserDto> userDtos = new ArrayList<>();
        for(User iter : users){
            userDtos.add(new UserDto(iter));
        }
        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchByParam(@RequestParam(required = false) String param) {
        List<User> users = userService.searchByParam(param != null ? param : "");
        List<UserDto> dto = new ArrayList<>();

        for(User iter :users){
            dto.add(new UserDto(iter));
        }
        return new ResponseEntity<List<UserDto>>(dto, HttpStatus.OK);
    }



}