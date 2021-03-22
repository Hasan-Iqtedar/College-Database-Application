package collegedatabaseapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Student extends Table {

	Scene studentScene;
	Scene studentView;
	TableView<StudentData> table;

	private String query;

	private Scene insertRecordScene;
	private Scene deleteRecordScene;

	/**Constructor to make student scene ready for insertion and deletion*/
	Student(){
		query = "SELECT * FROM Student";
		insertionQuery = "INSERT INTO Student VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		deletionQuery = "DELETE FROM Student WHERE s_id = ?";
		setScene();
		setStudentView();
		setInsertRecordScene();
		setDeleteRecordScene();
	}//End of constructor.


	/**
	 * Sets the scene for updating student table.
	 * This scene appears when the studentButton is clicked.
	 * */

	void setScene(){

		//Setting insertRecord and deleteRecord buttons.
		insertRecord.setOnAction(e->Controller.stage.setScene(insertRecordScene));
		deleteRecord.setOnAction(e->Controller.stage.setScene(deleteRecordScene));

		label.setText(" Update\n Student\n Table");
		studentScene = new Scene(hBox ,Controller.WIDTH, Controller.HEIGHT);

	}//End of method setStudentScene.


	/**
	 * Sets the scene for inserting a record into the student table.
	 * This scene appears when insertRecord button is clicked in student scene.
	 * */
	void setInsertRecordScene() {

		TextField [] fields = new TextField[11];

		for(int i =0; i< fields.length ; i++){
			fields[i] = new TextField();
			fields[i].setPrefColumnCount(15);
		}

		//Label studentNameLabel = new Label("Student Name");
		Label [] labels = new Label[11];
		labels[0] = new Label("Student Id");
		labels[1] = new Label("Name");
		labels[2] = new Label("Level (UG/PG)");
		labels[3] = new Label("Year");
		labels[4] = new Label("Status");
		labels[5] = new Label("CUM GPA");
		labels[6] = new Label("Major");
		labels[7] = new Label("City");
		labels[8] = new Label("Language");
		labels[9] = new Label("Blood Group");
		labels[10] = new Label("Age");

		for(int i=0;i<labels.length;i++){
			labels[i].setTextFill(Color.GOLD);
		}

		Button back = new Button("Back");
		back.setMinWidth(200);
		back.setMinHeight(35);
		back.setOnAction(e->Controller.stage.setScene(studentScene));

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

		grid1.add(labels[3], 0,0);
		grid1.add(fields[3], 1,0);
		grid1.add(labels[4], 0,1);
		grid1.add(fields[4], 1,1);
		grid1.add(labels[5], 0,2);
		grid1.add(fields[5], 1,2);

		grid2.add(labels[0], 0, 0);
		grid2.add(fields[0], 1, 0);
		grid2.add(labels[1], 0,1);
		grid2.add(fields[1], 1,1);
		grid2.add(labels[2], 0,2);
		grid2.add(fields[2], 1,2);

		GridPane grid3 = new GridPane();
		grid3.setHgap(25);
		grid3.setVgap(10);
		grid3.setAlignment(Pos.CENTER);

		grid3.add(labels[6], 0,0);
		grid3.add(fields[6], 1,0);
		grid3.add(labels[7], 0,1);
		grid3.add(fields[7], 1,1);
		grid3.add(labels[8], 0,2);
		grid3.add(fields[8], 1,2);

		GridPane grid4 = new GridPane();
		grid4.setHgap(30);
		grid4.setVgap(10);
		grid4.setAlignment(Pos.CENTER);

		grid4.add(labels[9], 0,0);
		grid4.add(fields[9], 1,0);
		grid4.add(labels[10], 0,1);
		grid4.add(fields[10], 1,1);
		//grid4.add(submit, 1,2);

		HBox hBox2 = new HBox(10);
		hBox2.setAlignment(Pos.CENTER);
		hBox2.getChildren().addAll(grid3, grid4);

		formVbox.getChildren().addAll(hBox2, submit, clear, back);
		insertRecordScene = new Scene(formVbox, Controller.WIDTH, Controller.HEIGHT);

	}//End of method setInsertRecordScene.


	/**
	 * Sets the scene for deleting a record from student table.
	 * This scene appears when deleteRecord button is clicked in student scene.
	 * */
	void setDeleteRecordScene(){

		Label label = new Label("Student-ID");
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

		back.setOnAction(e->Controller.stage.setScene(studentScene));

		deletionMessage.setTranslateY(-40);

		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(deletionMessage, hBox, delete, clear, back);
		vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		deleteRecordScene = new Scene(vBox, Controller.WIDTH, Controller.HEIGHT);

	}//End of method setDeleteRecordScene.


	/**
	 * Sets the scene for viewing student table.*/
	void setStudentView(){

		TableColumn<StudentData, String> col1 = new TableColumn<>("S_Id");
		col1.setCellValueFactory(new PropertyValueFactory<>("s_id"));
		TableColumn<StudentData, String> col = new TableColumn<>("Name");
		col.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<StudentData, String> col3 = new TableColumn<>("Level");
		col3.setCellValueFactory(new PropertyValueFactory<>("level"));
		TableColumn<StudentData, String> col4 = new TableColumn<>("Year");
		col4.setCellValueFactory(new PropertyValueFactory<>("year"));
		TableColumn<StudentData, String> col5 = new TableColumn<>("Status");
		col5.setCellValueFactory(new PropertyValueFactory<>("status"));
		TableColumn<StudentData, String> col6 = new TableColumn<>("GPA");
		col6.setCellValueFactory(new PropertyValueFactory<>("gpa"));
		TableColumn<StudentData, String> col7 = new TableColumn<>("Major");
		col7.setCellValueFactory(new PropertyValueFactory<>("major"));
		TableColumn<StudentData, String> col8 = new TableColumn<>("City");
		col8.setCellValueFactory(new PropertyValueFactory<>("city"));
		TableColumn<StudentData, String> col9 = new TableColumn<>("Language");
		col9.setCellValueFactory(new PropertyValueFactory<>("language"));
		TableColumn<StudentData, String> col10 = new TableColumn<>("Blood Group");
		col10.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));
		TableColumn<StudentData, String> col11 = new TableColumn<>("Age");
		col11.setCellValueFactory(new PropertyValueFactory<>("age"));

		table = new TableView<>();
		table.setMaxSize(600,300);

		table.setItems(getData());
		table.getColumns().addAll(col1, col, col3, col4, col5, col6, col7, col8, col9, col10, col11);

		Button back = new Button("Back");
		back.setMinWidth(200);
		back.setMinHeight(35);
		back.setOnAction(e->Controller.stage.setScene(Controller.mainMenu.getSceneViewData()));

		ComboBox cB = new ComboBox();
		cB.getItems().add("All");
		cB.getItems().add("Freshmen");
		cB.getItems().add("Sophomore");
		cB.getItems().add("Junior");
		cB.getItems().add("Senior");

		cB.setMaxWidth(100);
		cB.setMinHeight(35);

		cB.setPromptText("Select year");

		cB.setOnAction(e->{
			switch(cB.getSelectionModel().getSelectedIndex()){
				case 0: query = "SELECT * FROM Student"; table.setItems(getData()); break;
				case 1: query = "SELECT * FROM Student WHERE year = 'Freshmen' "; table.setItems(getData()); break;
				case 2: query = "select * from student where year = 'Sophomore'"; table.setItems(getData()); break;
				case 3: query = "SELECT * FROM Student WHERE year = 'Junior' "; table.setItems(getData()); break;
				case 4: query = "SELECT * FROM Student WHERE year = 'Senior' "; table.setItems(getData()); break;
			}
		});

		VBox v = new VBox(10);
		v.setAlignment(Pos.CENTER);
		v.getChildren().addAll(cB, table, back);
		v.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));

		studentView= new Scene(v, Controller.WIDTH, Controller.HEIGHT);
	}


	/**Returns the records of Student table in an observable list.*/
	public ObservableList<StudentData> getData(){
		ObservableList<StudentData> list = FXCollections.observableArrayList();
		try {
			Statement st = Controller.connection.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()){
				list.add(new StudentData(rs.getString(1), rs.getString(2)
						,rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getString(10),rs.getString(11)));
			}

		}
		catch(SQLException ex){ex.printStackTrace();}

		return list;
	}


}//End of class Student.



