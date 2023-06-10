package com.example.project.View;

import com.example.project.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientRegister extends Stage {
    private  Scene patientRegister;
   public PatientRegister() throws IOException {

       FXMLLoader patine = new FXMLLoader(App.class.getResource("View/Registration/patient-register.fxml"));
       Scene patientRegister = new Scene(patine.load());
       this.setScene(patientRegister);
       this.setTitle("Patient-Register");

   }

}
