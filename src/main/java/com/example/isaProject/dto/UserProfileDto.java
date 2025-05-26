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

public class UserProfileDto {

    private Long id;

    private String name;

    private String surname;

    private String username;

    private int numberOfFollowers;

    private int numberOfFollowees;

    private int numberOdPosts;

    public UserProfileDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.numberOfFollowers = user.getNumberOfFollowers();
        this.numberOfFollowees = user.getNumberOfFollowees();
        this.numberOdPosts = user.getNumberOfPosts();
    }
}
