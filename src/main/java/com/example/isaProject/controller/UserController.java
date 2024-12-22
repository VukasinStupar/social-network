package com.example.isaProject.controller;

import com.example.isaProject.dto.UserDto;
import com.example.isaProject.model.User;
import com.example.isaProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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




}
