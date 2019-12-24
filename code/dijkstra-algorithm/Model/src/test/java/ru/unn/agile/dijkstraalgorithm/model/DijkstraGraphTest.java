package ru.unn.agile.dijkstraalgorithm.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DijkstraGraphTest {

    private static final List<DijkstraGraph.Edge> EMPTY_EDGES = Collections.emptyList();

    private static final List<DijkstraGraph.Edge> EDGE_AB =
            Collections.singletonList(new DijkstraGraph.Edge("a", "b", 7));
    private static final String EDGE_AB_A_TO_B_PATH = "a(0) -> b(7)";
    private static final String EDGE_AB_A_TO_A_PATH = "a(0)";

    private static final List<DijkstraGraph.Edge> EDGES_ABCD = Arrays.asList(
            new DijkstraGraph.Edge("a", "b", 4),
            new DijkstraGraph.Edge("с", "d", 5)
    );
    private static final String CAN_NOT_REACH_D_VERTEX = "Can not reach d vertex";

    private static final List<DijkstraGraph.Edge> EDGES_1 = Arrays.asList(
            new DijkstraGraph.Edge("a", "b", 4),
            new DijkstraGraph.Edge("a", "c", 5),
            new DijkstraGraph.Edge("a", "d", 6),
            new DijkstraGraph.Edge("b", "e", 8),
            new DijkstraGraph.Edge("c", "e", 7),
            new DijkstraGraph.Edge("d", "e", 4)
    );
    private static final String EDGES_1_A_TO_E_PATH = "a(0) -> d(6) -> e(10)";

    private static final List<DijkstraGraph.Edge> EDGES_2 = Arrays.asList(
            new DijkstraGraph.Edge("a", "b", 4),
            new DijkstraGraph.Edge("a", "c", 5),
            new DijkstraGraph.Edge("a", "d", 6),
            new DijkstraGraph.Edge("b", "c", 2),
            new DijkstraGraph.Edge("d", "c", 1),
            new DijkstraGraph.Edge("c", "e", 3),
            new DijkstraGraph.Edge("e", "f", 11),
            new DijkstraGraph.Edge("e", "g", 5),
            new DijkstraGraph.Edge("e", "h", 6),
            new DijkstraGraph.Edge("f", "k", 1),
            new DijkstraGraph.Edge("g", "k", 7),
            new DijkstraGraph.Edge("h", "k", 10)
    );
    private static final String EDGES_2_A_TO_K_PATH = "a(0) -> c(5) -> e(8) -> g(13) -> k(20)";

    private static final List<DijkstraGraph.Edge> EDGE_AB_NEGATIVE =
            Collections.singletonList(new DijkstraGraph.Edge("a", "b", -7));

    @Test(expected = IllegalArgumentException.class)
    public void canNotBuildGraphWithNegativeWeightEdge() {
        new DijkstraGraph(EDGE_AB_NEGATIVE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateGraphFromNullEdgesList() {
        new DijkstraGraph(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateGraphFromEmptyEdgesArray() {
        new DijkstraGraph(EMPTY_EDGES);
    }

    @Test
    public void canReturnVertexNumber() {
        DijkstraGraph g = new DijkstraGraph(EDGE_AB);
        assertEquals(2, g.getVertexNumber());
    }

    @Test(expected = RuntimeException.class)
    public void canNotCalculateGraphForNonExistingVertex() {

        DijkstraGraph g = new DijkstraGraph(EDGE_AB);
        String start = "This vertex doesn't exist";

        g.calculate(start);
    }

    @Test
    public void canCalculatePathFor2ElementGraph() {

        DijkstraGraph g = new DijkstraGraph(EDGE_AB);
        String start = "a";
        String end = "b";

        g.calculate(start);
        String pathFromAtoB = g.getPath(end);

        assertEquals(EDGE_AB_A_TO_B_PATH, pathFromAtoB);
    }

    @Test
    public void canCalculatePathForGraph() {

        String start = "a";
        String end = "e";

        DijkstraGraph g = new DijkstraGraph(EDGES_1);
        g.calculate(start);
        String pathFromAtoE = g.getPath(end);

        assertEquals(EDGES_1_A_TO_E_PATH, pathFromAtoE);
    }

    @Test
    public void canCalculatePathForBiggerGraph() {

        DijkstraGraph g = new DijkstraGraph(EDGES_2);
        String start = "a";
        String end = "k";

        g.calculate(start);
        String pathFromAtoK = g.getPath(end);

        assertEquals(EDGES_2_A_TO_K_PATH, pathFromAtoK);
    }

    @Test(expected = RuntimeException.class)
    public void canNotCalculatePath2NonExistingVertex() {

        DijkstraGraph g = new DijkstraGraph(EDGE_AB);
        String start = "a";
        String end = "This vertex doesn't exist";

        g.calculate(start);
        g.getPath(end);
    }

    @Test
    public void canCalculatePathToStartPoint() {

        DijkstraGraph g = new DijkstraGraph(EDGE_AB);
        String start = "a";
        g.calculate(start);

        assertEquals(EDGE_AB_A_TO_A_PATH, g.getPath(start));
    }

    @Test
    public void canNotCalculatePathToUnreachablePoint() {

        DijkstraGraph g = new DijkstraGraph(EDGES_ABCD);
        String start = "a";
        String end = "d";

        g.calculate(start);
        String pathFromAtoD = g.getPath(end);

        assertEquals(CAN_NOT_REACH_D_VERTEX, pathFromAtoD);
    }

    @Test
    public void canGetVertexList() {
        DijkstraGraph g = new DijkstraGraph(EDGES_1);

        assertEquals(5, g.getVertexList().size());
    }
}
