package ar.davinci.edu.infraestructure.dto.users;

import java.util.Set;

import ar.davinci.edu.infraestructure.model.Routine;
import ar.davinci.edu.infraestructure.model.Scoring;
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