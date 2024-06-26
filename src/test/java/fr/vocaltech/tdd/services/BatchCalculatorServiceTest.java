package fr.vocaltech.tdd.services;

import fr.vocaltech.tdd.domains.Calculator;
import fr.vocaltech.tdd.domains.models.CalculationModel;
import fr.vocaltech.tdd.domains.models.CalculationSolution;
import fr.vocaltech.tdd.domains.models.CalculationType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class BatchCalculatorServiceTest {
    private CalculatorService mockCalculatorService;
    private BatchCalculatorService batchCalculatorServiceNoMock;
    private BatchCalculatorService batchCalculatorService;

    @BeforeEach
    void setupEach() {
        // no mock
        batchCalculatorServiceNoMock = new BatchCalculatorServiceImpl(new CalculatorServiceImpl(new Calculator()));

        // mock section
        mockCalculatorService = mock();
        batchCalculatorService = new BatchCalculatorServiceImpl(mockCalculatorService);
    }

    @AfterEach
    void tearDownEach() {
        batchCalculatorServiceNoMock = null;
        mockCalculatorService = null;
        batchCalculatorService = null;
    }

    @Test
    void givenOperationsList_whenBatchCalculate_thenReturnsCorrectAnswerList() {
        // GIVEN
        Stream<String> operations = Stream.of("3 + 4", "9 / 3");

        // WHEN
        List<CalculationSolution> solutions = batchCalculatorServiceNoMock.batchCalculate(operations);

        // THEN
        assertThat(solutions)
                .extracting(CalculationSolution::solution)
                .containsExactly(7, 3);
    }

    @Test
    void givenOperationsList_whenBatchCalculate_thenCallsServiceWithCorrectArguments() {
        // GIVEN
        Stream<String> operations = Stream.of("3 + 4", "9 / 3");
        final ArgumentCaptor<CalculationModel> calculationModelCaptor = ArgumentCaptor.forClass(CalculationModel.class);

        // WHEN
        batchCalculatorService.batchCalculate(operations);

        // THEN
        verify(mockCalculatorService, times(2)).calculate(calculationModelCaptor.capture());

        final List<CalculationModel> calculationModels = calculationModelCaptor.getAllValues();
        assertThat(calculationModels)
                .extracting(CalculationModel::leftOperand, CalculationModel::calculationType, CalculationModel::rightOperand)
                .containsExactly(
                        tuple(3, CalculationType.ADDITION, 4),
                        tuple(9, CalculationType.DIVISION, 3)
                );
    }

    @Test
    void givenOperationsList_whenBatchCalculate_thenCallsServiceAndReturnsAnswer() {
        // GIVEN
        Stream<String> operations = Stream.of("3 + 4", "9 / 3");

        when(mockCalculatorService.calculate(any(CalculationModel.class)))
                .then(invocation -> {
                    CalculationSolution calculationSolution = null;
                    CalculationModel calculationModel = invocation.getArgument(0, CalculationModel.class);
                    switch (calculationModel.calculationType()) {
                        case ADDITION -> calculationSolution = new CalculationSolution(7);
                        case DIVISION -> calculationSolution = new CalculationSolution(3);
                    }
                    return calculationSolution;
                });

        //  WHEN
        List<CalculationSolution> solutions = batchCalculatorService.batchCalculate(operations);

        // THEN
        verify(mockCalculatorService, times(2)).calculate(any(CalculationModel.class));
        assertThat(solutions)
                .extracting(CalculationSolution::solution)
                .containsExactly(7, 3);
    }
}
