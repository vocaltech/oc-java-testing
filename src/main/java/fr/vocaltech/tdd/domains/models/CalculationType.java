package fr.vocaltech.tdd.domains.models;

public enum CalculationType {
    ADDITION,
    DIVISION;

    public static CalculationType fromSymbol(String symbol) {
        switch (symbol) {
            case "+" -> {
                return  ADDITION;
            }
            case "/" -> {
                return DIVISION;
            }
            default -> throw new IllegalStateException("Unexpected value: " + symbol);
        }
    }
}
