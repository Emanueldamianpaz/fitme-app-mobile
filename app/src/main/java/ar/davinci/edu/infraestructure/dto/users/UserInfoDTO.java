package ar.davinci.edu.infraestructure.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserInfoDTO {

    private Double initialWeight;
    private String height;
    private Double currentFat;
    private String frecuencyExercise;

}
