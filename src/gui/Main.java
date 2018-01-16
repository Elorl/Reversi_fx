package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    @FXML
    Button startButton;
    @FXML
    Button settingsButton;

    @Override
    public void start(Stage primaryStage) throws Exception{

        AnchorPane root =  FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("reversi");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * openes a setting menu.
     */
    @FXML
    public void settingsAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
            loader.load();
            SettingsController settingsController = loader.getController();
            settingsController.settingsAction();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * showing game board scene.
     * @param event
     * @throws IOException
     */
    @FXML
    public void startAction(ActionEvent event) throws IOException {
        try {
            Parent gameNode = FXMLLoader.load(getClass().getResource("gameController.fxml"));
            Scene scene = new Scene(gameNode);
            Stage primaryStage = (Stage)startButton.getScene().getWindow();

            primaryStage.setScene(scene);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }

    }






    public static void main(String[] args) {


        launch(args);
    }
}
