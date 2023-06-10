package com.example.project.View;

import com.example.project.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientDashboard extends Stage {
    private final Scene appointmentScene;
    private final Scene b_appointmentScene;

    public PatientDashboard() throws IOException {
        FXMLLoader appointment = new FXMLLoader(App.class.getResource("View/patient/appointment/patient-dashboard-appointment.fxml"));
        appointmentScene = new Scene(appointment.load());
        changeSceneToPatientAppointment();

        FXMLLoader b_appointment = new FXMLLoader(App.class.getResource("View/patient/appointment/patient-dashboard-booked-appointment.fxml"));
        b_appointmentScene = new Scene(b_appointment.load());


    }

    public void changeSceneToPatientAppointment() {
        this.setScene(appointmentScene);
        this.setTitle("patient-appointment");
    }

    public void changeSceneToPatientBAppointment() {
        this.setScene(b_appointmentScene);
        this.setTitle("patient-booked-appointment");
    }
}
