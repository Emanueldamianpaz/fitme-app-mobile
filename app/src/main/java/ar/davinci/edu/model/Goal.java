package ar.davinci.edu.model;

import ar.davinci.edu.model.types.GoalType;
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