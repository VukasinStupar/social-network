package com.example.isaProject.dto;

import com.example.isaProject.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TrendingDto {
    long countAllPosts;
    long countAllPostsMonth;
    List<PostDto> fiveMostPopularPosts7Days;
    List<PostDto> tenMostPopularAllTime;


}
