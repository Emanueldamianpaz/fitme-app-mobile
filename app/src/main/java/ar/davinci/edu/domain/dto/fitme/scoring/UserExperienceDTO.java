package ar.davinci.edu.domain.dto.fitme.scoring;

import ar.davinci.edu.domain.types.ScoringType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserExperienceDTO {

    private ScoringType scoring;
    private String coachTip;
}
