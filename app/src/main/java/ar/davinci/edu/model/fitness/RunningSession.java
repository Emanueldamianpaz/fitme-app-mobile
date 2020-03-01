package ar.davinci.edu.model.fitness;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class RunningSession {

    private long totalTimeWalk;
    private float distanceCovered;
    private float pace;
    private float speedAvg;
}
