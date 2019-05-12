package ar.davinci.edu.infraestructure.model;

import lombok.*;

import java.util.Set;

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