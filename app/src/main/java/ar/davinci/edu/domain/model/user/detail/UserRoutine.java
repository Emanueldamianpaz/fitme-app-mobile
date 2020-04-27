package ar.davinci.edu.domain.model.user.detail;

import java.util.Set;

import ar.davinci.edu.domain.model.routine.RoutineTemplate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class UserRoutine {

    private Long id;
    private Set<UserExperience> userExperiences;
    private RoutineTemplate routineTemplate;

    public UserRoutine(RoutineTemplate routineTemplate) {
        this.routineTemplate = routineTemplate;
    }

    public UserRoutine(Long id, RoutineTemplate routineTemplate) {
        this.id = id;
        this.routineTemplate = routineTemplate;
    }

}
