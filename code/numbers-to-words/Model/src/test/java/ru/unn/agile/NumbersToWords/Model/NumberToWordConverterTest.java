package ru.unn.agile.NumbersToWords.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberToWordConverterTest {

    @Test
    public void canConvertOne() {
        String word = NumberToWordConverter.toWord(1);

        assertEquals("one", word);
    }

    @Test
    public void canConvertTwo() {
        String word = NumberToWordConverter.toWord(2);

        assertEquals("two", word);
    }

    @Test
    public void canConvertThree() {
        String word = NumberToWordConverter.toWord(3);

        assertEquals("three", word);
    }

    @Test
    public void canConvertTen() {
        String word = NumberToWordConverter.toWord(10);

        assertEquals("ten", word);
    }

    @Test
    public void canConvertTwenty() {
        String word = NumberToWordConverter.toWord(20);

        assertEquals("twenty", word);
    }

    @Test
    public void canConvertThirty() {
        String word = NumberToWordConverter.toWord(30);

        assertEquals("thirty", word);
    }

    @Test
    public void canConvertTwentyFive() {
        String word = NumberToWordConverter.toWord(25);

        assertEquals("twenty five", word);
    }

    @Test
    public void canConvertThirtySix() {
        String word = NumberToWordConverter.toWord(36);

        assertEquals("thirty six", word);
    }

    @Test
    public void canConvertTwelve() {
        String word = NumberToWordConverter.toWord(12);

        assertEquals("twelve", word);
    }

    @Test
    public void canConvertFifteen() {
        String word = NumberToWordConverter.toWord(15);

        assertEquals("fifteen", word);
    }

    @Test
    public void canConvertOneHundred() {
        String word = NumberToWordConverter.toWord(100);

        assertEquals("one hundred", word);
    }

    @Test
    public void canConvertTwoHundred() {
        String word = NumberToWordConverter.toWord(200);

        assertEquals("two hundred", word);
    }

    @Test
    public void canConvertFiveHundred() {
        String word = NumberToWordConverter.toWord(500);

        assertEquals("five hundred", word);
    }

    @Test
    public void canConvertFiveHundredAndFour() {
        String word = NumberToWordConverter.toWord(504);

        assertEquals("five hundred and four", word);
    }

    @Test
    public void canConvertFiveHundredAndFourteen() {
        String word = NumberToWordConverter.toWord(514);

        assertEquals("five hundred and fourteen", word);
    }

    @Test
    public void canConvertFiveHundredAndFortyOne() {
        String word = NumberToWordConverter.toWord(541);

        assertEquals("five hundred and forty one", word);
    }

    @Test
    public void canConvertOneThousand() {
        String word = NumberToWordConverter.toWord(1000);

        assertEquals("one thousand", word);
    }

    @Test
    public void canConvertTwoThousand() {
        String word = NumberToWordConverter.toWord(2000);

        assertEquals("two thousand", word);
    }

    @Test
    public void canConvertElevenThousand() {
        String word = NumberToWordConverter.toWord(11000);

        assertEquals("eleven thousand", word);
    }

    @Test
    public void canConvertOneHundredAndElevenThousand() {
        String word = NumberToWordConverter.toWord(111000);

        assertEquals("one hundred and eleven thousand", word);
    }

    @Test
    public void canConvertThousandsWithHundreds() {
        String word = NumberToWordConverter.toWord(123456);

        assertEquals("one hundred and twenty three thousand, four hundred and fifty six", word);
    }

    @Test
    public void canConvertOneMillion() {
        String word = NumberToWordConverter.toWord(1000000);

        assertEquals("one million", word);
    }

    @Test
    public void canConvertMillions() {
        String word = NumberToWordConverter.toWord(123000000);

        assertEquals("one hundred and twenty three million", word);
    }

    @Test
    public void canConvertMillionsWithThousands() {
        String word = NumberToWordConverter.toWord(123456000);

        assertEquals("one hundred and twenty three million, "
                + "four hundred and fifty six thousand", word);
    }

    @Test
    public void canConvertMillionsWithThousandsAndHundreds() {
        String word = NumberToWordConverter.toWord(123456789);

        assertEquals("one hundred and twenty three million, "
                + "four hundred and fifty six thousand, seven hundred and eighty nine", word);
    }

    @Test
    public void canConvertOneBillion() {
        String word = NumberToWordConverter.toWord(1000000000);

        assertEquals("one billion", word);
    }

    @Test
    public void canConvertCombinationsWithBillion() {
        String word = NumberToWordConverter.toWord(2123456789);

        assertEquals("two billion, one hundred and twenty three million, "
                + "four hundred and fifty six thousand, seven hundred and eighty nine", word);
    }

    @Test
    public void canConvertZero() {
        String word = NumberToWordConverter.toWord(0);

        assertEquals("", word);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionWhenConvertNegativeNumber() {
        String word = NumberToWordConverter.toWord(-1);
    }
}
