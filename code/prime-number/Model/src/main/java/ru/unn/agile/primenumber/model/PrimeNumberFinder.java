package ru.unn.agile.primenumber.model;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberFinder {

    private int startSpan;
    private int endSpan;

    public PrimeNumberFinder(final int startSpan, final int endSpan) {
        if (startSpan < 0 || endSpan < 0) {
            throw new IllegalArgumentException(
                    "Span's borders should be positive!");
        }
        if (startSpan > endSpan) {
            throw new IllegalArgumentException(
                    "Span's right border should be bigger than left border!");
        }
        this.endSpan = endSpan;
        this.startSpan = startSpan;
    }

    public List<Integer> findNumbers() {
        List<Integer> result = new ArrayList<>();
        int realStart = Math.max(startSpan, 2);
        for (int i = realStart; i < endSpan; i++) {
            if (isOriginalPrimeNumber(i) || isNotDivideNumberWithoutRemainder(i)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isOriginalPrimeNumber(final int number) {
        return (number == Originals.TWO.number
                || number == Originals.THREE.number
                || number == Originals.FIVE.number
                || number == Originals.SEVEN.number);
    }

    private boolean isNotDivideNumberWithoutRemainder(final int number) {
        return (number % Originals.TWO.number != 0
                && number % Originals.THREE.number != 0
                && number % Originals.FIVE.number != 0
                && number % Originals.SEVEN.number != 0);
    }

    enum Originals {
        TWO(2),
        THREE(3),
        FIVE(5),
        SEVEN(7);

        private final int number;

        Originals(final int number) {
            this.number = number;
        }
    }
}
