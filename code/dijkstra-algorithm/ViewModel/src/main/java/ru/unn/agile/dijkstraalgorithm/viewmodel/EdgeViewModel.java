package ru.unn.agile.dijkstraalgorithm.viewmodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import ru.unn.agile.dijkstraalgorithm.model.DijkstraGraph;

public class EdgeViewModel {
    private final SimpleStringProperty vertex1 = new SimpleStringProperty("");
    private final SimpleStringProperty vertex2 = new SimpleStringProperty("");
    private final SimpleIntegerProperty weight = new SimpleIntegerProperty();

    private final DijkstraGraph.Edge edge;

    EdgeViewModel(final String vertex1, final String vertex2, final String weight) {
        setVertex1(vertex1);
        setVertex2(vertex2);
        setWeight(weight);

        edge = new DijkstraGraph.Edge(getVertex1(), getVertex2(), getWeight());
    }

    private String getVertex1() {
        return vertex1.get();
    }

    public SimpleStringProperty vertex1Property() {
        return vertex1;
    }

    private void setVertex1(final String vertex1) {
        this.vertex1.set(vertex1);
    }

    private String getVertex2() {
        return vertex2.get();
    }

    public SimpleStringProperty vertex2Property() {
        return vertex2;
    }

    private void setVertex2(final String vertex2) {
        this.vertex2.set(vertex2);
    }

    private int getWeight() {
        return weight.get();
    }

    public SimpleIntegerProperty weightProperty() {
        return weight;
    }

    private void setWeight(final String weight) {
        this.weight.set(Integer.parseInt(weight));
    }

    public DijkstraGraph.Edge getEdge() {
        return edge;
    }
}
