package collegedatabaseapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Department extends Table {

	Scene departmentScene;
	Scene insertRecordScene;
	Scene deleteRecordScene;
	Scene ViewDepartment;

	TableView<DepartmentData> table;

	/**Constructor to make department scene ready for insertion and deletion*/
	Department(){
		setScene();
		setViewDataScene();
		setInsertRecordScene();
		setDeleteRecordScene();
		insertionQuery = "INSERT INTO Department VALUES (?, ?, ?)";
		deletionQuery = "DELETE FROM Department WHERE dept_id = ?";
	}


	/**
	 * Sets the scene for updating department table.
	 * This scene appears when the departmentButton is clicked.
	 * */
	void setScene(){

		//Setting insertRecord button.
		insertRecord.setOnAction(e->Controller.stage.setScene(insertRecordScene));
		deleteRecord.setOnAction(e->Controller.stage.setScene(deleteRecordScene));

		label.setText(" Update\n Department\n Table");
		departmentScene = new Scene(hBox, Controller.WIDTH, Controller.HEIGHT);

	}//End of method setCourseScene.


	/**
	 * Sets the scene for inserting a record into the department table.
	 * This scene appears when insertRecord button is clicked in department scene.
	 * */
	void setInsertRecordScene(){

		Label[] labels = new Label[3];
		labels[0] = new Label("Department Name");
		labels[1] = new Label("Department ID");
		labels[2] = new Label("Instructor ID (Head)");

		for(int i=0;i<labels.length;i++){
			labels[i].setTextFill(Color.GOLD);
		}

		TextField [] fields = new TextField[3];

		for(int i = 0; i< fields.length ; i++){
			fields[i] = new TextField();
			fields[i].setPrefColumnCount(15);
		}

		Button back = new Button("Back");
		back.setMinWidth(200);
		back.setMinHeight(35);
		back.setOnAction(e->Controller.stage.setScene(departmentScene));

		submit.setOnAction(e ->{
			try {
				insertionMessage.setText("");
				insert(fields);
				insertionMessage.setTextFill(Color.GREEN);
				insertionMessage.setText("Data Inserted");
			}
			catch (SQLException ex){
				insertionMessage.setTextFill(Color.RED);
				insertionMessage.setText("Please insert all fields correctly");
				System.err.println(ex.getMessage());
			}
		});

		clear.setOnAction(e->{
			for(int i=0;i<fields.length;i++){
				fields[i].setText("");
			}
			insertionMessage.setText("");
		});

		grid1.add(labels[0], 0,0);
		grid1.add(fields[0], 1,0);
		grid1.add(labels[1], 0,1);
		grid1.add(fields[1], 1,1);
		grid1.add(labels[2], 0,2);
		grid1.add(fields[2], 1,2);

		grid2.add(submit, 0, 0);
		grid2.add(back, 0, 2);
		grid2.add(clear, 0, 1);
		hbox1.setSpacing(25);

		insertRecordScene = new Scene(formVbox, Controller.WIDTH, Controller.HEIGHT);

	}


	/**
	 * Sets the scene for deleting a record from department table.
	 * This scene appears when deleteRecord button is clicked in department scene.
	 * */
	void setDeleteRecordScene(){

		Label label = new Label("Department-ID");
		label.setTextFill(Color.GOLD);
		TextField field = new TextField();
		field.setPrefColumnCount(15);

		HBox hBox = new HBox(15);
		hBox.setAlignment(Pos.CENTER);
		hBox.setTranslateY(-35);
		hBox.getChildren().addAll(label, field);

		Button delete = new Button("Delete Record");
		Button clear = new Button("Clear");
		Button back = new Button("Back");

		delete.setMinWidth(200);
		delete.setMinHeight(35);
		clear.setMinWidth(200);
		clear.setMinHeight(35);
		back.setMinWidth(200);
		back.setMinHeight(35);

		delete.setOnAction(e->{
			try{
				deletionMessage.setText("");
				delete(field);
				deletionMessage.setTextFill(Color.GREEN);
				deletionMessage.setText("Record Deleted");
			}
			catch (SQLException ex){
				deletionMessage.setTextFill(Color.RED);
				deletionMessage.setText("Please insert all fields correctly");
				System.err.println(ex.getMessage());
			}
		});

		clear.setOnAction(e->{
			field.setText("");
			deletionMessage.setText("");
		});

		back.setOnAction(e->Controller.stage.setScene(departmentScene));

		deletionMessage.setTranslateY(-40);

		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(deletionMessage, hBox, delete, clear, back);
		vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		deleteRecordScene = new Scene(vBox, Controller.WIDTH, Controller.HEIGHT);

	}//End of method setDeleteRecordScene.


	/**
	 * Sets the scene for viewing department table.*/
	void setViewDataScene(){

		TableColumn<DepartmentData, String> column1 = new TableColumn<>("Department Name");
		column1.setCellValueFactory(new PropertyValueFactory<>("DepartmentName"));

		TableColumn<DepartmentData, String> column2 = new TableColumn<>("Department ID");
		column2.setCellValueFactory(new PropertyValueFactory<>("DepartmentID"));

		TableColumn<DepartmentData, String> column3 = new TableColumn<>("Head ID");
		column3.setCellValueFactory(new PropertyValueFactory<>("InstructorID"));

		column1.setPrefWidth(200);
		column2.setPrefWidth(200);
		column3.setPrefWidth(200);


		table = new TableView<>();
		table.setMaxSize(600,300);

		table.setItems(getData());
		table.getColumns().addAll(column1, column2, column3);


		Button back = new Button("Back");
		back.setMaxHeight(35);
		back.setMinWidth(200);
		back.setOnAction(
				e->Controller.stage.setScene(Controller.mainMenu.getSceneViewData()));

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(table, back);
		vbox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));

		ViewDepartment = new Scene(vbox, Controller.WIDTH, Controller.HEIGHT);


	}


	/**Returns the records of department table in an observable list.*/
	public ObservableList<DepartmentData> getData(){
		ObservableList<DepartmentData> list = FXCollections.observableArrayList();
		try {
			Statement st = Controller.connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Department");

			while(rs.next()) {
				list.add(new DepartmentData(rs.getString(1), rs.getString(2), rs.getString(3)));

			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}

		return list;
	}



}//End of class Instructor.