package com.example.project.View;

import java.io.IOException;

public class ViewManager {
    private static PatientRegister register;
    private  static AdminLogin adminLogin;
    private  static  PatientLogin patientLogin;
    private  static  AdminRegister adminRegister;
    public  static AdminDashboard adminDashboard;
    private static CreatePatient createPatient;
    private static CreateAppointment createAppointment;
    public static PatientDashboard patientDashboard;
    private ViewManager(){}

    public static void openPatientRegister() throws IOException {
        if(register==null)
            register=new PatientRegister();
        register.show();
    }
    public  static  void closePatientRegister(){
        if(register!=null)
            register.close();
    }
    public static void  openAdminLogin() throws IOException {
         if(adminLogin==null)
             adminLogin=new AdminLogin();
         adminLogin.show();
    }
    public static  void closeAdminLogin(){
        if(adminLogin!=null)
            adminLogin.close();
    }

    public static void  openPatientLogin() throws IOException {
        if(patientLogin==null)
            patientLogin=new PatientLogin();
        patientLogin.show();
    }
    public static  void closePatientLogin(){
        if(patientLogin!=null)
            patientLogin.close();
    }

    public static void  openAdminRegister() throws IOException {
        if(adminRegister==null)
            adminRegister=new AdminRegister();
        adminRegister.show();
    }
    public static  void closeAdminRegister(){
        if(adminRegister!=null)
            adminRegister.close();
    }

    public static void  openAdminDashboard() throws IOException {
        if(adminDashboard==null)
            adminDashboard=new AdminDashboard();
        adminDashboard.show();
    }
    public static  void closeAdminDashboard(){
        if(adminDashboard!=null)
            adminDashboard.close();
    }

    public static void openCreatePatient() throws IOException {
        if (createPatient==null)
            createPatient = new CreatePatient();
        createPatient.show();
    }

    public static void openCreateAppointment() throws IOException {
        if(createAppointment==null)
            createAppointment=new CreateAppointment();
        createAppointment.show();
    }



    public static void openPatientDashboard() throws IOException {
        if(patientDashboard==null)
            patientDashboard=new PatientDashboard();
        patientDashboard.show();
    }

    public static void closePatientDashboard(){
        if(patientDashboard!=null)
            patientDashboard.close();
    }
}
