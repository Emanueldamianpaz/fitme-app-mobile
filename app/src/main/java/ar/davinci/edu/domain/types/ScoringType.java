package ar.davinci.edu.domain.types;

public enum ScoringType {
    GOOD("Bueno"),
    REGULAR("Regular"),
    BAD("Malo"),
    UNKNOWN("Desconocido");

    private final String label;

    ScoringType(String label) {
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
