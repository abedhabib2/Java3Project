package com.example.project.Controller.Admin;

import com.example.project.Model.Booked_Appointment;
import com.example.project.Model.User;
import com.example.project.View.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FinishBookedAppointmentController implements Initializable {
    Booked_Appointment oldBookedAppointment;

    @FXML
    private TextField comment;

    @FXML
    void finish(ActionEvent event) throws SQLException, IOException {
        Booked_Appointment bApp = new Booked_Appointment(
                oldBookedAppointment.getAppoId(),
                oldBookedAppointment.getUserId(),
                oldBookedAppointment.getStatus(),
                comment.getText());

        bApp.setId(oldBookedAppointment.getId());
        bApp.finishAppointment();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment Finished");
        alert.setContentText("Appointment Finished");
        alert.showAndWait();
        comment.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oldBookedAppointment = AdminDashboardBookedAppointmentController.updateBookedApp;
    }
}
