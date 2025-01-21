package com.example.isaProject.dto;

import com.example.isaProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long id;

    private String name;

    private String surname;

    private String username;

    private String adress;

    private String email;

    private String password;

    private boolean enabled;

    private Timestamp lastPasswordResetDate;


    public UserRequest(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.adress = user.getAdress();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.lastPasswordResetDate = user.getLastPasswordResetDate();
    }


}