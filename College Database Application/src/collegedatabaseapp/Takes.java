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

public class Takes extends Table {

	Scene TakeScene;
	Scene insertRecordScene;
	Scene deleteRecordScene;
	Scene ViewTakes;

	TableView<TakesData> table;

	Takes(){
		setScene();
		setViewDataScene();
		setInsertRecordScene();
		setDeleteRecordScene();
		insertionQuery = "INSERT INTO Takes VALUES (?, ?, ?)";
		deletionQuery = "DELETE FROM Takes WHERE course_id = ? and s_id = ?";
	}

	/**
	 * Sets the scene for updating student table.
	 * This scene appears when the departmentButton is clicked.
	 * */
	void setScene(){

		//Setting insertRecord button.
		insertRecord.setOnAction(e->Controller.stage.setScene(insertRecordScene));
		deleteRecord.setOnAction(e->Controller.stage.setScene(deleteRecordScene));

		label.setText(" Update\n Student's \n Courses");
		TakeScene = new Scene(hBox, Controller.WIDTH, Controller.HEIGHT);

	}//End of method setCourseScene.


	/**
	 * Sets the scene for inserting a record into the takes table.
	 * This scene appears when insertRecord button is clicked in takes scene.
	 * */
	void setInsertRecordScene(){

		Label[] labels = new Label[3];
		labels[0] = new Label("Student ID");
		labels[1] = new Label("Course ID");
		labels[2] = new Label("Attendance");

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
		back.setOnAction(e->Controller.stage.setScene(TakeScene));

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
	 * Sets the scene for deleting a record from takes table.
	 * This scene appears when deleteRecord button is clicked in takes scene.
	 * */
	void setDeleteRecordScene(){

		Label[] labels = new Label[2];
		labels[0] = new Label("Student ID");
		labels[1] = new Label("Course ID");

		for(int i=0;i<labels.length;i++){
			labels[i].setTextFill(Color.GOLD);
		}

		TextField [] fields = new TextField[2];

		for(int i = 0; i< fields.length ; i++){
			fields[i] = new TextField();
			fields[i].setPrefColumnCount(15);
		}
		
		HBox hBox = new HBox(15);
		hBox.setAlignment(Pos.CENTER);
		hBox.setTranslateY(-35);
		hBox.getChildren().addAll(labels[0], fields[0], labels[1], fields[1]);

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
				delete(fields);
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
			fields[0].setText("");
			fields[1].setText("");
			deletionMessage.setText("");
		});

		back.setOnAction(e->Controller.stage.setScene(TakeScene));

		deletionMessage.setTranslateY(-40);

		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(deletionMessage, hBox, delete, clear, back);
		vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		deleteRecordScene = new Scene(vBox, Controller.WIDTH, Controller.HEIGHT);

	}//End of method setDeleteRecordScene.


	/**
	 * Sets the scene for viewing takes table.*/
	void setViewDataScene(){

		TableColumn<TakesData, String> column1 = new TableColumn<>("Student Name");
		column1.setCellValueFactory(new PropertyValueFactory<>("sname"));

		TableColumn<TakesData, String> column2 = new TableColumn<>("Course");
		column2.setCellValueFactory(new PropertyValueFactory<>("cname"));

		TableColumn<TakesData, String> column3 = new TableColumn<>("Instructor");
		column3.setCellValueFactory(new PropertyValueFactory<>("ins_name"));

		TableColumn<TakesData, String> column4 = new TableColumn<>("Department");
		column4.setCellValueFactory(new PropertyValueFactory<>("dept_name"));

		table = new TableView<>();
		table.setMaxSize(600,300);

		table.setItems(getData());
		table.getColumns().addAll(column1, column2, column3, column4);

		Button back = new Button("Back");
		back.setMaxHeight(35);
		back.setMinWidth(200);
		back.setOnAction(e->Controller.stage.setScene(Controller.mainMenu.getSceneViewData()));

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(table, back);
		vbox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));

		ViewTakes = new Scene(vbox, Controller.WIDTH, Controller.HEIGHT);

	}


	/**Returns the records of student course info in an observable list.*/
	public ObservableList<TakesData> getData(){
		ObservableList<TakesData> list = FXCollections.observableArrayList();
		try {
			Statement st = Controller.connection.createStatement();

			ResultSet rs = st.executeQuery("SELECT sname, cname, ins_name as instructor, dept_name FROM student"
					+ " INNER JOIN takes ON student.s_id = takes.s_id"
					+ " INNER JOIN course ON course.course_id = takes.course_id"
					+ " INNER JOIN instructor ON course.instructor_id = instructor.instructor_id"
					+ " INNER JOIN department ON instructor.dept_id = department.dept_id");

			while(rs.next()) {
				list.add(new TakesData(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4)));
			}

		}
		catch(SQLException ex){
			ex.printStackTrace();
		}

		return list;
	}


	/**To delete a record from a table.*/
	private void delete(TextField[] fields) throws SQLException{
		statement = Controller.connection.prepareStatement(deletionQuery);
		for(int i = 0; i<fields.length;i++) {
			if(!fields[i].getText().isEmpty()) {
				statement.setString(i+1, fields[i].getText());
			}
			else
				throw new SQLException("Required Empty Field");

			fields[i].setText("");
		}
		statement.executeUpdate();

		System.out.println("Record Deleted");

	}//End of method delete.


}//End of class Instructor.