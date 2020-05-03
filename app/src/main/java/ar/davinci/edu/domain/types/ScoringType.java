package ar.davinci.edu.domain.types;

import ar.davinci.edu.R;

public enum ScoringType {
    GOOD(R.string.GOOD),
    REGULAR(R.string.REGULAR),
    BAD(R.string.BAD),
    UNKNOWN(R.string.UNKNOWN);

    private final int label;

    ScoringType(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }
}
