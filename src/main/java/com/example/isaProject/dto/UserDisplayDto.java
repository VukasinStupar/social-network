package com.example.isaProject.dto;

import com.example.isaProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDisplayDto {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private int numberOfPosts;

    private int numberOfFollowers;

    private int numberOfFollowee;


    public UserDisplayDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.numberOfPosts = user.getNumberOfPosts();
        this.numberOfFollowers = user.getNumberOfFollowers();
        this.numberOfFollowee = user.getNumberOfFollowees();

    }

}
