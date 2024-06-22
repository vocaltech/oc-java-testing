package fr.vocaltech.tdd.services;

import java.util.Locale;

public class SolutionFormatterImpl implements SolutionFormatter {
    @Override
    public String format(int number) {
        return String.format(Locale.US, "%,d", number)
                .replace(',', ' ');
    }
}
