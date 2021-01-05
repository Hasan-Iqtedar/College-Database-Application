package collegedatabaseapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public class MainMenu {

	private Scene sceneInsertData;
	private Scene sceneViewData;

	private Student student;
	private Course course;
	private Instructor instructor;


	Scene getSceneInsertData(){
		return sceneInsertData;
	}
	Scene getSceneViewData(){
		return sceneViewData;
	}


	private void setSceneInsertData(){

		Button back = new Button("Main Menu");
		Button studentButton = new Button("Student");
		Button instructorButton = new Button("Instructor");
		Button courseButton = new Button("Course");
		Button departmentButton = new Button("Department");

		back.setMinWidth(165);
		studentButton.setMinWidth(80);
		instructorButton.setMinWidth(80);
		courseButton.setMinWidth(80);
		departmentButton.setMinWidth(80);

		//Setting Action Events for the buttons.

		//Setting back button to switch back to main menu.
		back.setOnAction(e-> Controller.stage.setScene(Controller.scene));

		//Setting studentButton to switch to student scene where student table can be updated.
		studentButton.setOnAction(e-> Controller.stage.setScene(student.studentScene));

		//Setting courseButton to switch to course scene where course table can be updated.
		courseButton.setOnAction(e-> Controller.stage.setScene(course.courseScene));

		//Setting instructorButton to switch to instructor scene where instructor table can be updated.
		instructorButton.setOnAction(e->Controller.stage.setScene(instructor.instructorScene));

		Label label = new Label("Insert into Database");
		label.setFont(new Font(50));
		label.setTextFill(Color.GOLD);

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.TOP_CENTER);
		hbox.getChildren().add(label);
		hbox.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(15);
		grid.setAlignment(Pos.CENTER);
		grid.add(studentButton, 0, 0);
		grid.add(courseButton, 1, 0);
		grid.add(instructorButton, 0, 1);
		grid.add(departmentButton, 1, 1);
		grid.add(back, 0,2, 5, 1);


		VBox vbox = new VBox(70);
		vbox.getChildren().addAll(hbox, grid);

		sceneInsertData = new Scene(vbox, Controller.WIDTH, Controller.HEIGHT);

	}//End of method setSceneInsertData.


	void setSceneViewData() {
		Button back = new Button("Main Menu");
		Button student = new Button("Student");
		Button instructor = new Button("Instructor");
		Button course = new Button("Course");
		Button department = new Button("Department");

		back.setMinWidth(165);
		student.setMinWidth(80);
		instructor.setMinWidth(80);
		course.setMinWidth(80);
		department.setMinWidth(80);

		Label label = new Label("View Data from relation");
		label.setFont(new Font(50));
		label.setTextFill(Color.GOLD);

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.TOP_CENTER);
		hbox.getChildren().add(label);
		hbox.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(15);
		grid.add(student, 0, 0);
		grid.add(course, 1, 0);
		grid.add(instructor, 0, 1);
		grid.add(department, 1, 1);
		grid.add(back, 0,2, 5, 1);

		grid.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(70);
		vbox.getChildren().addAll(hbox, grid);
		back.setOnAction(e-> Controller.stage.setScene(Controller.scene));

		sceneViewData = new Scene(vbox, Controller.WIDTH, Controller.HEIGHT);

	}


	void setMenuElements(){

		student = new Student();
		course = new Course();
		instructor = new Instructor();


		student.setScene();
		course.setScene();
		instructor.setScene();

		Button insertData = new Button("Insert Data");
		Button viewData = new Button ("View Data");
		Button Exit = new Button ("Exit");
		//Setting buttons' size.
		insertData.setMinHeight(35);
		insertData.setMinWidth(100);
		viewData.setMinHeight(35);
		viewData.setMinWidth(100);
		Exit.setMinHeight(35);
		Exit.setMinWidth(100);

		//Setting Actions Events for the buttons.
		insertData.setOnAction(e -> {
			setSceneInsertData();
			Controller.stage.setScene(sceneInsertData);
		});
		viewData.setOnAction(e -> {
			setSceneViewData();
			Controller.stage.setScene(sceneViewData);
		});

		Exit.setOnAction(e ->{
			System.exit(1);
		});

		//Adding buttons in a grid.
		GridPane grid = new GridPane();
		grid.setVgap(5);
		grid.add(insertData, 1, 1);
		grid.add(viewData, 1, 2);
		grid.setAlignment(Pos.CENTER);

		//Setting Main Menu label in a HBox.
		HBox hbox = new HBox(20);
		Label label = new Label("Main Menu");
		//label.setAlignment(Pos.TOP_CENTER);
		label.setFont(new Font(50));
		label.setTextFill(Color.WHITE);

		hbox.getChildren().add(label);
		hbox.setAlignment(Pos.TOP_CENTER);
		hbox.setBackground(new Background(new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));

		VBox vbox = new VBox(20);
		vbox.getChildren().addAll(hbox, grid);

		Controller.scene = new Scene(vbox, Controller.WIDTH, Controller.HEIGHT);
		Controller.stage.setScene(Controller.scene);

	}//End of method setMenuElement.




}//End of class MainMenu.
