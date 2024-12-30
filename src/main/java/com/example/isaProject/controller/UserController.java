package com.example.isaProject.controller;

import com.example.isaProject.dto.PostDto;
import com.example.isaProject.dto.UserDisplayDto;
import com.example.isaProject.dto.UserDto;
import com.example.isaProject.dto.UserSearchDto;
import com.example.isaProject.model.User;
import com.example.isaProject.securityAuth.TokenBasedAuthentication;
import com.example.isaProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("user/{userId}")
    // @PreAuthorize("hasRole('ADMIN')")
    public User loadById(@PathVariable Long userId) {
        return this.userService.findById(userId);
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
    public ResponseEntity<ArrayList<UserDto>> getAll() {
        List<User> user = userService.findAll();

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

        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


}