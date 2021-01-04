package collegedatabaseapp;

import javafx.scene.Scene;

public class Instructor extends Table {

    Scene instructorScene;
    Scene insertRecordScene;
    Scene updateRecordScene;

    void setScene(){

        label.setText("Update Instructor Table");
        instructorScene = new Scene(vbox, Controller.WIDTH, Controller.HEIGHT);

    }//End of method setScene.


}//End of class Instructor.
