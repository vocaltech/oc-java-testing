package fr.vocaltech.tdd.services;

import fr.vocaltech.tdd.domains.Calculator;
import fr.vocaltech.tdd.domains.models.CalculationModel;
import fr.vocaltech.tdd.domains.models.CalculationType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CalculatorServiceTest {
    Calculator mockCalculator;
    CalculatorService classUnderTest;

    @BeforeEach
    void setupEach() {
        mockCalculator = mock();
        classUnderTest = new CalculatorServiceImpl(mockCalculator);
    }
    @AfterEach
    void tearDownEach() {
        mockCalculator = null;
        classUnderTest = null;
    }
    @Test
    void givenMockCalculatorAddition_whenCalculate_thenShouldUseCalculator() {
        // given
        when(mockCalculator.add(1, 2)).thenReturn(3);

        // when
        CalculationModel calculationModel = new CalculationModel(CalculationType.ADDITION, 1, 2);
        Integer result = classUnderTest
                .calculate(calculationModel)
                .solution();

        // then
        verify(mockCalculator).add(1, 2);
        assertThat(result).isEqualTo(3);
    }

    @Test
    void givenMockCalculatorDivision_whenCalculate_thenShouldUseCalculator() {
        // given
        when(mockCalculator.divide(6, 3)).thenReturn(2);

        // when
        CalculationModel calculationModel = new CalculationModel(CalculationType.DIVISION, 6, 3);
        Integer result = classUnderTest
                .calculate(calculationModel)
                .solution();

        // then
        verify(mockCalculator).divide(6, 3);
        assertThat(result).isEqualTo(2);
    }

    @Test
    void givenMockCalculatorDivisionByZero_whenCalculate_thenShouldThrowIllegalArgumentException() {
        // given
        when(mockCalculator.divide(3, 0)).thenThrow(ArithmeticException.class);

        // when
        CalculationModel calculationModel = new CalculationModel(CalculationType.DIVISION, 3, 0);
        assertThrows(IllegalArgumentException.class, () -> classUnderTest.calculate(calculationModel));

        // then
        verify(mockCalculator).divide(3, 0);
    }

    @Test
    void givenMockCalculatorAddition_whenFormatSolution_thenShouldUseSolutionFormatter() {
        // given
        when(mockCalculator.add(360000, 350)).thenReturn(360350);

        // when
        CalculationModel calculationModel = new CalculationModel(CalculationType.ADDITION, 360000, 350);
        String result = classUnderTest
                .calculate(calculationModel)
                .formattedSolution();

        // then
        assertThat(result).isEqualTo("360 350");
    }
}
