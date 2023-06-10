package com.example.project.Controller.Admin;

import com.example.project.App;
import com.example.project.Model.User;
import com.example.project.View.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminDashboardPatientsController implements Initializable {
    public static  User updateUser;

    @FXML
    private TableColumn<User, Integer> ageCol;

    @FXML
    private TableColumn<User, String> emailCol;

    @FXML
    private TableColumn<User, String> firstNameCol;

    @FXML
    private TextField firtsName;

    @FXML
    private TableColumn<User, String> genderCol;

    @FXML
    private TableColumn<User, Integer> idCol;

    @FXML
    private TableColumn<User, String> lastNameCol;

    @FXML
    private TableColumn<User, String> passwordCol;

    @FXML
    private TableView<User> patientTable;
    @FXML
    private TableColumn<User, Integer> phoneCol;

    @FXML
    private TableColumn<User, String> userNameCol;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
    }
    @FXML
    void create(ActionEvent event) throws IOException {
        ViewManager.openCreatePatient();
    }

    @FXML
    void delete(ActionEvent event) {

        if (patientTable.getSelectionModel().getSelectedItem() != null) {
            User user = patientTable.getSelectionModel().getSelectedItem();
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("User delete");
            deleteAlert.setContentText("Are you sure to delete this user ?");
            deleteAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        user.delete();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("User deleted");
                    deletedSuccessAlert.setContentText("User deleted");
                    deletedSuccessAlert.show();
                }
            });
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an user");
            warnAlert.setContentText("Please select an user from the table view");
            warnAlert.show();
        }
    }




    @FXML
    void goToAppointment(ActionEvent event) {ViewManager.adminDashboard.changeSceneToAdminAppointment();}

    @FXML
    void goToBookedAppointment(ActionEvent event) {
        ViewManager.adminDashboard.changeSceneToAdminBookedAppointment();
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        ViewManager.openPatientLogin();
        ViewManager.closeAdminDashboard();
    }



    @FXML
    void showAll(ActionEvent event) throws SQLException {
        ObservableList<User> list=FXCollections.observableArrayList(User.getAllPatient());
        patientTable.setItems(list);
    }

    @FXML
    void update(ActionEvent event) throws IOException {
        if(patientTable.getSelectionModel().getSelectedItem()!=null){
            updateUser=patientTable.getSelectionModel().getSelectedItem();
            System.out.println(updateUser.getId());
            //ViewManager.openUpdatePatient();
            FXMLLoader updatePatient = new FXMLLoader(App.class.getResource("View/admin/patient/update-patient.fxml"));
            Scene updatePatientScene = new Scene(updatePatient.load());
            Stage updateUserStage = new Stage();
            updateUserStage.setScene(updatePatientScene);
            updateUserStage.setTitle("Update Patient - " + updateUser.getUsername());
            updateUserStage.show();
        }else{
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an user");
            warnAlert.setContentText("Please select an user from the table view");
            warnAlert.show();
        }

    }
    @FXML
    void search(ActionEvent event) throws SQLException {
        ObservableList<User> list=FXCollections.observableArrayList(User.getSearchPatient(firtsName.getText()));
        patientTable.setItems(list);
        firtsName.setText("");
    }



}
