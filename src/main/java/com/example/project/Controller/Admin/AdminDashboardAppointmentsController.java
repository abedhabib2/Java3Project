package com.example.project.Controller.Admin;

import com.example.project.App;
import com.example.project.Model.Appointment;
import com.example.project.Model.User;
import com.example.project.View.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminDashboardAppointmentsController implements Initializable {

    public static Appointment updateAppointment;

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
    void create(ActionEvent event) throws IOException {
        ViewManager.openCreateAppointment();
    }

    @FXML
    void update(ActionEvent event) throws IOException {
        if(appTable.getSelectionModel().getSelectedItem()!=null) {
            updateAppointment=appTable.getSelectionModel().getSelectedItem();
            //ViewManager.openUpdateAppointment();
            FXMLLoader updateAppointmentLoader = new FXMLLoader(App.class.getResource("View/admin/appointment/update-appointment.fxml"));
            Scene updateAppointmentScene = new Scene(updateAppointmentLoader.load());
            Stage updateAppStage = new Stage();
            updateAppStage.setScene(updateAppointmentScene);
            updateAppStage.setTitle("Update Appointment - " + updateAppointment.getId());
            updateAppStage.show();
        } else{
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an appointment");
            warnAlert.setContentText("Please select an appointment from the table view");
            warnAlert.show();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        if(appTable.getSelectionModel().getSelectedItem()!=null) {
            Appointment appointment=appTable.getSelectionModel().getSelectedItem();
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Appointment delete");
            deleteAlert.setContentText("Are you sure to delete this Appointment ?");
            deleteAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        appointment.delete();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Appointment deleted");
                    deletedSuccessAlert.setContentText("Appointment deleted");
                    deletedSuccessAlert.show();
                }
            });
        }else{
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an Appointment");
            warnAlert.setContentText("Please select an Appointment from the table view");
            warnAlert.show();
        }
    }

    @FXML
    void goToPatients(ActionEvent event) {
        ViewManager.adminDashboard.changeSceneToAdminPatient();
    }

    @FXML
    void goToBookedAppointment(ActionEvent event) {
        ViewManager.adminDashboard.changeSceneToAdminBookedAppointment();
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        ViewManager.openPatientLogin();
        ViewManager.closeAdminDashboard();
    }

}
