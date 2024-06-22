package fr.vocaltech.tdd;

import fr.vocaltech.tdd.domains.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CalculatorTest {
    private Calculator calculatorUnderTest; // SUT
    private static Instant startTime;

    @BeforeAll
    static void setupAll() {
        System.out.println("setupAll()...");
        startTime = Instant.now();
    }

    @AfterAll
    static void teardownAll() {
        System.out.println("teardownAll()...");
        Instant endTime = Instant.now();
        long duration = Duration.between(startTime, endTime).toMillis();
        System.out.println(MessageFormat.format("Tests Duration: {0} ms", duration));
    }

    @BeforeEach
    void setup() {
        calculatorUnderTest = new Calculator();
        System.out.println("setup()...");
    }

    @AfterEach
    void teardown() {
        System.out.println("teardown()...");
        calculatorUnderTest = null;
    }

    @Test
    void givenTwoIntNumbers_whenAdd_thenOk() {
        // ARRANGE
        int a = 2;
        int b = 3;

        // ACT
        int somme = calculatorUnderTest.add(a, b);

        // ASSERT
        assertThat(somme).isEqualTo(5);
    }

    @Test
    void givenName_whenInitCalc_thenGetNameOk() {
        // Given
        String name = "myCalc";

        // When
        Calculator namedCalculator = new Calculator(name);

        // Then
        assertThat(namedCalculator).isInstanceOf(Calculator.class);
        assertThat(namedCalculator.getName()).isEqualTo(name);
    }

    @Test
    @Disabled("Disabled")
    void givenTwoDoubleNumbers_whenAdd_thenOk() {
        // Given
        double a = 2.3;
        double b = 3.6;

        // When
        double sum = calculatorUnderTest.add(a, b);

        // Then
    }

    @Test
    void givenTwoIntNumbers_whenMultiply_thenOk() {
        // ARRANGE
        int a = 3;
        int b = 6;

        // ACT
        int multiply = calculatorUnderTest.multiply(a, b);

        // ASSERT
        assertThat(multiply).isEqualTo(18);
    }

    @ParameterizedTest(name = "{0} x 0 is equal to zero !")
    @ValueSource(ints = {3, 6, 9})
    void givenNumbers_whenMultiplyWithZero_thenReturnZero(int args) {
        // ARRANGE

        // ACT
        int multiplyZero = calculatorUnderTest.multiply(args, 0);

        // ASSERT
        assertThat(multiplyZero).isZero();
    }

    @ParameterizedTest(name = "{0} + {1} doit etre egal a {2}")
    @CsvSource({"1,1,2", "2,3,5", "3,6,9"})
    void givenCsvSourceArgs_whenSum_thenOk(int arg1, int arg2, int expectedRes) {
        // ARRANGE

        // ACT
        int sum = calculatorUnderTest.add(arg1, arg2);

        // ASSERT
        assertThat(sum).isEqualTo(expectedRes);
    }

    @Test
    void givenDate_whenCheckIsAfter_thenThrows() {
        // ARRANGE
        Calendar futureDate = Calendar.getInstance();
        futureDate.set(2024, 9, 5);

        // ACT

        // ASSERT

        // -- when
        Throwable thrown = catchThrowable(() -> {
            assertThat(futureDate.toInstant())
                    .isBefore(Instant.now());
        });

        // -- then
        assertThat(thrown).isInstanceOf(AssertionError.class);
    }

    @Test
    void givenPositiveNumber_whenDigitsSet_thenReturnListOfDigits() {
        // Arrange (given)
        int number = 95897;

        // Act (when)
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        // Assert (then)
        assertThat(actualDigits).containsExactlyInAnyOrder(5, 7, 8, 9);
    }

    @Test
    void givenNegativeNumber_whenDigitsSet_thenReturnListOfDigits() {
        // Arrange (given)
        int number = -124432;

        // Act (when)
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        // Assert (then)
        assertThat(actualDigits).containsExactlyInAnyOrder(1, 2, 3, 4);
    }

    @Test
    void givenZero_whenDigitsSet_thenReturnZero() {
        // Arrange (given)
        int number = 0;

        // Act (when)
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        // Assert (then)
        assertThat(actualDigits).containsExactly(0);
    }

    @Test
    void givenTwoIntNumbers_whenDivide_thenOk() {
        // given
        int a = 6;
        int b = 3;

        // when
        int result = calculatorUnderTest.divide(a, b);

        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void givenOneIntNumberAndZero_whenDivide_thenThrowArithmeticException() {
        // given
        int a = 6;
        int b = 0;

        // when
        assertThrows(ArithmeticException.class, () -> calculatorUnderTest.divide(a, b));

        // then
    }
}
