package ar.davinci.edu.infraestructure.model;

import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class Routine {

    private Long id;
    private String name;
    private String description;
    private RoutineTemplate routineTemplate;
    private Set<UserRoutine> routineUserList;

}