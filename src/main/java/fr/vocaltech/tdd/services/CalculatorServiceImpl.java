package fr.vocaltech.tdd.services;

import fr.vocaltech.tdd.domains.Calculator;
import fr.vocaltech.tdd.domains.models.CalculationModel;
import fr.vocaltech.tdd.domains.models.CalculationSolution;

public class CalculatorServiceImpl implements CalculatorService {
    private final Calculator calculator;
    public CalculatorServiceImpl(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public CalculationSolution calculate(CalculationModel calculationModel) {
        CalculationSolution calculationSolution;

        switch(calculationModel.calculationType()) {
            case ADDITION -> {
                Integer solution = calculator.add(calculationModel.leftOperand(), calculationModel.rightOperand());
                calculationSolution = new CalculationSolution(solution);
            }

            case DIVISION -> {
                try {
                    Integer solution = calculator.divide(calculationModel.leftOperand(), calculationModel.rightOperand());
                    calculationSolution = new CalculationSolution(solution);
                } catch(ArithmeticException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            default -> {
                return null;
            }
        }

        return calculationSolution;
    }
}
