package ar.davinci.edu.infraestructure.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class UserFit {

    private Long id;
    private String nutrition;
    private String running;
    private String exercise;

    public UserFit(String userFitExercise, String userFitNutrition) {
        this.exercise = userFitExercise;
        this.nutrition = userFitNutrition;
    }

    public UserFit(Long id, String userFitExercise, String userFitNutrition) {
        this.id = id;
        this.exercise = userFitExercise;
        this.nutrition = userFitNutrition;
    }


}