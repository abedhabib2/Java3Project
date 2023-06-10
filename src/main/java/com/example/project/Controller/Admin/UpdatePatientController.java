package com.example.project.Controller.Admin;

import com.example.project.Model.User;
import com.example.project.View.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdatePatientController implements Initializable {
    User oldUser;
    @FXML
    private TextField age;

    @FXML
    private TextField email;

    @FXML
    private RadioButton female;

    @FXML
    private TextField firtsName;

    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField lastName;

    @FXML
    private RadioButton male;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phone;

    @FXML
    private TextField username;


    @FXML
    void cancel(ActionEvent event) {}

    @FXML
    void update(ActionEvent event) throws SQLException {
        if (username.getText().isEmpty() || password.getText().isEmpty() ||
                firtsName.getText().isEmpty() || lastName.getText().isEmpty() ||
                age.getText().isEmpty() || email.getText().isEmpty()||
                phone.getText().isEmpty() || gender.getSelectedToggle()==null) {
            System.out.println("Make sure there are no empty fields");
        } else{
            String username1 = username.getText();
            String password1 = password.getText();
            String firstname1 = firtsName.getText();
            String lastname1 = lastName.getText();
            int age1 = Integer.parseInt(age.getText());
            int phone1 = Integer.parseInt(phone.getText());
            String gender1 = ((RadioButton) gender.getSelectedToggle()).getText();
            String email1 = email.getText();
            User user = new User(username1, password1, firstname1, lastname1, age1, email1, phone1, gender1, "patient");
            user.setId(oldUser.getId());
            user.update();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patient Updated");
            alert.setContentText("Patient Updated");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(AdminDashboardPatientsController.updateUser!=null){
      oldUser  = AdminDashboardPatientsController.updateUser;
        username.setText(oldUser.getUsername());
        password.setText(oldUser.getPassword());
        firtsName.setText(oldUser.getFirstname());
        lastName.setText(oldUser.getLastname());
        email.setText(oldUser.getEmail());
        phone.setText((String.valueOf(oldUser.getPhone())) );
        age.setText(String.valueOf(oldUser.getAge()));
        if(oldUser.getGender().equals("male")){
            male.setSelected(true);
        }else{
            female.setSelected(true);
        }

        }else{

        }
    }
}
