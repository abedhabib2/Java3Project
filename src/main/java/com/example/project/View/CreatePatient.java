package com.example.project.View;

import com.example.project.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreatePatient extends Stage {
    public CreatePatient() throws IOException {
        FXMLLoader createPatient = new FXMLLoader(App.class.getResource("View/admin/patient/create-patient.fxml"));
        Scene createPatientScene = new Scene(createPatient.load());
        this.setScene(createPatientScene);
        this.setTitle("Create Patient");
    }
}
