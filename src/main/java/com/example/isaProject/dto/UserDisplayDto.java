package com.example.isaProject.dto;

import com.example.isaProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDisplayDto {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private Long numberOfPosts;

    private Long numberOfFallow;


    public UserDisplayDto(User user, Long numberOfPosts, Long numberOfFallow){
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.numberOfPosts = numberOfPosts;
        this.numberOfFallow = numberOfFallow;

    }

}
