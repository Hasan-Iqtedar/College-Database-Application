package collegedatabaseapp;

import javafx.scene.Scene;

public class Student extends Table {

    Scene studentScene;
    Scene insertRecordScene;
    Scene updateRecordScene;

    void setScene(){

        //Setting Action Events for the buttons.

        label.setText("Update Student Table");
        studentScene = new Scene(vbox ,Controller.WIDTH, Controller.HEIGHT);

    }

}//End of class Student.
