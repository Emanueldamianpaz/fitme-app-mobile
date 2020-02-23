package ar.davinci.edu.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private ExerciseSession userFit;

    public UserInfo(String id) {
        this.id = id;
    }

}