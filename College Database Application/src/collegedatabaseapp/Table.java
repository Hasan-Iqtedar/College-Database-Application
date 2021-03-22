package collegedatabaseapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class Table extends Theme{

	PreparedStatement statement;
	String insertionQuery;
	String deletionQuery;

	Button insertRecord;
	Button deleteRecord;
	Button back;
	Button submit;
	Button clear;

	Label label;
	Label insertionMessage;
	Label deletionMessage;

	GridPane grid1;
	GridPane grid2;
	HBox hbox1;
	VBox formVbox;

	abstract void setScene();

	//Constructor to initialize fields.
	Table(){

		insertRecord = new Button("Insert Record");
		deleteRecord = new Button("Delete Record");
		back = new Button("Back");
		submit = new Button("Submit");
		clear = new Button("Clear");

		insertRecord.setMinWidth(200);
		deleteRecord.setMinWidth(200);
		back.setMinWidth(200);
		submit.setMinWidth(200);
		clear.setMinWidth(200);

		insertRecord.setMinHeight(35);
		deleteRecord.setMinHeight(35);
		back.setMinHeight(35);
		submit.setMinHeight(35);
		clear.setMinHeight(35);

		back.setOnAction(e->Controller.stage.setScene(Controller.mainMenu.getSceneInsertData()));

		label = new Label();
		label.setTranslateY(60);
		label.setFont(new Font(50));
		label.setTextFill(Color.GOLD);

		insertionMessage = new Label();
		insertionMessage.setFont(new Font(25));

		deletionMessage = new Label();
		deletionMessage.setFont(new Font(25));

		setTheme();

		vBox1.getChildren().add(label);
		vBox2.getChildren().addAll(insertRecord, deleteRecord, back);

		//Setting layout elements for the forms for inserting data.
		grid1 = new GridPane();
		grid1.setAlignment(Pos.CENTER);
		grid1.setHgap(25);
		grid1.setVgap(10);

		grid2 = new GridPane();
		grid2.setHgap(25);
		grid2.setVgap(10);
		grid2.setAlignment(Pos.TOP_CENTER);

		hbox1 = new HBox(10);
		hbox1.getChildren().addAll(grid1, grid2);
		hbox1.setAlignment(Pos.CENTER);

		formVbox = new VBox(10);
		formVbox.getChildren().addAll(insertionMessage, hbox1);
		formVbox.setAlignment(Pos.CENTER);
		formVbox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

	}//End of constructor.


	/**To insert a record into a table.*/
	void insert(TextField[] fields) throws SQLException {

		statement = Controller.connection.prepareStatement(insertionQuery);

		for(int i =0; i<fields.length;i++){
			statement.setString(i+1, fields[i].getText());
		}
		statement.executeUpdate();
		System.out.println("Data Inserted");
	}//End of method insert.

	/**To delete a record from a table.*/
	void delete(TextField field)throws SQLException{

		if(!field.getText().isEmpty()) {
			statement = Controller.connection.prepareStatement(deletionQuery);
			statement.setString(1, field.getText());
			statement.executeUpdate();
			System.out.println("Record Deleted");
		}
		else
			throw new SQLException("Required Empty Field");

		field.setText("");

	}//End of method delete.


}//End of abstract class Table.
