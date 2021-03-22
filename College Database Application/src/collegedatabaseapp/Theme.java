package collegedatabaseapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * A class to maintain a standard theme for the application.*/
abstract class Theme {

    HBox hBox;
    VBox vBox1;
    VBox vBox2;

    void setTheme(){

        vBox1 = new VBox();
        vBox2 = new VBox();
        hBox = new HBox(150);

        vBox1.setMinWidth(300);
        vBox1.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        vBox2.setAlignment(Pos.CENTER);
        vBox2.setSpacing(15);

        hBox.getChildren().addAll(vBox1, vBox2);
        hBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

    }//End of method setTheme.

}//End of class Theme.
