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

public class AdminLoginController {

    @FXML
    private Label status;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    @FXML
    void create(ActionEvent event) throws IOException {
        ViewManager.openAdminRegister();
        ViewManager.closeAdminLogin();

    }

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
        if(userName.getText().isEmpty()){
            status.setText("User name is Empty");
        }else if(password.getText().isEmpty()){

            status.setText("Password is Empty");
        }else{
            User user= User.getUser(userName.getText());
            if(user.getId()==-1 || user.getRole().equals("patient")){
                status.setText("User name error");
            }else{
                if(user.getPassword().equals(password.getText())){
                    ViewManager.openAdminDashboard();
                    ViewManager.closeAdminLogin();
                   this.reset();
                }else{
                    status.setText("password error");
                }

            }
        }

    }
    public void reset(){
        userName.setText("");
        password.setText("");
    }

}
