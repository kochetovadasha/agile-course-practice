package ru.unn.agile.vectorsdistancescalculator.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.vectorsdistancescalculator.viewmodel.ViewModel;
import ru.unn.agile.vectorsdistancescalculator.model.VectorsDistancesCalculator.Operation;

public class VectorsDistancesCalculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtX1;
    @FXML
    private TextField txtY1;
    @FXML
    private TextField txtZ1;
    @FXML
    private TextField txtX2;
    @FXML
    private TextField txtY2;
    @FXML
    private TextField txtZ2;
    @FXML
    private ComboBox<Operation> comboBoxOperation;
    @FXML
    private Button buttonCalc;

    @FXML
    void initialize() {
        txtX1.textProperty().bindBidirectional(viewModel.x1Property());
        txtY1.textProperty().bindBidirectional(viewModel.y1Property());
        txtZ1.textProperty().bindBidirectional(viewModel.z1Property());
        txtX2.textProperty().bindBidirectional(viewModel.x2Property());
        txtY2.textProperty().bindBidirectional(viewModel.y2Property());
        txtZ2.textProperty().bindBidirectional(viewModel.z2Property());

        comboBoxOperation.valueProperty().bindBidirectional(viewModel.operationProperty());

        buttonCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
