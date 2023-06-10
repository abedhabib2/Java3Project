package com.example.project.View;

import com.example.project.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientLogin extends Stage {
    public PatientLogin() throws IOException {

        FXMLLoader patient = new FXMLLoader(App.class.getResource("View/login/patient-login.fxml"));
        Scene patientRegister = new Scene(patient.load());
        this.setScene(patientRegister);
        this.setTitle("Patient-Login");
    }
}
