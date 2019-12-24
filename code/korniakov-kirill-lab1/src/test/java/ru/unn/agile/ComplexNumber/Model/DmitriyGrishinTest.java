package ru.unn.agile.ComplexNumber.Model;

import org.junit.Test;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DmitriyGrishinTest {

    @Test
    public void canCreateComplexNumberBasedOnNumbers() {
        var realPartNumber = 1.2;
        var imaginaryPartNumber = 3.4;
        ComplexNumber complexNumber = new ComplexNumber(realPartNumber, imaginaryPartNumber);
        assertNotNull(complexNumber);
    }

    @Test
    public void canCreateComplexNumberBasedOnStringPresentation() {
        var realPartString = "1.2";
        var imaginaryPartString = "3.4";
        ComplexNumber complexNumber = new ComplexNumber(realPartString, imaginaryPartString);
        assertEquals(new ComplexNumber(1.2, 3.4), complexNumber);
    }

    @Test
    public void canGetStringPresentation() {
        ComplexNumber complexNumber = new ComplexNumber(1.2, 3.4);
        var expectedString = "1.2 + 3.4i";
        assertEquals(expectedString, complexNumber.toString());
    }
}
