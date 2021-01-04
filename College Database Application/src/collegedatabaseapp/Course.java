package collegedatabaseapp;

import javafx.scene.Scene;

class Course extends Table{

    Scene courseScene;
    Scene insertRecordScene;
    Scene updateRecordScene;

    void setScene(){


        label.setText("Update Course Table");
        courseScene = new Scene(vbox, Controller.WIDTH, Controller.HEIGHT);


    }//End of method setCourseScene.

}//End of class Course.
