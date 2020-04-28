package ar.davinci.edu.domain.dto.fitme.training;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TrainningTotalStadistDTO {

    private String date;
    private Double calories;
    private Double meters;
}
