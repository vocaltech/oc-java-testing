package fr.vocaltech.tdd.services;

import fr.vocaltech.tdd.domains.models.CalculationModel;
import fr.vocaltech.tdd.domains.models.CalculationSolution;

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
                 .map(operation -> calculatorService.calculate(CalculationModel.fromText(operation)))
                 .toList();
    }
}
