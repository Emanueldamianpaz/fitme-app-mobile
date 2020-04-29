package ar.davinci.edu.domain.model.user.detail;

import ar.davinci.edu.domain.types.GoalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class UserGoal {

    private String id;

    private GoalType type;

    private Double goalFat;

    public UserGoal(String id) {
        this.id = id;
        this.type = GoalType.UNKNOWN;
        this.goalFat = 0.0;
    }

}
