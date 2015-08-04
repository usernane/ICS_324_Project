/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication;

import Frameworks.ConnectionManager;
import Frameworks.OperationResult;
import databaseapplication.admin.AdminMainWindow;
import databaseapplication.instructor.InstructorMainWindow;
import databaseapplication.student.StudentMainWindow;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Ibrahim
 */
public class SuperManager {
    
    private static String userName;
    private static String password_;
    private static String userType;
    private static ConnectionManager connection;
    
    private static void showLoginDialog(){
        LoginDialog d = new LoginDialog();
    }
    
    public static void showMainWindow(){
        if(userType.compareToIgnoreCase("Admin") == 0){
            new AdminMainWindow();
        }
        else if(userType.compareToIgnoreCase("student") == 0){
            new StudentMainWindow();
        }
        else if(userType.compareToIgnoreCase("instructor") == 0){
            new InstructorMainWindow();
        }
    }
    
    public static OperationResult executeQuery(String val){
        return connection.executeQuery(val);
    }
    
    public static ResultSet getResultSet(){
        return connection.getResultSet();
    }
    
    public static OperationResult insert(String tableName, String values){
        return connection.insert(tableName, values);
    }
    
    public static void startApp(){
        connection = new ConnectionManager("jdbc:derby://localhost:1527/AppDB","ibrahim","ibrahim");
        OperationResult r = connection.openConnection();
        if(r.getResult()){
            JOptionPane.showMessageDialog(null, r.getMessage(), "Connection State", JOptionPane.INFORMATION_MESSAGE);
            showLoginDialog();
        }
        else{
            JOptionPane.showMessageDialog(null, r.getMessage()+". The app will close.", "Connection State", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }
    
    public static OperationResult login( String loginType, String username, String password){
        if(loginType != null){
            if(loginType.compareToIgnoreCase("admin") == 0){
                if(username.compareToIgnoreCase("ibrahim") == 0 && password.compareToIgnoreCase("ibrahim") == 0){
                    userName = username;
                    userType = loginType;
                    password_ = password;
                    return new OperationResult(true, "Admin login success!");
                }
                else{
                    return new OperationResult(false,"Login failed!. incorrect username or password.");
                }
            }
            else if(loginType.compareToIgnoreCase("student") == 0){
                
            }
            else if(loginType.compareToIgnoreCase("instructor") == 0){
                
            }
        }
        return null;
    }
}
