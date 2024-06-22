package fr.vocaltech.tdd.services;

import fr.vocaltech.tdd.domains.models.CalculationModel;
import fr.vocaltech.tdd.domains.models.CalculationSolution;

public interface CalculatorService {
    CalculationSolution calculate(CalculationModel calculationModel);
}
