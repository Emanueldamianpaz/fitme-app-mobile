package ar.davinci.edu.domain.model.training.detail;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class RunningSession {

    private long totalTimeWalk;
    private float distanceCovered;
    private float pace;
    private float speedAvg;
}
