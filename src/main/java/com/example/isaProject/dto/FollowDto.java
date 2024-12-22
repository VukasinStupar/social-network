package com.example.isaProject.dto;

import com.example.isaProject.model.Follow;
import com.example.isaProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {

    private Long id;

    private Long follower; // The user who is following

    private Long followee; // The user being followed

    private Long countOfFolloweers;

    public FollowDto(Follow follow){
        this.id = follow.getId();
        this.followee = follow.getId();
        this.follower = follow.getId();


    }

}
