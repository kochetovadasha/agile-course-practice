package ru.unn.agile.calc.model;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalcTest {

    private final double delta = 0.00000001;
    private StringCalc calc;

    @Before
    public void setUp() {
        calc = new StringCalc();
    }

    @After
    public void tearDown() {
        calc = null;
    }

    @Test
    public void canParseNumber1() {
        var number = calc.result("1");
        assertEquals(1, number, delta);
    }

    @Test
    public void canParseNumber143() {
        var number = calc.result("143");
        assertEquals(143, number, delta);
    }

    @Test
    public void canParseRandomNumber() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100000);
        var number = calc.result(Integer.toString(randomNum));
        assertEquals(randomNum, number, delta);
    }

    @Test
    public void canParseNotInt() {
        var number = calc.result("143.26");
        assertEquals(143.26, number, delta);
    }

    @Test
    public void canParseSumOfTwoNumbers() {
        var number = calc.result("143.26+2352.549");
        assertEquals(143.26 + 2352.549, number, delta);
    }

    @Test
    public void canParseSumOfThreeNumbers() {
        var number = calc.result("143.26+2352.549+25436.25");
        assertEquals(143.26 + 2352.549 + 25436.25, number, delta);
    }

    @Test(expected = NumberFormatException.class)
    public void cantParseSumOfNumbersAndStrings() {
        calc.result("143.26+2352.549+ewffwfe");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantParseNullStrings() {
        calc.result(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantParseEmptyStrings() {
        StringCalc calc = new StringCalc();
        calc.result("");
    }

    @Test
    public void canParseSumAndResidualOfThreeNumbers() {
        var number = calc.result("1+1-1");
        assertEquals(1 + 1 - 1, number, delta);
    }


    @Test
    public void canParseSumAndResidualOfThreeNumbers2() {
        var number = calc.result("-1+1-1");
        assertEquals(-1 + 1 - 1, number, delta);
    }

    @Test
    public void canParseMultiplication() {
        var number = calc.result("5*6");
        assertEquals(5 * 6, number, delta);
    }

    @Test
    public void canParseMultiplicationWithSum() {
        var number = calc.result("-7+5*6");
        assertEquals(-7 + 5 * 6, number, delta);
    }

    @Test
    public void canParseMultiplicationWithSum2() {
        var number = calc.result("-7*5+1+5*6");
        assertEquals(-7 * 5 + 1 + 5 * 6, number, delta);
    }
}
