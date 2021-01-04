package collegedatabaseapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

abstract class Table {

    Button insertRecord;
    Button updateRecord;
    Button back;

    GridPane grid;
    Label label;
    HBox hbox;
    VBox vbox;

    abstract void setScene();

    Table(){
        insertRecord = new Button("Insert Record");
        updateRecord = new Button("Update Record");
        back = new Button("Back");

        back.setOnAction(e->Controller.stage.setScene(Controller.mainMenu.getSceneInsertData()));

        grid = new GridPane();
        label = new Label();
        hbox = new HBox();
        vbox = new VBox(70);

        insertRecord.setMinWidth(100);
        updateRecord.setMinWidth(100);
        back.setMinWidth(100);

        label.setFont(new Font(50));
        label.setTextFill(Color.GOLD);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(15);
        grid.add(insertRecord, 0, 0);
        grid.add(updateRecord, 0, 1);
        grid.add(back, 0, 2);

        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        hbox.getChildren().add(label);
        vbox.getChildren().addAll(hbox, grid);

    }//End of constructor.


}//End of abstract class Table.
