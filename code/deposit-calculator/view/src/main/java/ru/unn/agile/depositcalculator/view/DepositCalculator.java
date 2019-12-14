package ru.unn.agile.depositcalculator.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.unn.agile.depositcalculator.model.CapitalizationPeriod;
import ru.unn.agile.depositcalculator.model.DepositTimeType;
import ru.unn.agile.depositcalculator.viewmodel.ViewModel;

public class DepositCalculator {

    @FXML
    private ViewModel viewModel;

    @FXML
    private TextField txtPercentage;
    @FXML
    private TextField txtStartSum;

    @FXML
    private ComboBox<DepositTimeType> cmbPeriod;
    @FXML
    private ComboBox<CapitalizationPeriod> cmbCapit;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnCalc;

    @FXML
    void initialize() {

        txtPercentage.textProperty().bindBidirectional(viewModel.percentProperty());
        txtStartSum.textProperty().bindBidirectional(viewModel.startSumProperty());
        txtResult.textProperty().bindBidirectional(viewModel.resultProperty());

        cmbPeriod.valueProperty().bindBidirectional(viewModel.periodProperty());
        cmbCapit.valueProperty().bindBidirectional(viewModel.capitalizationProperty());

        btnCalc.setOnAction(event -> viewModel.calculate());
    }

}
