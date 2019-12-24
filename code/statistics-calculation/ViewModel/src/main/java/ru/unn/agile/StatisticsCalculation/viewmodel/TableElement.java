package ru.unn.agile.statisticscalculation.viewmodel;

public class TableElement {
    private String value;
    private String probability;

    public TableElement(final String value, final String probability) {
        this.value = value;
        this.probability = probability;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(final String probabilitie) {
        this.probability = probabilitie;
    }
}
