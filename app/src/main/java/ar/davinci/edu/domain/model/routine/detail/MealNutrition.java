package ar.davinci.edu.domain.model.routine.detail;

import ar.davinci.edu.domain.types.MealNutritionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Builder
public class MealNutrition {

    private Long id;
    private String name;
    private MealNutritionType type;
    private Double calories;
}
