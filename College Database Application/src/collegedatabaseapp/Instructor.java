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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Instructor extends Table {

	Scene instructorScene;
	Scene insertRecordScene;
	Scene deleteRecordScene;
	Scene instructorView;

	TableView<InstructorData> table;

	/**Constructor to make instructor scene ready for insertion and deletion*/
	Instructor(){
		setScene();
		setInstructorView();
		setInsertRecordScene();
		setDeleteRecordScene();
		insertionQuery = "INSERT INTO Instructor VALUES (?, ?, ?, ?, ?)";
		deletionQuery = "DELETE FROM Instructor WHERE instructor_id = ?";
	}

	/**
	 * Sets the scene for updating instructor table.
	 * This scene appears when the instructorButton is clicked.
	 * */
	void setScene(){

		//Setting insertRecord button.
		insertRecord.setOnAction(e->Controller.stage.setScene(insertRecordScene));
		deleteRecord.setOnAction(e->Controller.stage.setScene(deleteRecordScene));
		label.setText(" Update\n Instructor\n Table");
		instructorScene = new Scene(hBox, Controller.WIDTH, Controller.HEIGHT);

	}//End of method setCourseScene.


	/**
	 * Sets the scene for inserting a record into the instructor table.
	 * This scene appears when insertRecord button is clicked in instructor scene.
	 * */
	void setInsertRecordScene(){

		Label[] labels = new Label[5];
		labels[0] = new Label("Instructor Name");
		labels[1] = new Label("Instructor ID");
		labels[2] = new Label("Instructor Rank");
		labels[3] = new Label("Office");
		labels[4] = new Label("Department ID");

		for(int i=0;i<labels.length;i++){
			labels[i].setTextFill(Color.GOLD);
		}

		TextField [] fields = new TextField[5];

		for(int i = 0; i< fields.length ; i++){
			fields[i] = new TextField();
			fields[i].setPrefColumnCount(15);
		}

		Button back = new Button("Back");
		back.setMinWidth(200);
		back.setMinHeight(35);
		back.setOnAction(e->Controller.stage.setScene(instructorScene));

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

		grid2.add(labels[3], 0, 0);
		grid2.add(fields[3], 1, 0 );
		grid2.add(labels[4], 0, 1);
		grid2.add(fields[4], 1, 1 );


		formVbox.getChildren().addAll(submit, clear, back);
		insertRecordScene = new Scene(formVbox, Controller.WIDTH, Controller.HEIGHT);  

	}


	/**
	 * Sets the scene for deleting a record from instructor table.
	 * This scene appears when deleteRecord button is clicked in instructor scene.
	 * */
	void setDeleteRecordScene(){

		Label label = new Label("Instructor-ID");
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

		back.setOnAction(e->Controller.stage.setScene(instructorScene));

		deletionMessage.setTranslateY(-40);

		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(deletionMessage, hBox, delete, clear, back);
		vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		deleteRecordScene = new Scene(vBox, Controller.WIDTH, Controller.HEIGHT);

	}////End of method setDeleteRecordScene.


	/**
	 * Sets the scene for viewing instructor table.*/
	void setInstructorView(){

		TableColumn<InstructorData, String> column1 = new TableColumn<>("Instructor Name");
		column1.setCellValueFactory(new PropertyValueFactory<>("InstructorName"));

		TableColumn<InstructorData, String> column2 = new TableColumn<>("Instructor ID");
		column2.setCellValueFactory(new PropertyValueFactory<>("InstructorId"));

		TableColumn<InstructorData, String> column3 = new TableColumn<>("Instructor Rank");
		column3.setCellValueFactory(new PropertyValueFactory<>("InstructorRank"));

		TableColumn<InstructorData, String> column4 = new TableColumn<>("Office");
		column4.setCellValueFactory(new PropertyValueFactory<>("office"));

		TableColumn<InstructorData, String> column5 = new TableColumn<>("Department ID");
		column5.setCellValueFactory(new PropertyValueFactory<>("DepartmentId"));

		column1.setPrefWidth(130);
		column2.setPrefWidth(130);
		column3.setPrefWidth(130);
		column4.setPrefWidth(130);
		column5.setPrefWidth(130);

		table = new TableView<>();
		table.setMaxSize(600,300);

		table.setItems(getData());
		table.getColumns().addAll(column1, column2, column3, column4, column5);


		Button back = new Button("Back");
		back.setMaxHeight(35);
		back.setMinWidth(200);
		back.setOnAction(e->Controller.stage.setScene(Controller.mainMenu.getSceneViewData()));

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(table, back);
		vbox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));

		instructorView = new Scene(vbox, Controller.WIDTH, Controller.HEIGHT);
	}


	/**Returns the records of instructor table in an observable list.*/
	public ObservableList<InstructorData> getData(){
		ObservableList<InstructorData> list = FXCollections.observableArrayList();
		try {
			Statement st = Controller.connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Instructor");

			while(rs.next()){
				list.add(new InstructorData(rs.getString(1), rs.getString(2)
						,rs.getString(3), rs.getString(4), rs.getString(5)));

			}

		}
		catch(SQLException ex){
			ex.printStackTrace();
		}

		return list;
	}



}//End of class Instructor.