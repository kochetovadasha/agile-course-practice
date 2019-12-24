package ru.unn.agile.primenumber.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PrimeNumberFinderTest {

    @Test
    public void canInitFinder() {
        var finder = new PrimeNumberFinder(0, 10);
        assertNotNull(finder);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotInitFinderWithNegativeStart() {
        var finder = new PrimeNumberFinder(-1, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotInitFinderWithNegativeEnd() {
        var finder = new PrimeNumberFinder(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotInitFinderWithEndBiggerThanStart() {
        var finder = new PrimeNumberFinder(10, 0);
    }

    @Test
    public void cannotFindPrimeNumberBefore2() {
        var finder = new PrimeNumberFinder(0, 2);
        var result = finder.findNumbers();
        List<Integer> expectedResult = new ArrayList<>();
        assertEquals(expectedResult, result);
    }

    @Test
    public void canFindPrimeNumber1() {
        var finder = new PrimeNumberFinder(0, 3);
        var result = finder.findNumbers();
        List<Integer> expectedResult = new ArrayList<>() {{
            add(2);
        }};
        assertEquals(expectedResult, result);
    }

    @Test
    public void canFindPrimeNumber3() {
        var finder = new PrimeNumberFinder(0, 4);
        var result = finder.findNumbers();
        List<Integer> expectedResult = new ArrayList<>() {{
            add(2);
            add(3);
        }};
        assertEquals(expectedResult, result);
    }

    @Test
    public void canFindPrimeNumber5() {
        var finder = new PrimeNumberFinder(0, 6);
        var result = finder.findNumbers();
        List<Integer> expectedResult = new ArrayList<>() {{
            add(2);
            add(3);
            add(5);
        }};
        assertEquals(expectedResult, result);
    }

    @Test
    public void canFindPrimeNumber7() {
        var finder = new PrimeNumberFinder(0, 8);
        var result = finder.findNumbers();
        List<Integer> expectedResult = new ArrayList<>() {{
            add(2);
            add(3);
            add(5);
            add(7);
        }};
        assertEquals(expectedResult, result);
    }

    @Test
    public void canFindPrimeNumber11() {
        var finder = new PrimeNumberFinder(0, 12);
        var result = finder.findNumbers();
        List<Integer> expectedResult = new ArrayList<>() {{
            add(2);
            add(3);
            add(5);
            add(7);
            add(11);
        }};
        assertEquals(expectedResult, result);
    }

    @Test
    public void canFindPrimeNumber101() {
        var finder = new PrimeNumberFinder(0, 102);
        var result = finder.findNumbers();
        assertTrue(result.contains(101));
    }

    @Test
    public void canFindPrimeNumber907() {
        var finder = new PrimeNumberFinder(0, 1000);
        var result = finder.findNumbers();
        assertTrue(result.contains(907));
    }

}
