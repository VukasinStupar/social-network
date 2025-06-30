package com.example.isaProject.dto;

import com.example.isaProject.model.Follow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {

    private Long id;

    private Long follower; // ID korisnika koji prati

    private Long followee; // ID korisnika koji je praćen

    private int numberOfFollowers;  // Broj ljudi koji prate followee-a
    private int numberOfFollowees;  // Broj ljudi koje follower prati

    public FollowDto(Follow follow) {
        this.id = follow.getId();

        // Ovde treba da postaviš tačne ID-jeve korisnika
        if (follow.getFollower() != null) {
            this.follower = follow.getFollower().getId();
            this.numberOfFollowees = follow.getFollower().getNumberOfFollowees();
        }

        if (follow.getFollowee() != null) {
            this.followee = follow.getFollowee().getId();
            this.numberOfFollowers = follow.getFollowee().getNumberOfFollowers();
        }
    }
}
