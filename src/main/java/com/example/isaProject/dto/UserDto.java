package com.example.isaProject.dto;

import com.example.isaProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String adress;

    private String email;

    private String password;

    private Timestamp lastPasswordResetDate;

    private boolean enabled;



    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.adress = user.getAdress();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.lastPasswordResetDate = user.getLastPasswordResetDate();
        this.enabled = user.isEnabled();
    }

    public User mapTo(){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setAdress(adress);
        user.setEmail(email);
        user.setPassword(password);
        user.setLastPasswordResetDate(lastPasswordResetDate);
        user.setEnabled(enabled);
        return user;
    }


}
