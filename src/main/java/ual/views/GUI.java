package ual.views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;


public class GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        stage.setTitle("Batalha Naval");
        stage.setScene(new Scene(root,600, 400));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
