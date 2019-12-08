package ar.davinci.edu.infraestructure.model;

import java.util.Set;

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
    private Set<Exercise> exercises;
    private Set<Nutrition> nutritions;
    private String scoring;


    public RoutineTemplate(Set<Exercise> exercises, Set<Nutrition> nutritions, String scoring) {
        this.exercises = exercises;
        this.nutritions = nutritions;
        this.scoring = scoring;
    }

    public RoutineTemplate(Long id, Set<Exercise> exercises, Set<Nutrition> nutritions) {
        this.id = id;
        this.exercises = exercises;
        this.nutritions = nutritions;
    }


}