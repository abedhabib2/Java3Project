package com.example.project;

import com.example.project.View.ViewManager;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        ViewManager.openPatientLogin();
    }

    public static void main(String[] args) {
        launch();
    }
}