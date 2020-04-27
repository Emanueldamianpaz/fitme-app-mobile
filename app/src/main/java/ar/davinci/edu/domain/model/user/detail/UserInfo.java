package ar.davinci.edu.domain.model.user.detail;

import java.util.Set;

import ar.davinci.edu.domain.dto.fitme.user.UserInfoRequestDTO;
import ar.davinci.edu.domain.model.training.TrainingSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class UserInfo {

    private String id;
    private Double initialWeight;
    private String height;
    private Double currentFat;
    private String frecuencyExercise;
    private UserGoal userGoal;
    private Set<TrainingSession> trainingSession;

    public UserInfo(String id, UserGoal userGoal) {
        this.id = id;
        this.userGoal = userGoal;
    }

    public UserInfo(UserInfoRequestDTO userInfoRequest) {
        this.initialWeight = userInfoRequest.getInitialWeight();
        this.height = userInfoRequest.getHeight();
        this.currentFat = userInfoRequest.getCurrentFat();
        this.frecuencyExercise = userInfoRequest.getFrecuencyExercise();
    }

    public UserInfo(String id, UserInfoRequestDTO userInfoRequest) {
        this.id = id;
        this.initialWeight = userInfoRequest.getInitialWeight();
        this.height = userInfoRequest.getHeight();
        this.currentFat = userInfoRequest.getCurrentFat();
        this.frecuencyExercise = userInfoRequest.getFrecuencyExercise();
    }
}
