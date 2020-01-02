package ar.davinci.edu.infraestructure.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserInfoLightDTO {

    private String id;
    private UserInfoDTO userInfo;
    private String name;
    private String lastName;
    private String email;
    private String picture;
    private String nickname;
    private String genre;
    private String role;
}
