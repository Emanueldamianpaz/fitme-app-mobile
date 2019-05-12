package ar.davinci.edu.infraestructure.model;

import ar.davinci.edu.infraestructure.model.types.GoalType;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class Goal {

    private Long id;

    private GoalType type;

    private Double goalFat;


}