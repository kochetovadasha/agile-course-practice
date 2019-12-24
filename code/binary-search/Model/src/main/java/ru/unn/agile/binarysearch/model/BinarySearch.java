package ru.unn.agile.binarysearch.model;

public class BinarySearch {
    private int[] array;
    public static final int NOT_FOUND = -1;
    public static final int NOT_SORTED = -2;

    public BinarySearch(final int[] inputArray) {
        if (inputArray == null) {
            String errorMessage = "Can't initialize BinarySearch instance with null value";
            throw new NullPointerException(errorMessage);
        }
        array = inputArray;
    }

    public int[] getArray() {
        return array;
    }

    private boolean isSorted() {
        final int end = array.length - 1;
        for (int index = 0; index < end; ++index) {
            if (array[index] > array[index + 1]) {
                return false;
            }
        }
        return true;
    }

    public int search(final int element) {
        int leftBorder = 0;
        int rightBorder = array.length;
        if (isSorted()) {
            while (rightBorder - leftBorder > 1) {
                int mid = (leftBorder + rightBorder) / 2;
                if (element >= array[mid]) {
                    leftBorder = mid;
                } else {
                    rightBorder = mid;
                }
            }

            if (array[leftBorder] != element) {
                return BinarySearch.NOT_FOUND;
            }

            return leftBorder;
        }
        return BinarySearch.NOT_SORTED;
    }
}
