package fr.vocaltech.tdd.domains.models;

import fr.vocaltech.tdd.services.SolutionFormatter;
import fr.vocaltech.tdd.services.SolutionFormatterImpl;

public record CalculationSolution(Integer solution) {
    private static final SolutionFormatter solutionFormatter = new SolutionFormatterImpl();
    public String formattedSolution() {
        return solutionFormatter.format(solution);
    }
}
