package com.example.project.Model;

import com.example.project.Controller.Login.PatientLoginController;
import com.example.project.View.PatientDashboard;
import com.example.project.View.PatientLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Appointment {
    int id;
    String date;
    String day;
    String time;
    String status;

    public Appointment(String date, String day, String time, String status) {
        this.date = date;
        this.day = day;
        this.time = time;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void save() throws SQLException {
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String insert = "INSERT INTO `appointments`(`appo_date`, `appo_day`, `appo_time`, `appo_status`) VALUES(?,?,?,?)";
        ps = conn.prepareStatement(insert);
        ps.setString(1, this.getDate());
        ps.setString(2, this.getDay());
        ps.setString(3, this.getTime());
        ps.setString(4, this.getStatus());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Appointment was add successfully");
        }
        if (ps != null) {
            ps.close();
        }
        conn.close();
    }

    public static ArrayList<Appointment> getAllAppointments() throws SQLException {
        ArrayList<Appointment> apps = new ArrayList<>();
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        String select = "SELECT * FROM `appointments` WHERE `appo_status` = 'free'";
        ps = conn.prepareStatement(select);
        ResultSet re = ps.executeQuery();
        while (re.next()) {
            Appointment app = new Appointment(re.getString("appo_date"), re.getString("appo_day"), re.getString("appo_time"), re.getString("appo_status"));
            app.setId(re.getInt("appo_id"));
            apps.add(app);
        }
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return apps;
    }

    public void delete() throws SQLException {
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        String delete = "DELETE FROM `appointments` WHERE appo_id=?";
        ps = conn.prepareStatement(delete);
        ps.setInt(1, this.getId());
        ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
        conn.close();
    }

    public void update() throws SQLException {
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        String update = "UPDATE `appointments` SET appo_date=?, appo_day=? , appo_time=? ,appo_status=? WHERE appo_id=?";
        ps = conn.prepareStatement(update);
        ps.setString(1, this.getDate());
        ps.setString(2, this.getDay());
        ps.setString(3, this.getTime());
        ps.setString(4, this.getStatus());
        ps.setInt(5, this.getId());
        int record = ps.executeUpdate();
        if (record > 0) {
            System.out.println("OK");
        }
        if (ps != null) {
            ps.close();

        }
        conn.close();

    }

    public void book() throws SQLException {
        addNewBookedApp();
        //removeFreeApp();
    }

    public void removeFreeApp() throws SQLException {
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        String delete = "DELETE FROM `appointments` WHERE `appo_id` = ? ";
        ps = conn.prepareStatement(delete);
        ps.setInt(1, this.getId());
        ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
        conn.close();
    }

    public void addNewBookedApp() throws SQLException {
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String insert = "INSERT INTO `booked_appointments`( `appo_id`, `user_id`, `booked_status`, `doctor_comment`) VALUES(?,?,?,?)";
        ps = conn.prepareStatement(insert);
        ps.setInt(1, this.getId());
        ps.setInt(2, PatientLoginController.id);
        ps.setString(3, "waiting");
        ps.setString(4, "No Comment");
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Appointment was add successfully");
        }
        if (ps != null) {
            ps.close();
        }
        conn.close();
    }

}
