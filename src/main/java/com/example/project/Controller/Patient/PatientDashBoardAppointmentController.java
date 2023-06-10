package com.example.project.Controller.Patient;

import com.example.project.App;
import com.example.project.Model.Appointment;
import com.example.project.View.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientDashBoardAppointmentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    @FXML
    private TableColumn<Appointment, Integer> idCol;

    @FXML
    private TableColumn<Appointment,String> dateCol;

    @FXML
    private TableColumn<Appointment,String> statusCol;

    @FXML
    private TableView<Appointment> appTable;

    @FXML
    private TableColumn<Appointment,String> dayCol;

    @FXML
    private TableColumn<Appointment,String> timeCol;

    @FXML
    void showAll(ActionEvent event) throws SQLException {
        ObservableList<Appointment> list= FXCollections.observableArrayList(Appointment.getAllAppointments());
        appTable.setItems(list);
    }

    @FXML
    void book(ActionEvent event) throws SQLException {
        if (appTable.getSelectionModel().getSelectedItem()!=null) {
            Appointment selectedApp = appTable.getSelectionModel().getSelectedItem();
            selectedApp.book();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Booked");
            alert.setContentText("Appointment Booked");
            alert.showAndWait();
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an appointment");
            warnAlert.setContentText("Please select an appointment from the table view");
            warnAlert.show();
        }
    }

    @FXML
    void goToBookedAppointment(ActionEvent event) {
        ViewManager.patientDashboard.changeSceneToPatientBAppointment();
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        ViewManager.openPatientLogin();
        ViewManager.closePatientDashboard();
    }
}
