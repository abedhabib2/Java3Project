package com.example.project.View;

import com.example.project.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAppointment extends Stage {
    public CreateAppointment() throws IOException {
        FXMLLoader createAppointment = new FXMLLoader(App.class.getResource("View/admin/appointment/create-appointment.fxml"));
        Scene createAppointmentScene = new Scene(createAppointment.load());
        this.setScene(createAppointmentScene);
        this.setTitle("Create Appointment");
    }
}
