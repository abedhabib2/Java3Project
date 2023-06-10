package com.example.project.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

  private  static DB instance;
  private DB (){}
  public static DB getInstance(){
      if(instance==null){
          instance=new DB();
      }
      return  instance;
  }

  public Connection getConnection(){
      Connection conn;
      try {
          String url="jdbc:mysql://localhost:3306/clinic_appointment?serverTimezone=UTC";
          String username="root";
          String password="";
          Class.forName("com.mysql.cj.jdbc.Driver");
          conn= DriverManager.getConnection(url,username,password);
      } catch (ClassNotFoundException | SQLException e) {
          throw new RuntimeException(e);
      }
      return conn;
  }


}
