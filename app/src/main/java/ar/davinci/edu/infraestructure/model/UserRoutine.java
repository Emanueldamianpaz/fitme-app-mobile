package ar.davinci.edu.infraestructure.model;

import java.util.List;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class UserRoutine {

    private Long id;
    private User user;
    private Scoring scoring;
    private List<Routine> routines;


}