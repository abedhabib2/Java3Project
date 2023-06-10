package com.example.project.Controller.Admin;

import com.example.project.App;
import com.example.project.Model.Appointment;
import com.example.project.Model.Booked_Appointment;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminDashboardBookedAppointmentController implements Initializable {
    public static  Booked_Appointment updateBookedApp;
    @FXML
    private TableColumn<Integer, Booked_Appointment> idCol;

    @FXML
    private TableColumn<String, Booked_Appointment> statusCol;

    @FXML
    private TableColumn<String, Booked_Appointment> commentCol;

    @FXML
    private TextField firtsName;

    @FXML
    private TableColumn<Integer, Booked_Appointment> uIdCol;

    @FXML
    private TableView<Booked_Appointment> bAppTable;

    @FXML
    private TableColumn<Integer, Booked_Appointment> aIdCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        aIdCol.setCellValueFactory(new PropertyValueFactory<>("appoId"));
        uIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        commentCol.setCellValueFactory(new PropertyValueFactory<>("DoctorComment"));
    }

    @FXML
    void search(ActionEvent event) throws SQLException {
        ObservableList<Booked_Appointment> list=FXCollections.observableArrayList(Booked_Appointment.getSearchBookedApps(firtsName.getText()));
        bAppTable.setItems(list);
        firtsName.setText("");
    }

    @FXML
    void showAll(ActionEvent event) throws SQLException {
        ObservableList<Booked_Appointment> list= FXCollections.observableArrayList(Booked_Appointment.getAllBookedAppointments());
        bAppTable.setItems(list);
    }

    @FXML
    void finish(ActionEvent event) throws IOException {
        if(bAppTable.getSelectionModel().getSelectedItem()!=null){
            updateBookedApp=bAppTable.getSelectionModel().getSelectedItem();
            //ViewManager.openFinishBookedAppointment();
            FXMLLoader finishBookedAppointment = new FXMLLoader(App.class.getResource("View/admin/booked_appointment/finish_booked_appointment.fxml"));
            Scene finishBookedAppointmentScene = new Scene(finishBookedAppointment.load());
            Stage finishStage = new Stage();
            finishStage.setScene(finishBookedAppointmentScene);
            finishStage.setTitle("Finish Booked Appointment - " + updateBookedApp.getId());
            finishStage.show();
        }else{
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an appointment");
            warnAlert.setContentText("Please select an appointment from the table view");
            warnAlert.show();
        }

    }

    @FXML
    void goToAppointment(ActionEvent event) {
        ViewManager.adminDashboard.changeSceneToAdminAppointment();
    }

    @FXML
    void goToPatient(ActionEvent event) {
        ViewManager.adminDashboard.changeSceneToAdminPatient();
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        ViewManager.openPatientLogin();
        ViewManager.closeAdminDashboard();
    }

}
