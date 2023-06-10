package com.example.project.View;

import com.example.project.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLogin extends Stage {
    public AdminLogin() throws IOException {

        FXMLLoader admin = new FXMLLoader(App.class.getResource("View/login/admin-login.fxml"));
        Scene patientRegister = new Scene(admin.load());
        this.setScene(patientRegister);
        this.setTitle("Admin-Login");
    }
}
