package com.example.project.Controller.Patient;

import com.example.project.Controller.Login.PatientLoginController;
import com.example.project.Model.Appointment;
import com.example.project.Model.Booked_Appointment;
import com.example.project.View.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientDashBoardBookedAppointmentController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        aIdCol.setCellValueFactory(new PropertyValueFactory<>("appoId"));
        uIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        commentCol.setCellValueFactory(new PropertyValueFactory<>("DoctorComment"));
    }
    @FXML
    private TableColumn<Integer, Booked_Appointment> idCol;

    @FXML
    private TableColumn<String, Booked_Appointment> statusCol;

    @FXML
    private TableColumn<String, Booked_Appointment> commentCol;

    @FXML
    private TableColumn<Integer, Booked_Appointment> uIdCol;

    @FXML
    private TableView<Booked_Appointment> bAppTable;

    @FXML
    private TableColumn<Integer, Booked_Appointment> aIdCol;
    @FXML
    void showAll(ActionEvent event) throws SQLException {
        ObservableList<Booked_Appointment> list= FXCollections.observableArrayList(Booked_Appointment.getBookedApps());
        bAppTable.setItems(list);
    }

    @FXML
    void goToAppointment(ActionEvent event) {
        ViewManager.patientDashboard.changeSceneToPatientAppointment();
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        ViewManager.openPatientLogin();
        ViewManager.closePatientDashboard();
    }
}
