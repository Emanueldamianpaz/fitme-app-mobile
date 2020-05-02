package ar.davinci.edu.domain.model.training.detail;

import java.io.Serializable;
import java.sql.Timestamp;

import ar.davinci.edu.domain.types.MealNutritionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class NutritionSession implements Serializable {
    private String id;
    private Timestamp timestamp;
    private String name;
    private MealNutritionType type;
    private Double calories;

    public NutritionSession(String name, MealNutritionType type, Double calories) {
        this.name = name;
        this.type = type;
        this.calories = calories;
    }
}
