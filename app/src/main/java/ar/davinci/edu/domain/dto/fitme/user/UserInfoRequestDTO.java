package ar.davinci.edu.domain.dto.fitme.user;

import ar.davinci.edu.domain.model.user.detail.UserGoal;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserInfoRequestDTO {

    private Double initialWeight;
    private String height;
    private Double currentFat;
    private String frecuencyExercise;
    private UserGoal goal;
}
