package ar.davinci.edu.infraestructure.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class UserInfo {

    private String id;
    private Double weight;
    private String height;
    private Double currentFat;
    private String frecuencyExercise;
    private Goal goal;
    private UserFit userFit;

    public UserInfo(String id) {
        this.id = id;
    }

}