package ar.davinci.edu.domain.types;

import ar.davinci.edu.R;

public enum WorkoutExerciseType {
    AEROBIC(R.string.AEROBIC),
    FLEXIBILITY(R.string.FLEXIBILITY),
    STRENGTH(R.string.STRENGTH);

    private final int label;

    WorkoutExerciseType(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }
}