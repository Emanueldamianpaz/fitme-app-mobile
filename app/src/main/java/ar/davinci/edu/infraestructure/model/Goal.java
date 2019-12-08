package ar.davinci.edu.infraestructure.model;

import ar.davinci.edu.infraestructure.model.types.GoalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class Goal {

    private Long id;

    private GoalType type;

    private Double goalFat;


}