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
public class ExerciseSession {

    private Long id;
    private String nutrition;
    private String exercise;

    public ExerciseSession(String exerciseSessionExercise, String exerciseSessionNutrition) {
        this.exercise = exerciseSessionExercise;
        this.nutrition = exerciseSessionNutrition;
    }
}