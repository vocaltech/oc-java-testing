package fr.vocaltech.tdd;

import java.util.HashSet;
import java.util.Set;

public class Calculator {
    private String name = "";

    public Calculator() {}
    public Calculator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }
    public Set<Integer> digitsSet(int number) {
        Set<Integer> digitsSet = new HashSet<>();
        String numberStr = String.valueOf(number);

        for (int i=0; i<numberStr.length(); i++) {
            if (numberStr.charAt(i) != '-')
                digitsSet.add(Integer.parseInt(String.valueOf(numberStr.charAt(i))));
        }

        return digitsSet;
    }
}
