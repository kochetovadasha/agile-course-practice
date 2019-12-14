package ru.unn.agile.binarysearch.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class BinarySearchTest {

    @Test
    public void canInitializeArrayForBinarySearch() {
        int[] inputArray = {0, 1, 2, 3, 4, 5};
        BinarySearch binarySearch = new BinarySearch(inputArray);

        assertEquals(inputArray, binarySearch.getArray());
    }

    @Test(expected = NullPointerException.class)
    public void canNotCreateBinarySearchWithNullArray() {
        int[] inputArray = null;
        BinarySearch binarySearch = new BinarySearch(inputArray);
    }

    @Test
    public void canDoSimpleSearch() {
        int[] inputArray = {1};
        BinarySearch binarySearch = new BinarySearch(inputArray);
        int element = 1;
        int expectedPosition = 0;

        assertEquals(expectedPosition, binarySearch.search(element));
    }

    @Test
    public void canSearchElementInSortedArraySimple() {
        int[] inputArray = {1, 2, 3};
        BinarySearch binarySearch = new BinarySearch(inputArray);
        int element = 3;
        int expectedPosition = 2;

        assertEquals(expectedPosition, binarySearch.search(element));
    }

    @Test
    public void canSearchElementInSortedArrayMiddle() {
        int[] inputArray = {1, 2, 3, 4, 5, 6, 7};
        BinarySearch binarySearch = new BinarySearch(inputArray);
        int element = 5;
        int expectedPosition = 4;

        assertEquals(expectedPosition, binarySearch.search(element));
    }

    @Test
    public void canNotSearchElementInArray() {
        int[] inputArray = {1, 2, 3, 4, 5, 6, 7};
        BinarySearch binarySearch = new BinarySearch(inputArray);
        int element = 9;
        int expectedPosition = BinarySearch.NOT_FOUND;

        assertEquals(expectedPosition, binarySearch.search(element));
    }

    @Test
    public void canExceptionForUnsortedArray() {
        int[] inputArray = {2, 1, 3};
        BinarySearch binarySearch = new BinarySearch(inputArray);
        int element = 1;
        int expectedPosition = BinarySearch.NOT_SORTED;

        assertEquals(expectedPosition, binarySearch.search(element));
    }
}
