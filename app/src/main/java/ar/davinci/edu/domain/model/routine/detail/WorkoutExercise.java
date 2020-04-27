package ar.davinci.edu.domain.model.routine.detail;

import ar.davinci.edu.domain.types.DifficultyType;
import ar.davinci.edu.domain.types.WorkoutExerciseType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class WorkoutExercise {

    private Long id;
    private String name;
    private WorkoutExerciseType type;
    private String description;
    private DifficultyType difficulty;

}
