package ru.unn.agile.statisticscalculation.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiscreteRandomVariableTest {
    private final double delta = 0.001;

    @Test
    public void canCreateDisceteVarWithIntegerVals() {
        Integer[] values = {1};
        Double[] probabilities = {1.0};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);

        assertNotNull(var);
    }

    @Test
    public void canCreateDisceteVarWithDoubleVals() {
        Double[] values = {1.0, 2.0};
        Double[] probabilities = {0.5, 0.5};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);

        assertNotNull(var);
    }

    @Test
    public void canGetValues() {
        Number[] values = {-1.0, 2};
        Double[] probabilities = {0.5, 0.5};
        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);

        assertArrayEquals(values, var.getValues());
    }

    @Test
    public void canGetProbabilities() {
        Double[] values = {1.0, 2.0};
        Double[] probabilities = {0.5, 0.5};
        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);

        assertArrayEquals(probabilities, var.getProbabilities());
    }

    @Test
    public void canCopyDiscreteRandomVariable() {
        Double[] values = {-1.0, 2.0, 5.0};
        Double[] probabilities = {0.1, 0.1, 0.8};
        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);

        DiscreteRandomVariable var2 = var;

        assertEquals(var, var2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithEmptyValuesAndProbs() {
        Integer[] values = {};
        Double[] probabilities = {};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithEmptyValues() {
        Integer[] values = {};
        Double[] probabilities = {1.0};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithEmptyProbs() {
        Integer[] values = {1};
        Double[] probabilities = {};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithNullValuesAndProbs() {
        Integer[] values = null;
        Double[] probabilities = null;

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithNullValues() {
        Integer[] values = null;
        Double[] probabilities = {1.0};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithNullProbs() {
        Integer[] values = {1};
        Double[] probabilities = null;

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithValsAndProbsWithDifferentSize() {
        Integer[] values = {1, 2, 3};
        Double[] probabilities = {0.1, 0.9};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithValuesWithNull() {
        Integer[] values = {1, null, 3};
        Double[] probabilities = {0.1, 0.8, 0.1};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithProbsWithNull() {
        Integer[] values = {1, 2, 3};
        Double[] probabilities = {0.1, null, 0.1};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithProbsWithNegativeValues() {
        Integer[] values = {1, 2, 3};
        Double[] probabilities = {-0.1, 1.0, 0.1};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithProbsWithBigValues() {
        Integer[] values = {1, 2, 3};
        Double[] probabilities = {0.1, 1.2, 0.1};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithProbsWithSumMoreOne() {
        Integer[] values = {1, 2, 3};
        Double[] probabilities = {0.1, 0.9, 0.1};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenCreateVarWithProbsWithSumLessOne() {
        Integer[] values = {1, 2, 3};
        Double[] probabilities = {0.1, 0.3, 0.1};

        DiscreteRandomVariable var = new DiscreteRandomVariable(values, probabilities);
    }



}
