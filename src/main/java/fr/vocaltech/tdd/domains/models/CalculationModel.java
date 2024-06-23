package fr.vocaltech.tdd.domains.models;

public record CalculationModel(CalculationType calculationType, Integer leftOperand, Integer rightOperand) {
    public static CalculationModel fromText(String calculation) {
        String[] parts = calculation.split(" ");

        return new CalculationModel(
                CalculationType.fromSymbol(parts[1]),
                Integer.valueOf(parts[0]),
                Integer.valueOf(parts[2])
        );
    }
}
