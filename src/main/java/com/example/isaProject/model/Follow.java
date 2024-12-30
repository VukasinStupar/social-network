package com.example.isaProject.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower; // The user who is following

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "followee_id", nullable = false)
    private User followee; // The user being followed

    @Column(name = "follow_creation", nullable = false)
    private LocalDateTime followCreation;
}
