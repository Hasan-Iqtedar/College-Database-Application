
package collegedatabaseapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.*;

public class Controller extends Application {

    static final int WIDTH = 750, HEIGHT = 400;
    static MainMenu mainMenu;
    static Scene scene;
    static Stage stage;

    static Connection connection;
    static String password;
    static String userName;


    public void start(Stage stage) throws Exception {

        Controller.stage = stage;
        mainMenu = new MainMenu();

        login();

        stage.setTitle("College Database Application");
        stage.setResizable(false);
        stage.show();

    }//End of method start.


    /**
     * To establish connection with the database.*/
    public void connect() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{

        Class.forName("com.mysql.jdbc.Driver");//.newInstance();

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/collegedatabase", userName, password);
        System.out.println("Database connected");

    }//End of method connect.


    /**
     * To login into the application and connect to the database.*/
    public void login(){

        Label label0 = new Label("");
        Label label1 = new Label("Username");
        Label label2 = new Label("Password");

        label0.setTextFill(Color.RED);
        label1.setTextFill(Color.GOLD);
        label2.setTextFill(Color.GOLD);

        label0.setFont(new Font(15));

        TextField textField1 = new TextField();
        //TextField textField2 = new TextField();

        PasswordField passwordField = new PasswordField();


        Button loginButton = new Button("Sign-in");
        loginButton.setMinWidth(100);

        loginButton.setOnAction(e->{
            try {
                userName = textField1.getText();
                password = passwordField.getText();

                connect();
                mainMenu.setMenuElements(); }
            catch(SQLException ex1){label0.setText("Incorrect Credentials");}
            catch(ClassNotFoundException ex2){label0.setText("Incorrect Credentials");}
            catch(IllegalAccessException ex3){label0.setText("Incorrect Credentials");}
            catch(InstantiationException ex4){label0.setText("Incorrect Credentials");}
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.add(label1, 0, 0);
        grid.add(textField1, 1, 0);
        grid.add(label2, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.addRow(4);
        grid.add(loginButton, 0, 4, 2, 1);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label0, grid);
        vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene loginScene = new Scene(vBox, WIDTH, HEIGHT);

        stage.setScene(loginScene);

    }//End of method login.


    public static void main(String[] args) {
        launch(args);
    }


}//End of Controller class.
