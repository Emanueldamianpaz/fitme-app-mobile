package ar.davinci.edu.domain.types;

import ar.davinci.edu.R;

public enum MealNutritionType {
    BREAKFAST(R.string.BREAKFAST),
    // The first meal of the day. Usually around 6am-9am.

    BRUNCH(R.string.BRUNCH),
    // A meal eaten in the late morning, instead of Breakfast and Lunch. (informal)

    ELEVENSES(R.string.ELEVENSES),
    // A snack (for example, biscuits and coffee). Around 11am. (BrE, informal)

    LUNCH(R.string.LUNCH),
    // A meal in the middle of the day. Usually around noon or 1pm.

    TEA(R.string.TEA),
    // A light afternoon meal of sandwiches, cakes etc, with a drink of tea. Around 4pm.
    // It is also sometimes called afternoon tea (mainly BrE).
    // The word tea can also refer to a cooked evening meal, around 6pm (BrE)

    SUPPER(R.string.SUPPER),
    // A light or informal evening meal. Around 6pm-7pm.

    DINNER(R.string.DINNER);
    // The main meal of the day, eaten either in the middle of the day or in the evening.
    // Usually when people say "dinner", they mean an evening meal, around 7pm-9pm.}

    private final int label;

    MealNutritionType(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

}
