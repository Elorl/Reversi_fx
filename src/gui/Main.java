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
import reversi.Color;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Main extends Application{
    @FXML
    Button startButton;
    @FXML
    Button settingsButton;
    @FXML
    Button closeButton;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.defaultSettings();
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
            primaryStage.setHeight(646);
            primaryStage.setWidth(740);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }

    }

    @FXML
    public void closeAction() {
        Stage s = (Stage) this.closeButton.getScene().getWindow();
        s.close();
    }


    public static void main(String[] args) {


        launch(args);
    }

    public void defaultSettings() {
        javafx.scene.paint.Color colorP1 = javafx.scene.paint.Color.BLACK;
        javafx.scene.paint.Color colorP2 = javafx.scene.paint.Color.WHITE;
        int sizeBoard = 8;
        String str = colorP1.toString() + " " + colorP2.toString() + " " + sizeBoard;
        File settingsFIle = new File("settings.txt");
        try {
            FileOutputStream file = new FileOutputStream(settingsFIle.getName());
            PrintStream writer = new PrintStream(file);
            writer.print(str);
            file.close();
        }catch (Exception FileNotFoundException) {
            throw new RuntimeException();
        }
    }
}
