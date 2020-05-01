package ar.davinci.edu.domain.dto.fitme.training;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TrainingTotalStadistDTO {

    private String date;
    private Double calories;
    private Double meters;
}
