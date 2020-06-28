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
    private String userMessage;

    public UserExperience(ScoringType scoring, String userMessage) {
        this.scoring = scoring;
        this.userMessage = userMessage;
    }

}
