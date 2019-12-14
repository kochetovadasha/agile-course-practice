package ru.unn.agile.depositcalculator.viewmodel;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.depositcalculator.model.CapitalizationPeriod;
import ru.unn.agile.depositcalculator.model.DepositTimeType;

import static ru.unn.agile.depositcalculator.viewmodel.ViewModel.VALIDATION_ERROR;


public class ViewModelTest {

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
    public void canSetDefaultDepositPeriod() {
        Assert.assertEquals(DepositTimeType.DAY, viewModel.periodProperty().get());
    }

    @Test
    public void canSetDefaultCapitPeriod() {
        Assert.assertEquals(CapitalizationPeriod.MONTH, viewModel.capitalizationProperty().get());
    }

    @Test
    public void canSetDefaultStartSum() {
        Assert.assertEquals("1000", viewModel.getStartSumProperty());
    }

    @Test
    public void canSetDefaultPercent() {
        Assert.assertEquals("8", viewModel.getPercentProperty());
    }

    @Test
    public void canCalculate() {
        viewModel.calculate();
        Assert.assertEquals("1000.22", viewModel.getResultProperty());
    }

    @Test
    public void canValidateEmptyPercentage() {
        viewModel.setPercentProperty("");
        viewModel.calculate();
        Assert.assertEquals(VALIDATION_ERROR, viewModel.getResultProperty());
    }

    @Test
    public void canValidateEmptyStartSum() {
        viewModel.setStartSumProperty("");
        viewModel.calculate();
        Assert.assertEquals(VALIDATION_ERROR, viewModel.getResultProperty());
    }

    @Test
    public void canValidateNegativeStartSum() {
        viewModel.setStartSumProperty("-1");
        viewModel.calculate();
        Assert.assertEquals(VALIDATION_ERROR, viewModel.getResultProperty());
    }

    @Test
    public void canValidateNegativePercentage() {
        viewModel.setPercentProperty("-1");
        viewModel.calculate();
        Assert.assertEquals(VALIDATION_ERROR, viewModel.getResultProperty());
    }

    @Test
    public void canValidateNotNumberSymbolsPercentage() {
        viewModel.setPercentProperty("asdfasdf100-adsf.0");
        viewModel.calculate();
        Assert.assertEquals(VALIDATION_ERROR, viewModel.getResultProperty());
    }

    @Test
    public void canValidateNotNumberSymbolsStartSum() {
        viewModel.setStartSumProperty("asdfasdf100-adsf.0");
        viewModel.calculate();
        Assert.assertEquals(VALIDATION_ERROR, viewModel.getResultProperty());
    }
}
