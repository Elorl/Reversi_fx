package gui;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * AlertBox.
 *
 */
public class AlertBox {

    /**
     * display.
     *
     * display the alertBox.
     */
    public static void display(String msg) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Alert");
        //set size to the window
        window.setHeight(120);
        window.setWidth(400);
        Label label = new Label();
        msg = "\n" + msg + "\n";
        label.setText(msg);
        label.setTextFill(Color.WHITE);
        Button closeButton = new Button("Ok, got it!");
        closeButton.setOnAction(e -> window.close());
        closeButton.setStyle("-fx-background-radius: 6; -fx-background-color:linear-gradient(#002444, #144C7D);  -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 10, 0.0 , 0 , 5);");
        closeButton.setTextFill(Color.WHITE);
        //create the new vbox.
        VBox vBOX = new VBox(20);
        vBOX.getChildren().addAll(label, closeButton);
        vBOX.setAlignment(Pos.CENTER);
        vBOX.setStyle("-fx-background-image: url(wallpaper.jpg);");
        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(vBOX);
        window.setScene(scene);
        window.showAndWait();
    }

}
