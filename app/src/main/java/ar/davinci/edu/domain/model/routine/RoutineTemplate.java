package ar.davinci.edu.domain.model.routine;

import java.util.Set;

import ar.davinci.edu.domain.model.routine.detail.MealNutrition;
import ar.davinci.edu.domain.model.routine.detail.WorkoutExercise;
import ar.davinci.edu.domain.types.GoalType;
import ar.davinci.edu.domain.types.ScoringType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class RoutineTemplate {

    private Long id;
    private String name;
    private String description;
    private Set<WorkoutExercise> workoutExercises;
    private Set<MealNutrition> mealNutritions;
    private ScoringType scoringSystem;
    private GoalType goalType;


}
