package ru.unn.agile.Matrix.view;

import javafx.fxml.FXML;
import ru.unn.agile.Matrix.viewmodel.ViewModel;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class Matrix {
    private static final String ZERO_ELEMENT_INDEX = "0";

    private static final String FIRST_ELEMENT_INDEX = "1";

    private static final String SECOND_ELEMENT_INDEX = "2";

    private static final String THIRD_ELEMENT_INDEX = "3";

    private static final String FOURTH_ELEMENT_INDEX = "4";

    private static final String FIFTH_ELEMENT_INDEX = "5";

    private static final String SIXTH_ELEMENT_INDEX = "6";

    private static final String SEVENTH_ELEMENT_INDEX = "7";

    private static final String EIGHT_ELEMENT_INDEX = "8";

    @FXML
    private ViewModel viewModel;
    @FXML
    private Button buttonCompare;
    @FXML
    private Button transposeFirst;
    @FXML
    private Button transposeSecond;
    @FXML
    private Button sumButton;
    @FXML
    private TextField first00 = new TextField();
    @FXML
    private TextField first10 = new TextField();
    @FXML
    private TextField first20 = new TextField();
    @FXML
    private TextField first01 = new TextField();
    @FXML
    private TextField first11 = new TextField();
    @FXML
    private TextField first21 = new TextField();
    @FXML
    private TextField first02 = new TextField();
    @FXML
    private TextField first12 = new TextField();
    @FXML
    private TextField first22 = new TextField();
    @FXML
    private TextField second00 = new TextField();
    @FXML
    private TextField second10 = new TextField();
    @FXML
    private TextField second20 = new TextField();
    @FXML
    private TextField second01 = new TextField();
    @FXML
    private TextField second11 = new TextField();
    @FXML
    private TextField second21 = new TextField();
    @FXML
    private TextField second02 = new TextField();
    @FXML
    private TextField second12 = new TextField();
    @FXML
    private TextField second22 = new TextField();
    @FXML
    private Label result00 = new Label();
    @FXML
    private Label result10 = new Label();
    @FXML
    private Label result20 = new Label();
    @FXML
    private Label result01 = new Label();
    @FXML
    private Label result11 = new Label();
    @FXML
    private Label result21 = new Label();
    @FXML
    private Label result02 = new Label();
    @FXML
    private Label result12 = new Label();
    @FXML
    private Label result22 = new Label();

    @FXML
    private TextField[] firstMatrixFields = new TextField[] {
            first00,
            first10,
            first20,
            first01,
            first11,
            first21,
            first02,
            first12,
            first22
    };


    @FXML
    void initialize() {
        first00.textProperty().bindBidirectional(viewModel.getFirstMatrixPropertiesByIndex(
                ZERO_ELEMENT_INDEX
        ));
        first10.textProperty().bindBidirectional(viewModel.getFirstMatrixPropertiesByIndex(
                FIRST_ELEMENT_INDEX
        ));
        first20.textProperty().bindBidirectional(viewModel.getFirstMatrixPropertiesByIndex(
                SECOND_ELEMENT_INDEX
        ));
        first01.textProperty().bindBidirectional(viewModel.getFirstMatrixPropertiesByIndex(
                THIRD_ELEMENT_INDEX
        ));
        first11.textProperty().bindBidirectional(viewModel.getFirstMatrixPropertiesByIndex(
                FOURTH_ELEMENT_INDEX
        ));
        first21.textProperty().bindBidirectional(viewModel.getFirstMatrixPropertiesByIndex(
                FIFTH_ELEMENT_INDEX
        ));
        first02.textProperty().bindBidirectional(viewModel.getFirstMatrixPropertiesByIndex(
                SIXTH_ELEMENT_INDEX
        ));
        first12.textProperty().bindBidirectional(viewModel.getFirstMatrixPropertiesByIndex(
                SEVENTH_ELEMENT_INDEX
        ));
        first22.textProperty().bindBidirectional(viewModel.getFirstMatrixPropertiesByIndex(
                EIGHT_ELEMENT_INDEX
        ));

        second00.textProperty().bindBidirectional(viewModel.getSecondMatrixPropertiesByIndex(
                ZERO_ELEMENT_INDEX
        ));
        second10.textProperty().bindBidirectional(viewModel.getSecondMatrixPropertiesByIndex(
                FIRST_ELEMENT_INDEX
        ));
        second20.textProperty().bindBidirectional(viewModel.getSecondMatrixPropertiesByIndex(
                SECOND_ELEMENT_INDEX
        ));
        second01.textProperty().bindBidirectional(viewModel.getSecondMatrixPropertiesByIndex(
                THIRD_ELEMENT_INDEX
        ));
        second11.textProperty().bindBidirectional(viewModel.getSecondMatrixPropertiesByIndex(
                FOURTH_ELEMENT_INDEX
        ));
        second21.textProperty().bindBidirectional(viewModel.getSecondMatrixPropertiesByIndex(
                FIFTH_ELEMENT_INDEX
        ));
        second02.textProperty().bindBidirectional(viewModel.getSecondMatrixPropertiesByIndex(
                SIXTH_ELEMENT_INDEX
        ));
        second12.textProperty().bindBidirectional(viewModel.getSecondMatrixPropertiesByIndex(
                SEVENTH_ELEMENT_INDEX
        ));
        second22.textProperty().bindBidirectional(viewModel.getSecondMatrixPropertiesByIndex(
                EIGHT_ELEMENT_INDEX
        ));

        result00.textProperty().bindBidirectional(viewModel.getResultMatrixPropertiesByIndex(
                ZERO_ELEMENT_INDEX
        ));
        result10.textProperty().bindBidirectional(viewModel.getResultMatrixPropertiesByIndex(
                FIRST_ELEMENT_INDEX
        ));
        result20.textProperty().bindBidirectional(viewModel.getResultMatrixPropertiesByIndex(
                SECOND_ELEMENT_INDEX
        ));
        result01.textProperty().bindBidirectional(viewModel.getResultMatrixPropertiesByIndex(
                THIRD_ELEMENT_INDEX
        ));
        result11.textProperty().bindBidirectional(viewModel.getResultMatrixPropertiesByIndex(
                FOURTH_ELEMENT_INDEX
        ));
        result21.textProperty().bindBidirectional(viewModel.getResultMatrixPropertiesByIndex(
                FIFTH_ELEMENT_INDEX
        ));
        result02.textProperty().bindBidirectional(viewModel.getResultMatrixPropertiesByIndex(
                SIXTH_ELEMENT_INDEX
        ));
        result12.textProperty().bindBidirectional(viewModel.getResultMatrixPropertiesByIndex(
                SEVENTH_ELEMENT_INDEX
        ));
        result22.textProperty().bindBidirectional(viewModel.getResultMatrixPropertiesByIndex(
                EIGHT_ELEMENT_INDEX
        ));

        buttonCompare.setOnAction(actionEvent -> {
            viewModel.compareMatrices();
        });
        transposeFirst.setOnAction(actionEvent -> {
            viewModel.transposeFirstMatrix();
        });
        transposeSecond.setOnAction(actionEvent -> {
            viewModel.transposeSecondMatrix();
        });
        sumButton.setOnAction(actionEvent -> {
            viewModel.sumMatrices();
        });

    }


}
