package ar.davinci.edu.domain.model.training;

import java.util.List;

import ar.davinci.edu.domain.model.training.detail.ExerciseRunningSession;
import ar.davinci.edu.domain.model.training.detail.NutritionSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TrainingSession {

    private Long id;
    private String date;
    private List<NutritionSession> nutritionSessions;
    private List<ExerciseRunningSession> runningSessions;

}
