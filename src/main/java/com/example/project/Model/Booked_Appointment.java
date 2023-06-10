package com.example.project.Model;

import com.example.project.Controller.Login.PatientLoginController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Booked_Appointment {
    int id;
    int appoId;
    int userId;
    String status;
    String doctorComment;

    public Booked_Appointment(int appoId, int userId, String status, String doctorComment) {
        this.appoId = appoId;
        this.userId = userId;
        this.status = status;
        this.doctorComment = doctorComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppoId() {
        return appoId;
    }

    public void setAppoId(int appoId) {
        this.appoId = appoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorComment() {
        return doctorComment;
    }

    public void setDoctorComment(String doctorComment) {
        this.doctorComment = doctorComment;
    }

    public static ArrayList<Booked_Appointment> getAllBookedAppointments() throws SQLException {
        ArrayList<Booked_Appointment> bApps = new ArrayList<>();
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        String select = "SELECT * FROM `booked_appointments`";
        ps = conn.prepareStatement(select);
        ResultSet re = ps.executeQuery();
        while (re.next()) {
            Booked_Appointment bApp = new Booked_Appointment(re.getInt("appo_id"), re.getInt("user_id"), re.getString("booked_status"), re.getString("doctor_comment"));
            bApp.setId(re.getInt("booked_id"));
            bApps.add(bApp);
        }
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return bApps;
    }

    public static ArrayList<Booked_Appointment> getSearchBookedApps(String firstname) throws SQLException {
        ArrayList<Booked_Appointment> bApps = new ArrayList<>();
        ArrayList<User> users = User.getSearchPatient(firstname);
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;

        for (User user : users) {
            String select = "SELECT * FROM `booked_appointments` WHERE `user_id` = ? ";
            ps = conn.prepareStatement(select);
            ps.setInt(1, user.getId());
            ResultSet re = ps.executeQuery();
            while (re.next()) {
                Booked_Appointment bApp = new Booked_Appointment(re.getInt("appo_id"), re.getInt("user_id"), re.getString("booked_status"), re.getString("doctor_comment"));
                bApp.setId(re.getInt("booked_id"));
                bApps.add(bApp);
            }
        }
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return bApps;
    }

    public static ArrayList<Booked_Appointment> getBookedApps() throws SQLException {
        ArrayList<Booked_Appointment> bApps = new ArrayList<>();
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        String select = "SELECT * FROM `booked_appointments` WHERE `user_id` = ? ";
        ps = conn.prepareStatement(select);
        ps.setInt(1, PatientLoginController.id);
        ResultSet re = ps.executeQuery();
        while (re.next()) {
            Booked_Appointment bApp = new Booked_Appointment(re.getInt("appo_id"), re.getInt("user_id"), re.getString("booked_status"), re.getString("doctor_comment"));
            bApp.setId(re.getInt("booked_id"));
            bApps.add(bApp);
        }

        ps.close();
        conn.close();
        return bApps;
    }

    public void finishAppointment() throws SQLException {
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        String update = "UPDATE `booked_appointments` SET booked_status = ? , doctor_comment = ? WHERE appo_id = ? AND user_id = ?";
        ps = conn.prepareStatement(update);
        ps.setString(1, "finished");
        ps.setString(2, this.getDoctorComment());
        ps.setInt(3, this.getAppoId());
        ps.setInt(4, this.getUserId());
        int record = ps.executeUpdate();
        if (record > 0) {
            System.out.println("OK");
        }
        if (ps != null) {
            ps.close();
        }
        conn.close();
    }
}
