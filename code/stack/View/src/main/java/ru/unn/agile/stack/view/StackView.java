package ru.unn.agile.stack.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import ru.unn.agile.stack.viewmodel.ViewModel;
import ru.unn.agile.stack.infrastructure.TxtLogger;

public class StackView {
    @FXML
    private ViewModel viewModel;
    @FXML
    private Button popElementButton;
    @FXML
    private Button pushElementButton;
    @FXML
    private TextField inputNewElementField;

    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger("./Stack.log"));

        inputNewElementField.textProperty().bindBidirectional(viewModel.pushElementProperty());

        pushElementButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.pushNewElement();
            }
        });

        popElementButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.popElement();
            }
        });
    }
}
