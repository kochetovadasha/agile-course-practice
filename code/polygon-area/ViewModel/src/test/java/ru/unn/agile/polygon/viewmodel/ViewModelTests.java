package ru.unn.agile.polygon.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.polygon.model.Point;

import static org.junit.Assert.*;

public class ViewModelTests {
    private final double eps = 1.0E-10;
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
    public void isAddingValidInput() {
        setInputData("-261.55", "2.645");
        viewModel.addPoint();
        assertEquals(new Point(-261.55, 2.645).getX(), viewModel.getPointList().get(0).getX(), eps);
        assertEquals(new Point(-261.55, 2.645).getY(), viewModel.getPointList().get(0).getY(), eps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isNotAddedInvalidInput() {
        setInputData("-26vrt1.55", "2..645");
        viewModel.addPoint();
    }

    @Test(expected = IllegalArgumentException.class)
    public void isNotAddedEmptyInput() {
        setInputData("", "");
        viewModel.addPoint();
    }

    @Test
    public void cantCalcAreaWhenPointListIsEmpty() {
        viewModel.calcArea();
        assertEquals(null, viewModel.getResult());
    }

    @Test
    public void canCalcAreaOfThreePointPolygon() {
        setInputData("1", "0");
        viewModel.addPoint();
        setInputData("0", "0");
        viewModel.addPoint();
        setInputData("0", "1");
        viewModel.addPoint();
        viewModel.calcArea();
        assertEquals(0.5, Double.parseDouble(viewModel.getResult()), eps);
    }

    @Test
    public void isAddPointButtonDisabledForEmptyInput() {
        setInputData("", "");

        assertTrue(viewModel.isAddingNewPointDisabled());
    }

    @Test
    public void isAddPointButtonDisabledForInvalidXInput() {
        setInputData("256..1", "23");

        assertTrue(viewModel.isAddingNewPointDisabled());
    }

    @Test
    public void isAddPointButtonDisabledForInvalidYInput() {
        setInputData("23", "--235...5");

        assertTrue(viewModel.isAddingNewPointDisabled());
    }

    @Test
    public void isFormInputsEmptyAfterAddingNewPoint() {
        setInputData("-36.516", "-62.52");
        viewModel.addPoint();
        assertTrue(viewModel.xProperty().get().isEmpty()
                && viewModel.yProperty().get().isEmpty()
        );
    }

    @Test
    public void cantCalcAreaForLessThanThreePointsPolygon() {
        setInputData("1", "0");
        viewModel.addPoint();
        setInputData("0", "0");
        viewModel.addPoint();
        viewModel.calcArea();
        assertEquals("A polygon must have at least three vertices", viewModel.getResult());
    }

    @Test
    public void cantCalcAreaForSelfIntersectingPolygon() {
        setInputData("1", "1");
        viewModel.addPoint();
        setInputData("0", "0");
        viewModel.addPoint();
        setInputData("1", "0");
        viewModel.addPoint();
        setInputData("0", "1");
        viewModel.addPoint();
        viewModel.calcArea();
        assertEquals("Sides of polygon must not intersect", viewModel.getResult());
    }

    private void setInputData(final String x, final String y) {
        viewModel.xProperty().set(x);
        viewModel.yProperty().set(y);
    }
}
