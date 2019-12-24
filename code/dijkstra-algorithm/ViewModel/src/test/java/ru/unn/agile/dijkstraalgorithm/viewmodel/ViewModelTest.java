package ru.unn.agile.dijkstraalgorithm.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ViewModelTest {

    private static final String VERTEX1_CORRECT_INPUT = "A";
    private static final String VERTEX2_CORRECT_INPUT = "B";
    private static final String WEIGHT_CORRECT_INPUT = "2";

    private static final String VERTEX1_INCORRECT_INPUT = "A1!";
    private static final String VERTEX2_INCORRECT_INPUT = "B1%";
    private static final String WEIGHT_INCORRECT_INPUT = "2A!";

    private static final String PATH_RESULT = "A(0) -> B(2)";

    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void isAddEdgeButtonDisabledForEmptyInput() {
        setFormTextFields("", "", "");

        assertTrue(isAddEdgeButtonDisabled());
    }

    @Test
    public void isAddEdgeButtonDisabledForInvalidVertex1Input() {
        setFormTextFields(VERTEX1_INCORRECT_INPUT, VERTEX2_CORRECT_INPUT, WEIGHT_CORRECT_INPUT);
        assertTrue(isAddEdgeButtonDisabled());
    }

    @Test
    public void isAddEdgeButtonDisabledForInvalidVertex2Input() {
        setFormTextFields(VERTEX1_CORRECT_INPUT, VERTEX2_INCORRECT_INPUT, WEIGHT_CORRECT_INPUT);
        assertTrue(isAddEdgeButtonDisabled());
    }

    @Test
    public void isAddEdgeButtonDisabledForInvalidWeightInput() {
        setFormTextFields(VERTEX1_CORRECT_INPUT, VERTEX2_CORRECT_INPUT, WEIGHT_INCORRECT_INPUT);
        assertTrue(isAddEdgeButtonDisabled());
    }

    @Test
    public void isAddEdgeButtonEnabledForCorrectInput() {
        setFormTextFields(VERTEX1_CORRECT_INPUT, VERTEX2_CORRECT_INPUT, WEIGHT_CORRECT_INPUT);
        assertFalse(isAddEdgeButtonDisabled());
    }

    @Test
    public void isFormInputsEmptyAfterAddingNewEdge() {
        addEdge();
        assertTrue(viewModel.vertex1Property().get().isEmpty()
                && viewModel.vertex2Property().get().isEmpty()
                && viewModel.weightProperty().get().isEmpty()
        );
    }

    @Test
    public void isGraphEdgeListNotEmptyAfterAdding() {
        addEdgeAndCreateGraph();
        assertFalse(viewModel.getEdgeList().isEmpty());
    }

    @Test
    public void isGraphEdgeListEmptyAfterCreateGraphWithoutEdges() {
        createGraph();
        assertTrue(viewModel.getEdgeList().isEmpty());
    }

    @Test
    public void isVertexListUpdated() {
        addEdgeAndCreateGraph();
        assertFalse(viewModel.getVertexList().isEmpty());
    }

    @Test(expected = Test.None.class)
    public void canChooseVertexFromComboBox() {
        addEdgeAndCreateGraph();
        getVertexFromList(0);
    }

    @Test
    public void canCalculatePath() {
        addEdgeAndCreateGraph();
        chooseFromComboBox(0);
        chooseToComboBox(1);
        viewModel.calculatePath();

        assertEquals(PATH_RESULT, viewModel.resultPathProperty().get());
    }

    @Test
    public void emptyResultWhenFromAndToComboBoxesAreEmpty() {
        addEdgeAndCreateGraph();
        viewModel.calculatePath();
        assertNull(viewModel.resultPathProperty().get());
    }



    private boolean isAddEdgeButtonDisabled() {
        return viewModel.addingNewEdgeDisabledProperty().get();
    }

    private void setFormTextFields(final String vertex1,
                                   final String vertex2,
                                   final String weight) {
        viewModel.vertex1Property().setValue(vertex1);
        viewModel.vertex2Property().setValue(vertex2);
        viewModel.weightProperty().setValue(weight);
    }

    private void addEdge() {
        setFormTextFields(VERTEX1_CORRECT_INPUT, VERTEX2_CORRECT_INPUT, WEIGHT_CORRECT_INPUT);
        viewModel.addEdge();
    }

    private void createGraph() {
        viewModel.createGraph();
    }

    private void addEdgeAndCreateGraph() {
        addEdge();
        createGraph();
    }

    private String getVertexFromList(final int vertexIndex) {
        return viewModel.getVertexList().get(vertexIndex);
    }

    private void chooseFromComboBox(final int vertexIndex) {
        viewModel.vertexFromProperty().setValue(getVertexFromList(vertexIndex));
    }

    private void chooseToComboBox(final int vertexIndex) {
        viewModel.vertexToProperty().setValue(getVertexFromList(vertexIndex));
    }
}
