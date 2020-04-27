package ar.davinci.edu.domain.model.training.detail;

import java.io.Serializable;
import java.sql.Timestamp;

import ar.davinci.edu.domain.types.ScoringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ExerciseRunningSession implements Serializable {
    private String id;
    private Timestamp timestamp;
    private ScoringType scoring;
    private RunningSession runningSession;

    public ExerciseRunningSession(ScoringType scoring, RunningSession runningSession) {
        this.scoring = scoring;
        this.runningSession = runningSession;
    }
}
