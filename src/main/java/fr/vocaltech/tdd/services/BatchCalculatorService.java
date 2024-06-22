package fr.vocaltech.tdd.services;

import fr.vocaltech.tdd.domains.models.CalculationModel;
import fr.vocaltech.tdd.domains.models.CalculationSolution;

import java.util.List;
import java.util.stream.Stream;

public interface BatchCalculatorService {
    List<CalculationSolution> batchCalculate(Stream<String> operationsList);
}
