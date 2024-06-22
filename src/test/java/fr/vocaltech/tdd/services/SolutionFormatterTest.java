package fr.vocaltech.tdd.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionFormatterTest {
    private static SolutionFormatter solutionFormatter;

    @BeforeAll
    static void setup() {
        solutionFormatter = new SolutionFormatterImpl();
    }

    @Test
    void givenIntNumber_whenFormat_thenOk() {
        // given
        int number = 350000;

        // when
        String formatted = solutionFormatter.format(number);

        // then
        assertThat(formatted).isEqualTo("350 000");
    }
}
