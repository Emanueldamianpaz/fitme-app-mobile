package ar.davinci.edu.domain.types;

public enum MealNutritionType {
    BREAKFAST("Desayuno"),
    // The first meal of the day. Usually around 6am-9am.

    BRUNCH("Brunch"),
    // A meal eaten in the late morning, instead of Breakfast and Lunch. (informal)

    ELEVENSES("Colación"),
    // A snack (for example, biscuits and coffee). Around 11am. (BrE, informal)

    LUNCH("Almuerzo"),
    // A meal in the middle of the day. Usually around noon or 1pm.

    TEA("Té"),
    // A light afternoon meal of sandwiches, cakes etc, with a drink of tea. Around 4pm.
    // It is also sometimes called afternoon tea (mainly BrE).
    // The word tea can also refer to a cooked evening meal, around 6pm (BrE)

    SUPPER("Precena"),
    // A light or informal evening meal. Around 6pm-7pm.

    DINNER("Cena");
    // The main meal of the day, eaten either in the middle of the day or in the evening.
    // Usually when people say "dinner", they mean an evening meal, around 7pm-9pm.}

    private final String label;

    MealNutritionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

}
