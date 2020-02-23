package ar.davinci.edu.api.dto.users;

import java.util.Set;

import ar.davinci.edu.model.Routine;
import ar.davinci.edu.model.Scoring;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class UserRoutineDTO {

    private Long id;
    private Scoring scoring;
    private Set<Routine> routine;

}