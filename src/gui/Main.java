package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{

        HBox root = (HBox) FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("reversi");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }






    public static void main(String[] args) {


        launch(args);
    }
}
