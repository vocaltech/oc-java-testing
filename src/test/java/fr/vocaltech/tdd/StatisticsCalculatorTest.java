package fr.vocaltech.tdd;

import fr.vocaltech.tdd.domains.StatisticsCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StatisticsCalculatorTest {
    IntSummaryStatistics summaryStatistics;
    IntSummaryStatistics spySummaryStatistics;
    StatisticsCalculator underTest;

    @BeforeEach
    void setupEach() {
        spySummaryStatistics = spy();
        underTest = new StatisticsCalculator(spySummaryStatistics);
    }

    @AfterEach
    void tearDownEach() {
        underTest = null;
        summaryStatistics = null;
        spySummaryStatistics = null;
    }

    @Test
    void givenStreamOfIntegers_whenAverage_thenShouldReturnTheMean () {
        // GIVEN
        final Stream<Integer> samples = Stream.of(2, 8, 5, 3, 7);
        summaryStatistics = new IntSummaryStatistics();
        underTest = new StatisticsCalculator(summaryStatistics);

        // WHEN
        final Integer result = underTest.average(samples);

        // THEN
        assertThat(result).isEqualTo(5);
    }

    @Test
    void givenStreamOfIntegers_whenAverage_thenCallsWithCorrectArguments() {
        // GIVEN
        final Stream<Integer> samples = Stream.of(2, 8, 5, 3, 7);
        ArgumentCaptor<Integer> samplesCaptor = ArgumentCaptor.forClass(Integer.class);

        // WHEN
        underTest.average(samples);

        // THEN
        verify(spySummaryStatistics, times(5)).accept(samplesCaptor.capture());
        List<Integer> capturedStream = samplesCaptor.getAllValues();

        assertThat(capturedStream).containsExactly(2, 8, 5, 3, 7);
    }
}
