package ar.davinci.edu.domain.model.user.detail;

import ar.davinci.edu.domain.dto.fitme.scoring.UserExperienceDTO;
import ar.davinci.edu.domain.types.ScoringType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class UserExperience {

    private Long id;
    private ScoringType scoring;
    private String coachTip;

    public UserExperience(UserExperienceDTO scoring) {
        this.scoring = scoring.getScoring();
        this.coachTip = scoring.getCoachTip();
    }

    public UserExperience(ScoringType scoring, String coachTip) {
        this.scoring = scoring;
        this.coachTip = coachTip;
    }

    public UserExperience(Long id, UserExperienceDTO scoring) {
        this.id = id;
        this.scoring = scoring.getScoring();
        this.coachTip = scoring.getCoachTip();
    }
}
