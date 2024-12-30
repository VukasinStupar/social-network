package com.example.isaProject.dto;

import com.example.isaProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String userName;

    private Long postFrom;
    private Long postTo;

    private Long followFrom;
    private Long followTo;

    private int pageFrom;
    private int pageTo;

    public UserSearchDto(User user, Long postFrom, Long postTo, Long followFrom, Long followTo, int pageFrom, int pageTo){
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.userName = user.getUsername();

        this.pageFrom = pageFrom;
        this.pageTo = pageTo;
        this.followFrom = followFrom;
        this.followTo = followTo;
        this.postFrom = postFrom;
        this.postTo = postTo;
    }


}
