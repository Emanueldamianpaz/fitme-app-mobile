package ar.davinci.edu.domain.types;

public enum GoalType {
    UNKNOWN("Desconocido"),
    GAIN_WEIGHT("Ganar peso"),
    LOSS_WEIGHT("Perder peso");

    private final String label;

    GoalType(String label) {
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
