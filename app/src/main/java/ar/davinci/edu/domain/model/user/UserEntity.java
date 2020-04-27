package ar.davinci.edu.domain.model.user;

import java.util.Set;

import ar.davinci.edu.domain.model.user.detail.UserInfo;
import ar.davinci.edu.domain.model.user.detail.UserRoutine;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class UserEntity {
    private String id;
    private UserInfo userInfo;
    private Set<UserRoutine> userRoutines;
    private String name;
    private String lastName;
    private String email;
    private String picture;
    private String nickname;
    private String genre;
    private String role;
}
