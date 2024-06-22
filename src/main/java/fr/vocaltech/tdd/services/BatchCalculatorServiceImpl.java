package fr.vocaltech.tdd.services;

import fr.vocaltech.tdd.domains.models.CalculationModel;
import fr.vocaltech.tdd.domains.models.CalculationSolution;
import fr.vocaltech.tdd.domains.models.CalculationType;

import java.util.List;
import java.util.stream.Stream;

public class BatchCalculatorServiceImpl implements BatchCalculatorService {
    private final CalculatorService calculatorService;
    public BatchCalculatorServiceImpl(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }
    @Override
    public List<CalculationSolution> batchCalculate(Stream<String> operations) {
        return operations
                .map(operation -> calculatorService.calculate(fromText(operation)))
                .toList();
    }

    private CalculationModel fromText(String calculation) {
        String[] parts = calculation.split(" ");

        return new CalculationModel(
                CalculationType.fromSymbol(parts[1]),
                Integer.valueOf(parts[0]),
                Integer.valueOf(parts[2])
        );
    }
}
