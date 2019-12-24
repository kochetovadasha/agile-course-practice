package ru.unn.agile.ComplexNumber.Model;

import org.junit.Test;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;

import static org.junit.Assert.assertEquals;

public class DariaFedorovaTest {
    @Test
    public void canAddComplexNumbers() {
        ComplexNumber number1 = new ComplexNumber(2.0, 1.0);
        ComplexNumber number2 = new ComplexNumber(1.0, 1.0);

        ComplexNumber resultNumber = number1.add(number2);

        assertEquals(new ComplexNumber(3.0, 2.0), resultNumber);
    }

    @Test
    public void canAddNegativeComplexNumbers() {
        ComplexNumber number1 = new ComplexNumber(-2.0, -1.0);
        ComplexNumber number2 = new ComplexNumber(-1.0, -1.0);

        ComplexNumber resultNumber = number1.add(number2);

        assertEquals(new ComplexNumber(-3.0, -2.0), resultNumber);
    }

    @Test
    public void canAddZeroComplexNumbers() {
        ComplexNumber number1 = new ComplexNumber(-2.0, 1.0);
        ComplexNumber number2 = new ComplexNumber(0.0, 0.0);

        ComplexNumber resultNumber = number1.add(number2);

        assertEquals(new ComplexNumber(-2.0, 1.0), resultNumber);
    }
}
