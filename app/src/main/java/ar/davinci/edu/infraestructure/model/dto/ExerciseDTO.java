package ar.davinci.edu.infraestructure.model.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class ExerciseDTO {
    private RunningDTO runningSession;
    private Double kilometersRunned;
}
