package com.example.project.Controller.Admin;

import com.example.project.Model.Appointment;
import com.example.project.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.sql.SQLException;

public class CreateAppointmentController {

    @FXML
    private DatePicker date;

    @FXML
    private TextField time;

    @FXML
    private TextField day;

    @FXML
    void create(ActionEvent event) throws SQLException {
        String date1 = date.getValue().toString();
        String day1 = day.getText();
        String time1 = time.getText();
        Appointment appointment = new Appointment(date1, day1, time1, "free");
        appointment.save();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment inserted");
        alert.setContentText("Appointment inserted");
        alert.showAndWait();
    }
}
