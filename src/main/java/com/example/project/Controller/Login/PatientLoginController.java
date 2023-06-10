package com.example.project.Controller.Login;

import com.example.project.Model.User;
import com.example.project.View.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class PatientLoginController {

    public static int id;
    public static String fname;
    @FXML
    private PasswordField password;

    @FXML
    private Label status;

    @FXML
    private TextField userName;

    @FXML
    void adminLogin(ActionEvent event) throws IOException {
        ViewManager.openAdminLogin();
        ViewManager.closePatientLogin();
    }

    @FXML
    void patientLogin(ActionEvent event) throws SQLException, IOException {
        if(userName.getText().isEmpty()){
            status.setText("User name is Empty");
        }else if(password.getText().isEmpty()){

            status.setText("Password is Empty");
        }else{
            User user= User.getUser(userName.getText());
            if(user.getId()==-1 || user.getRole().equals("admin")){
                status.setText("User name error");
            }else{
                if(user.getPassword().equals(password.getText())){
                    id = user.getId();
                    fname = user.getFirstname();
                    System.out.println("FN : "+ fname);
                    //status.setText("Done - " + id);
                    this.reset();
                    ViewManager.openPatientDashboard();
                    ViewManager.closePatientLogin();
                }else{
                    status.setText("password error");
                }

            }
        }



    }

    @FXML
    void CreateAccount(ActionEvent event) throws IOException {
        ViewManager.openPatientRegister();
        ViewManager.closePatientLogin();
    }

    public void reset() {
        userName.setText("");
        password.setText("");
    }

}
