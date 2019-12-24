package ru.unn.agile.polygon.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.polygon.model.Point;

import java.util.List;

import static org.junit.Assert.*;
import static ru.unn.agile.polygon.viewmodel.LogMessages.CALCULATE_BUTTON_PRESSED;

public class PolygonAreaCalcViewModelTests {
    private final double eps = 1.0E-10;

    private PolygonAreaCalcViewModel viewModel;

    protected void setTestViewModel(final PolygonAreaCalcViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        viewModel = new PolygonAreaCalcViewModel(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void isAddingValidInput() {
        addPoint("-261.55", "2.645");
        assertEquals(new Point(-261.55, 2.645).getX(), viewModel.getPointList().get(0).getX(), eps);
        assertEquals(new Point(-261.55, 2.645).getY(), viewModel.getPointList().get(0).getY(), eps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isNotAddedInvalidInput() {
        addPoint("-26vrt1.55", "2..645");
    }

    @Test(expected = IllegalArgumentException.class)
    public void isNotAddedEmptyInput() {
        addPoint("", "");
    }

    @Test
    public void cantCalcAreaWhenPointListIsEmpty() {
        viewModel.calcArea();
        assertNull(viewModel.getResult());
    }

    @Test
    public void canCalcAreaOfThreePointPolygon() {
        addPoint("1", "0");
        addPoint("0", "0");
        addPoint("0", "1");
        viewModel.calcArea();
        assertEquals(0.5, Double.parseDouble(viewModel.getResult()), eps);
    }

    @Test
    public void isAddPointButtonDisabledForEmptyInput() {
        setCoordinates("", "");

        assertTrue(viewModel.isAddingNewPointDisabled());
    }

    @Test
    public void isAddPointButtonDisabledForInvalidXInput() {
        setCoordinates("256..1", "23");

        assertTrue(viewModel.isAddingNewPointDisabled());
    }

    @Test
    public void isAddPointButtonDisabledForInvalidYInput() {
        setCoordinates("23", "--235...5");

        assertTrue(viewModel.isAddingNewPointDisabled());
    }

    @Test
    public void isFormInputsEmptyAfterAddingNewPoint() {
        addPoint("-36.516", "-62.52");
        assertTrue(viewModel.xProperty().get().isEmpty()
                && viewModel.yProperty().get().isEmpty()
        );
    }

    @Test
    public void cantCalcAreaForLessThanThreePointsPolygon() {
        addPoint("1", "0");
        addPoint("0", "0");
        viewModel.calcArea();
        assertEquals("A polygon must have at least three vertices", viewModel.getResult());
    }

    @Test
    public void cantCalcAreaForSelfIntersectingPolygon() {
        addPoint("1", "1");
        addPoint("0", "0");
        addPoint("1", "0");
        addPoint("0", "1");
        viewModel.calcArea();
        assertEquals("Sides of polygon must not intersect", viewModel.getResult());
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotInstantiateViewModelWithNullLogger() {
        viewModel = new PolygonAreaCalcViewModel(null);
    }

    @Test
    public void logAreEmptyOnInit() {
        assertTrue(getLog().isEmpty());
    }

    @Test
    public void logContainsFormattedMessageAfterCalculation() {
        addPoint("1", "0");
        addPoint("0", "0");
        addPoint("0", "1");
        viewModel.calcArea();

        assertTrue(!getLog().isEmpty() && getLog().get(0).contains(CALCULATE_BUTTON_PRESSED));
    }

    private void addPoint(String x,String y) {
        setCoordinates(x, y);
        viewModel.addPoint();
    }

    private void setCoordinates(final String x, final String y) {
        viewModel.xProperty().set(x);
        viewModel.yProperty().set(y);
    }

    private List<String> getLog() {
        return viewModel.getLog();
    }
}
