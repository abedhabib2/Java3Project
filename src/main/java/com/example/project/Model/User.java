package com.example.project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    int id;
    String username;
    String password;
    String firstname;
    String lastname;
    int age;
    String email;
    int phone;

    String gender;
    String role;

    public User() {
        setId(-1);
    }

    public User(String username, String password, String firstname, String lastname, int age, String email, int phone, String gender, String role) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void save() throws SQLException {
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String insert = "INSERT INTO `users`( `username`, `password`, `firstname`, `lastname`, `age`, `email`, `phone`, `gender`, `role`) VALUES(?,?,?,?,?,?,?,?,?)";
        ps = conn.prepareStatement(insert);
        ps.setString(1, this.getUsername());
        ps.setString(2, this.getPassword());
        ps.setString(3, this.getFirstname());
        ps.setString(4, this.getLastname());
        ps.setInt(5, this.getAge());
        ps.setString(6, this.getEmail());
        ps.setInt(7, this.getPhone());
        ps.setString(8, this.getGender());
        ps.setString(9, this.getRole());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUsername() + "User was add successfully");
        }
        if (ps != null) {
            ps.close();
        }
        conn.close();
    }

    public static User getUser(String username) throws SQLException {
        Connection conn = DB.getInstance().getConnection();
        String select = "SELECT * FROM `users` WHERE username=?  ";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(select);
        ps.setString(1, username);
        ResultSet re = null;
        re = ps.executeQuery();
        User user = new User();
        while (re.next()) {
            user = new User(
                    re.getString("username"),
                    re.getString("password"),
                    re.getString("firstname"),
                    re.getString("lastname"),
                    re.getInt("age"),
                    re.getString("email"),
                    re.getInt("phone"),
                    re.getString("gender"),
                    re.getString("role")
            );
            user.setId(re.getInt("user_id"));
        }

        if (ps != null) {
            ps.close();
        }
        conn.close();
        return user;
    }

    public static ArrayList<User> getAllPatient() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        String select = "SELECT * FROM `users` WHERE role=? ";
        ps = conn.prepareStatement(select);
        ps.setString(1, "patient");
        ResultSet re = ps.executeQuery();
        while (re.next()) {
            User user = new User(re.getString("username"), re.getString("password"), re.getString("firstname"), re.getString("lastname"), re.getInt("age"), re.getString("email"), re.getInt("phone"), re.getString("gender"), re.getString("role"));
            user.setId(re.getInt("user_id"));
            users.add(user);
        }
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return users;
    }

    public static ArrayList<User> getSearchPatient(String firstname) throws SQLException {
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        String select = "SELECT * FROM `users` WHERE role=? AND firstname LIKE '"+ firstname +"%' ";
        ps = conn.prepareStatement(select);
        ps.setString(1, "patient");
        //ps.setString(2, firstname);
        ResultSet re = ps.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (re.next()) {
            User user = new User(re.getString("username"), re.getString("password"), re.getString("firstname"), re.getString("lastname"), re.getInt("age"), re.getString("email"), re.getInt("phone"), re.getString("gender"), re.getString("role"));
            user.setId(re.getInt("user_id"));
            users.add(user);
        }
        if (ps != null) {
            ps.close();
        }
        conn.close();
        return users;

    }

    public void delete() throws SQLException {
        Connection conn = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        String delete = "DELETE FROM `users` WHERE user_id=?";
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
        String update = "UPDATE `users` SET username=?, password=? ,firstname=? ,lastname=?, age=?, email=?, phone=? ,gender=? WHERE user_id=?";
        ps = conn.prepareStatement(update);
        ps.setString(1, this.getUsername());
        ps.setString(2, this.getPassword());
        ps.setString(3, this.getFirstname());
        ps.setString(4, this.getLastname());
        ps.setInt(5, this.getAge());
        ps.setString(6, this.getEmail());
        ps.setInt(7, this.getPhone());
        ps.setString(8, this.getGender());
        ps.setInt(9, this.getId());
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
