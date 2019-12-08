package ar.davinci.edu.infraestructure.model;

import ar.davinci.edu.infraestructure.security.FitmeUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class User {

    private String id;
    private UserInfo userInfo;
    private String name;
    private String lastName;
    private String email;
    private String picture;
    private String nickname;
    private String gender;
    private UserRoutine userRoutine;

    public User(FitmeUser user) {
        this.id = user.getId();
        this.userInfo = new UserInfo(user.getId());
        this.name = user.getName();
        this.email = user.getEmail();
        this.nickname = user.getNickname();

    }
}