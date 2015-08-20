/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication;

import Frameworks.ConnectionManager;
import Frameworks.OperationResult;
import Frameworks.table.TableData;
import databaseapplication.admin.AdminMainWindow;
import databaseapplication.instructor.PackageMainInterface;
import databaseapplication.student.StudentMainWindow;
//import databaseapplication.student.StudentMainWindow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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
    /**
     * 
     */
    public static void showMainWindow(String id){
        if(userType.compareToIgnoreCase("Admin") == 0){
            new AdminMainWindow();
        }
        else if(userType.compareToIgnoreCase("student") == 0){
            try{
                new StudentMainWindow(password_);
            }
            catch(Exception Ex){}
        }
        else if(userType.compareToIgnoreCase("instructor") == 0){
            PackageMainInterface.runInstructorUI(password_);
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
        connection = new ConnectionManager("jdbc:derby://localhost:1527/ProjectDB","ibrahim","ibrahim","C:\\Users\\Ibrahim\\Documents\\GitHub\\project\\Phase II\\DatabaseApplication\\src\\DatabaseKeys.txt");
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
            userName = username;
            userType = loginType;
            password_ = password;
            if(loginType.compareToIgnoreCase("admin") == 0){
                if(username.compareToIgnoreCase("ibrahim") == 0 && password.compareToIgnoreCase("ibrahim") == 0){
                    return new OperationResult(true, "Admin login success!");
                }
                else{
                    return new OperationResult(false,"Login failed!. incorrect username or password.");
                }
            }
            else if(loginType.compareToIgnoreCase("student") == 0){
                try{
                    TableData data = connection.getResultSetAsTable("select id, first_name from student where id = "+password+" and first_name = '"+username+"'");
                    if(data.rows() == 1){
                        
                        return new OperationResult(true, "Student login success!");
                        
                    }
                    else{
                        return new OperationResult(false,"Login failed!. incorrect username or password.");
                    }
                }
                catch(Exception ex){
                    return new OperationResult(false,"Login failed!. incorrect username or password.");
                }
            }
            else if(loginType.compareToIgnoreCase("instructor") == 0){
                try{
                    TableData data = connection.getResultSetAsTable("select id, first_name from instructor where id = "+password+" and first_name = '"+username+"'");
                    if(data.rows() == 1){
                        
                        return new OperationResult(true, "Instructor login success!");
                        
                    }
                    else{
                        return new OperationResult(false,"Login failed!. incorrect username or password.");
                    }
                }
                catch(Exception ex){
                    return new OperationResult(false,"Login failed!. incorrect username or password.");
                }
                
            }
        }
        return null;
    }

    public static LinkedList<String> getTables() {
        if(connection != null){
            return connection.getTables();
        }
        return null;
    }

    public static ConnectionManager getConnectionManager() {
        return connection;
    }

    public static OperationResult delete(String selectedTableName, String deleteCondition) {
       return connection.delete(selectedTableName, deleteCondition);
    }

    public static OperationResult update(String tableName, String newValues, String condition) {
        return connection.executeUpdate(tableName, newValues, condition);
    }
}
