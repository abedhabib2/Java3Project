package com.example.project.Controller.Admin;

import com.example.project.Model.Appointment;
import com.example.project.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {
    Appointment oldApp;
    @FXML
    private DatePicker date;

    @FXML
    private TextField time;

    @FXML
    private TextField day;

    @FXML
    void update(ActionEvent event) throws SQLException {
        String date1 = date.getValue().toString();
        String day1 = day.getText();
        String time1 = time.getText();
        Appointment appointment = new Appointment(date1, day1, time1, "free");

        appointment.setId(oldApp.getId());
        appointment.update();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment updated");
        alert.setContentText("Appointment updated");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(AdminDashboardAppointmentsController.updateAppointment!=null){
            oldApp = AdminDashboardAppointmentsController.updateAppointment;
            date.setValue(LocalDate.parse(oldApp.getDate()));
            day.setText(oldApp.getDay());
            time.setText(oldApp.getTime());
        }else{

        }
    }
}
