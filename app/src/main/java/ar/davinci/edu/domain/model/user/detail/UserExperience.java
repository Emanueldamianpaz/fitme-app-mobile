package ar.davinci.edu.domain.model.user.detail;

import ar.davinci.edu.domain.types.ScoringType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class UserExperience {

    private Long id;
    private ScoringType scoring;
    private String coachTip;


    public UserExperience(ScoringType scoring) {
        this.scoring = scoring;
    }

}
