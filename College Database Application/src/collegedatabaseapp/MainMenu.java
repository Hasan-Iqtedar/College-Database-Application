package collegedatabaseapp;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class MainMenu extends Theme{

	private Scene sceneInsertData;
	private Scene sceneViewData;

	private Student student;
	private Course course;
	private Instructor instructor;
	private Department department;
	private Takes take;

	Scene getSceneInsertData(){
		return sceneInsertData;
	}

	Scene getSceneViewData(){
		return sceneViewData;
	}


	/**
	 * Sets the scene for inserting data.
	 * This scene includes 5 buttons.
	 * 4 for inserting student, course, instructor and department data, 1 to update student's courses and 1 to go back.*/
	private void setSceneInsertData(){

		Button back = new Button("Back");
		Button studentButton = new Button("Student");
		Button instructorButton = new Button("Instructor");
		Button courseButton = new Button("Course");
		Button departmentButton = new Button("Department");
		Button takesButton = new Button("Student's Courses");

		//Setting Action Events for the buttons.
		//Setting each button to switch to respective scenes.
		back.setOnAction(e-> Controller.stage.setScene(Controller.scene));
		studentButton.setOnAction(e-> Controller.stage.setScene(student.studentScene));
		courseButton.setOnAction(e-> Controller.stage.setScene(course.courseScene));
		instructorButton.setOnAction(e->Controller.stage.setScene(instructor.instructorScene));
		departmentButton.setOnAction(e->Controller.stage.setScene(department.departmentScene));
		takesButton.setOnAction(e->Controller.stage.setScene(take.TakeScene));

		Label label = new Label(" Insert into\n Table");
		label.setTranslateY(80);
		label.setFont(new Font(50));
		label.setTextFill(Color.GOLD);

		VBox vBox1 = new VBox();
		VBox vBox2 = new VBox();
		HBox hBox = new HBox(150);

		setLayout( studentButton, courseButton, instructorButton, departmentButton, takesButton, back, label, hBox, vBox1, vBox2);

		sceneInsertData = new Scene(hBox, Controller.WIDTH, Controller.HEIGHT);

	}//End of method setSceneInsertData.


	/**
	 * Sets the scene for viewing data.
	 * This scene includes 5 buttons.
	 * 4 for viewing student, course, instructor and department data and 1 to go back.*/
	private void setSceneViewData() {

		Button back = new Button("Back");
		Button studentButton = new Button("Student");
		Button instructorButton = new Button("Instructor");
		Button courseButton = new Button("Course");
		Button departmentButton = new Button("Department");
		Button studentCourseInfoButton = new Button("Student Course Info");

		//Setting back button to switch back to main menu.
		back.setOnAction(e-> Controller.stage.setScene(Controller.scene));

		//Setting studentButton to switch to student scene where student table can be updated.
		studentButton.setOnAction(e->{
			student.table.setItems(student.getData());
			Controller.stage.setScene(student.studentView);
		});


		//Setting courseButton to switch to course scene where course table can be updated.
		courseButton.setOnAction(e->{
			course.table.setItems(course.getData());
			Controller.stage.setScene(course.courseView);
		});


		//Setting instructorButton to switch to instructor scene where instructor table can be updated.
		instructorButton.setOnAction(e->{
			instructor.table.setItems(instructor.getData());
			Controller.stage.setScene(instructor.instructorView);
		});


		//Setting instructorButton to switch to department scene where department table can be updated.
		departmentButton.setOnAction(e->{
			department.table.setItems(department.getData());
			Controller.stage.setScene(department.ViewDepartment);
		});

		//Setting studentCourseInfoButton to switch to a scene where student Course Info can be updated.
		studentCourseInfoButton.setOnAction(e->{
			take.table.setItems(take.getData());
			Controller.stage.setScene(take.ViewTakes);
		});

		Label label = new Label("  View data\n  of Table");
		label.setTranslateY(80);
		label.setFont(new Font(50));
		label.setTextFill(Color.GOLD);

		VBox vBox1 = new VBox();
		VBox vBox2 = new VBox();
		HBox hBox = new HBox(150);

		setLayout(studentButton, courseButton, instructorButton, departmentButton, studentCourseInfoButton, back, label, hBox, vBox1, vBox2);
		sceneViewData = new Scene(hBox, Controller.WIDTH, Controller.HEIGHT);

	}//End of method setSceneViewData.


	/**
	 * Sets the layout for Insert and View data scenes.*/
	private void setLayout(Button bT1, Button bT2, Button bT3, Button bT4, Button bT5, Button  bT6, Label lB, HBox hB, VBox vB1, VBox vB2 ){

		bT1.setMinWidth(200);
		bT2.setMinWidth(200);
		bT3.setMinWidth(200);
		bT4.setMinWidth(200);
		bT5.setMinWidth(200);
		bT6.setMinWidth(200);
		
		bT1.setMinHeight(35);
		bT2.setMinHeight(35);
		bT3.setMinHeight(35);
		bT4.setMinHeight(35);
		bT5.setMinHeight(35);
		bT6.setMinHeight(35);
		
		vB1.getChildren().addAll(lB);
		vB2.getChildren().addAll(bT1, bT2, bT3, bT4, bT5, bT6);

		vB1.setMinWidth(300);
		vB1.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

		vB2.setAlignment(Pos.CENTER);
		vB2.setSpacing(15);

		hB.getChildren().addAll(vB1, vB2);
		hB.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

	}//End of method setLayout.


	/**
	 * Sets the main menu widgets which includes 2 buttons
	 * 1. For inserting data.
	 * 2. For viewing data.*/
	void setMenuElements(){

		student = new Student();
		course = new Course();
		instructor = new Instructor();
		department = new Department();
		take = new Takes();

		Button insertData = new Button("Insert Data");
		Button viewData = new Button ("View Data");
		Button exit = new Button ("Exit");

		//Setting buttons' sizes.
		insertData.setMinHeight(35);
		insertData.setMinWidth(200);
		viewData.setMinHeight(35);
		viewData.setMinWidth(200);
		exit.setMinHeight(35);
		exit.setMinWidth(200);

		setSceneInsertData();
		setSceneViewData();

		//Setting Actions Events for the buttons.
		insertData.setOnAction(e ->Controller.stage.setScene(sceneInsertData));

		viewData.setOnAction(e ->Controller.stage.setScene(sceneViewData));

		exit.setOnAction(e -> System.exit(1));

		Label label = new Label("Main Menu");
		label.setTranslateX(25);
		label.setFont(new Font(50));
		label.setTextFill(Color.GHOSTWHITE);

		Label label2 = new Label("  College\n  Database\n  Project");
		label2.setFont(new Font(50));
		label2.setTextFill(Color.GHOSTWHITE);

		//Setting the theme.
		setTheme();

		vBox1.getChildren().addAll(label, label2);
		vBox2.getChildren().addAll(insertData, viewData, exit);

		Controller.scene = new Scene(hBox, Controller.WIDTH, Controller.HEIGHT);
		Controller.stage.setScene(Controller.scene);

	}//End of method setMenuElement.


}//End of class MainMenu.



