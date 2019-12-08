package ar.davinci.edu.infraestructure.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class ExerciseSession {

    private Long id;
    private String nutrition;
    private String exercise;

    public ExerciseSession(String exerciseSessionExercise, String exerciseSessionNutrition) {
        this.exercise = exerciseSessionExercise;
        this.nutrition = exerciseSessionNutrition;
    }
}