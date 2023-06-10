package com.example.project.Controller.Register;

import com.example.project.Model.User;
import com.example.project.View.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class PatientRegisterController {

    @FXML
    private TextField age;

    @FXML
    private TextField email;

    @FXML
    private TextField firtsName;

    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phone;

    @FXML
    private TextField username;

    @FXML
    void createNewUser(ActionEvent event) throws SQLException {
        String username1=username.getText();
        String password1=password.getText();
        String firstname1=firtsName.getText();
        String lastname1=lastName.getText();

        int age1=Integer.parseInt(age.getText());
        int phone1=Integer.parseInt(phone.getText());
        String gender1=((RadioButton)gender.getSelectedToggle()).getText();
        String  email1=email.getText();
        User user=new User(username1,password1,firstname1,lastname1,age1,email1,phone1,gender1,"patient");
        user.save();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User inserted");
        alert.setContentText("User inserted");
        alert.showAndWait();
        this.reset();
    }

    @FXML
    void showLoginPage(ActionEvent event) throws IOException {
        ViewManager.openPatientLogin();
        ViewManager.closePatientRegister();
    }

    public void reset(){
        username.setText("");
        password.setText("");
        firtsName.setText("");
        lastName.setText("");
        age.setText("");
        phone.setText("");
        email.setText("");
        ((RadioButton)gender.getSelectedToggle()).setSelected(false);

    }
}
