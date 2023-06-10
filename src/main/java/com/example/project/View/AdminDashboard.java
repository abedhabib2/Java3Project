package com.example.project.View;

import com.example.project.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboard extends Stage {
    private final Scene patientScene;
    private final Scene appointmentScene;
    private final Scene bookedAppointmentScene;
    public AdminDashboard() throws IOException {
        FXMLLoader patient = new FXMLLoader(App.class.getResource("View/admin/patient/admin-dashboard-patients.fxml"));
        patientScene = new Scene(patient.load());
        changeSceneToAdminPatient();

        FXMLLoader appointment = new FXMLLoader(App.class.getResource("View/admin/appointment/admin-dashboard-appointment.fxml"));
        appointmentScene = new Scene(appointment.load());

        FXMLLoader bookedAppointment = new FXMLLoader(App.class.getResource("View/admin/booked_appointment/admin-dashboard-booked-appointment.fxml"));
        bookedAppointmentScene = new Scene(bookedAppointment.load());

    }

    public void changeSceneToAdminPatient() {
        this.setScene(patientScene);
        this.setTitle("admin-patient");
    }

    public void changeSceneToAdminAppointment() {
        this.setScene(appointmentScene);
        this.setTitle("admin-appointment");
    }

    public void changeSceneToAdminBookedAppointment() {
        this.setScene(bookedAppointmentScene);
        this.setTitle("admin-booked-appointment");
    }
}
