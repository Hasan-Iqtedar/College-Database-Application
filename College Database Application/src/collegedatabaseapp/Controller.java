
package collegedatabaseapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller extends Application {

    static final int WIDTH = 750, HEIGHT = 400;
    static MainMenu mainMenu;

    static Scene scene;
    static Stage stage;



    public void start(Stage stage) throws Exception {


        Controller.stage = stage;

        mainMenu = new MainMenu();
        mainMenu.setMenuElements();

        //stage.setScene(scene);
        stage.setTitle("College Database Application");
        stage.show();

    }//End of method start.


    public static void main(String[] args) {
        launch(args);
    }


}//End of Controller class.
