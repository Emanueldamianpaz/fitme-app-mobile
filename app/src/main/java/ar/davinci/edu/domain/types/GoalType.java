package ar.davinci.edu.domain.types;

import ar.davinci.edu.R;

public enum GoalType {
    UNKNOWN(R.string.UNKNOWN),
    GAIN_WEIGHT(R.string.GAIN_WEIGHT),
    LOSS_WEIGHT(R.string.LOSS_WEIGHT);

    private final int label;

    GoalType(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

}
