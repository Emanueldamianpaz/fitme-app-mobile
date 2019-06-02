package ar.davinci.edu.infraestructure.model.dto;

import java.util.List;

public class RoutineTemplateDTO {

    int id;
    List<ExerciseDTO> exercises;

    public RoutineTemplateDTO(int id, List<ExerciseDTO> exercises) {
        this.id = id;
        this.exercises = exercises;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDTO> exercises) {
        this.exercises = exercises;
    }
}
