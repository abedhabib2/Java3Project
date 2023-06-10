module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
     requires mysql.connector.j;
     requires java.sql;



    exports com.example.project;
    opens com.example.project to javafx.fxml;
    exports com.example.project.Model;
    opens com.example.project.Model to javafx.fxml;
    exports com.example.project.Controller.Login;
    opens com.example.project.Controller.Login to javafx.fxml;
    exports com.example.project.Controller.Register;
    opens com.example.project.Controller.Register to javafx.fxml;
    exports com.example.project.Controller.Admin;
    opens com.example.project.Controller.Admin to javafx.fxml;
    exports com.example.project.Controller.Patient;
    opens com.example.project.Controller.Patient to javafx.fxml;

}