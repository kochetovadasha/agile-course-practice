package ru.unn.agile.dijkstraalgorithm.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.dijkstraalgorithm.model.DijkstraGraph;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ViewModel {

    private DijkstraGraph graph;

    private static final Pattern VERTEX_INPUT_ALLOWED_SYMBOLS = Pattern.compile("^[a-zA-Z]+$");
    private static final Pattern WEIGHT_INPUT_ALLOWED_SYMBOLS = Pattern.compile("^[1-9][0-9]*$");
    private final SimpleBooleanProperty addingNewEdgeDisabled = new SimpleBooleanProperty();


    private final StringProperty vertex1 = new SimpleStringProperty();
    private final StringProperty vertex2 = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();

    private StringProperty vertexFrom = new SimpleStringProperty();
    private StringProperty vertexTo = new SimpleStringProperty();
    private StringProperty resultPath = new SimpleStringProperty();


    private final ObservableList<EdgeViewModel> edgeList = FXCollections.observableArrayList();
    private final ObservableList<String> vertexList = FXCollections.observableArrayList();


    public ViewModel() {
        clearFormInput();

        BooleanBinding canCalculateBoolBinding = new BooleanBinding() {
            {
                super.bind(vertex1, vertex2, weight);
            }
            @Override
            protected boolean computeValue() {
                return (isVertex1InputCorrect()
                        && isVertex2InputCorrect()
                        && isWeightInputCorrect());
            }
        };
        addingNewEdgeDisabled.bind(canCalculateBoolBinding.not());
    }

    public void addEdge() {
        EdgeViewModel newEdge = new EdgeViewModel(vertex1.get(), vertex2.get(), weight.get());
        edgeList.add(newEdge);

        clearFormInput();
    }

    private void clearFormInput() {
        vertex1.set("");
        vertex2.set("");
        weight.set("");
    }

    public boolean isVertex1InputCorrect() {
        String exprText = vertex1.get();
        return (VERTEX_INPUT_ALLOWED_SYMBOLS.matcher(exprText).matches()
                && areVertexInputsNotEqual());
    }

    public boolean isVertex2InputCorrect() {
        String exprText = vertex2.get();
        return (VERTEX_INPUT_ALLOWED_SYMBOLS.matcher(exprText).matches()
                && areVertexInputsNotEqual());
    }

    private boolean areVertexInputsNotEqual() {
        return !vertex1.get().equals(vertex2.get());
    }

    public boolean isWeightInputCorrect() {
        String exprText = weight.get();
        return (WEIGHT_INPUT_ALLOWED_SYMBOLS.matcher(exprText).matches());
    }

    public void createGraph() {
        if (edgeList.isEmpty()) {
            return;
        }

        List<DijkstraGraph.Edge> list = edgeList.stream()
                .map(EdgeViewModel::getEdge)
                .collect(Collectors.toList());

        graph = new DijkstraGraph(list);
        updateVertexList();
    }

    private void updateVertexList() {
        vertexList.clear();
        vertexList.addAll(graph.getVertexList());
    }

    public void calculatePath() {
        String toPath = getVertexTo();
        String fromPath = getVertexFrom();
        if (toPath == null || fromPath == null) {
            return;
        }

        graph.calculate(fromPath);
        resultPath.setValue(graph.getPath(toPath));
    }

    public StringProperty vertex1Property() {
        return vertex1;
    }

    public StringProperty vertex2Property() {
        return vertex2;
    }

    public StringProperty weightProperty() {
        return weight;
    }

    private String getVertexFrom() {
        return vertexFrom.get();
    }

    public StringProperty vertexFromProperty() {
        return vertexFrom;
    }


    private String getVertexTo() {
        return vertexTo.get();
    }

    public StringProperty vertexToProperty() {
        return vertexTo;
    }

    public StringProperty resultPathProperty() {
        return resultPath;
    }

    public SimpleBooleanProperty addingNewEdgeDisabledProperty() {
        return addingNewEdgeDisabled;
    }

    public ObservableList<EdgeViewModel> getEdgeList() {
        return edgeList;
    }

    public ObservableList<String> getVertexList() {
        return vertexList;
    }
}
