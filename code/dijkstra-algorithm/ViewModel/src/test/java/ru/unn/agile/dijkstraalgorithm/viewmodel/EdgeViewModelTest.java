package ru.unn.agile.dijkstraalgorithm.viewmodel;

import org.junit.Test;
import ru.unn.agile.dijkstraalgorithm.model.DijkstraGraph;

import static org.junit.Assert.assertTrue;

public class EdgeViewModelTest {
    private final String vertex1 = "A";
    private final String vertex2 = "B";
    private final String weight = "2";

    private final EdgeViewModel edge = new EdgeViewModel(vertex1, vertex2, weight);

    @Test
    public void canGetVertex1Property() {
        assertTrue(edge.getEdge() instanceof DijkstraGraph.Edge);
    }
}
