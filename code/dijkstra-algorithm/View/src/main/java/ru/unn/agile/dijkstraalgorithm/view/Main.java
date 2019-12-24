package ru.unn.agile.dijkstraalgorithm.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private static final String VIEW_TITLE = "Dijkstra Algorithm";
    private static final String FXML_RESOURCES = "Dijkstra.fxml";
    private static final String STYLE_RESOURCES = "style.css";

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FXML_RESOURCES));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(STYLE_RESOURCES).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle(VIEW_TITLE);
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}


