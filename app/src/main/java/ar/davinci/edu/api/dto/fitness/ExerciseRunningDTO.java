package ar.davinci.edu.api.dto.fitness;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExerciseRunningDTO {

    private List<RunningSessionDTO> my_sessions;
    private Double totalKilometersRunned;


}
