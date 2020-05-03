package ar.davinci.edu.domain.types;

import ar.davinci.edu.R;

public enum DifficultyType {
    BEGINNER(R.string.BEGINNER),
    BASIC(R.string.BASIC),
    INTERMEDIATE(R.string.INTERMEDIATE),
    ADVANCED(R.string.ADVANCED),
    EXPERT(R.string.EXPERT);

    private final int label;

    DifficultyType(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }


}
