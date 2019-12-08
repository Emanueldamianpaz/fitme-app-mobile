package ar.davinci.edu.infraestructure.model;

import java.util.List;

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
    private User user;
    private Scoring scoring;
    private List<Routine> routines;


}