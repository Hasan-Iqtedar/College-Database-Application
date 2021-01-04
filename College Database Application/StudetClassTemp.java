package collegedatabaseapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Student extends Table {

    Scene studentScene;
    Scene insertRecordScene;
    Scene updateRecordScene;

    void setStudentScene(){
        /**
        Button insertRecord = new Button("Insert Record");
        Button updateRecord = new Button("Update Record");
        Button back = new Button("Back");
        */

        insertRecord.setMinWidth(100);
        updateRecord.setMinWidth(100);
        back.setMinWidth(100);

        //Setting Action Events for the buttons.
        back.setOnAction(e->Controller.stage.setScene(Controller.mainMenu.getSceneInsertData()));

        /**
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        */

        grid.setHgap(5);
        grid.setVgap(15);
        grid.add(insertRecord, 0, 0);
        grid.add(updateRecord, 0, 1);
        grid.add(back, 0, 2);


        /**
        Label label = new Label("Update Student Table");
        label.setFont(new Font(50));
        label.setTextFill(Color.GOLD);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_CENTER);

        hbox.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));


        VBox vbox = new VBox(70);
        vbox.getChildren().addAll(hbox, grid);
        */

        hbox.getChildren().add(label);
        vbox.getChildren().addAll(hbox, grid);
        studentScene = new Scene(vbox ,Controller.WIDTH, Controller.HEIGHT);

    }

}//End of class Student.
