package ar.davinci.edu.api.dto.fitness;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RunningSessionDTO {

    private long totalTimeWalk;
    private float distanceCovered;
    private float pace;
    private float speedAvg;

}
