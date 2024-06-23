package fr.vocaltech.tdd.domains;

import java.util.IntSummaryStatistics;
import java.util.stream.Stream;

public class StatisticsCalculator {
    private final IntSummaryStatistics summaryStatistics;
    public StatisticsCalculator(IntSummaryStatistics summaryStatistics) {
        this.summaryStatistics = summaryStatistics;
    }
    public Integer average(Stream<Integer> samples) {
        samples.forEach(summaryStatistics::accept);
        return (int) summaryStatistics.getAverage();
    }
}
