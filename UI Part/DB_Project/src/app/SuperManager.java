/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.Dialogs.NewCollegeDialog;
import app.Dialogs.NewDepartmentDialog;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ibrahim
 * This class is the hear of our application. most of the operations will be done from here.
 */
public class SuperManager {
    
    private static ConnectionManager connection;
    
    public static void startApp(){
        try{
            connection = new ConnectionManager("jdbc:derby://localhost:1527/TestDatabase","ibrahim","ibrahim");
            //just for testing
            new NewDepartmentDialog();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error: Could not connect to the database", "Connection Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }
    
    public static OperationResult addCollege(String unique_id, String name, String abbr){
        String message = "Failed to add record!, no open connection.";
        if(connection.isConnected()){
            for(int i = 0 ; i < 26 ; i++){
                try{
                    connection.insert("college","'"+unique_id+"','"+name+"','"+abbr+"'");
                    return new OperationResult(true,"New record was added!");
                }
                catch(SQLException ex){
                    message = "Failed to add record!, "+ex.getMessage();
                }
            }
        }
        return new OperationResult(false,message);
    }
    public LinkedList<String> getDepartmentsNames(){
        try{
            ResultSet rs = connection.executeQuery("select department.name from department");
            LinkedList<String> names = new LinkedList<>();
            while(rs.next()){
                names.add(rs.getString(1));
            }
            return names;
        }
        catch(SQLException ex){return null;}
    }
    public static LinkedList<String> getCollegesNames(){
        try{
            ResultSet rs = connection.executeQuery("select college.name from college");
            LinkedList<String> names = new LinkedList<>();
            while(rs.next()){
                names.add(rs.getString(1));
            }
            return names;
        }
        catch(SQLException ex){return null;}
    }
    public static LinkedList<String> getCollegeIDs(){
        try{
            ResultSet rs = connection.executeQuery("select college.id from college");
            LinkedList<String> names = new LinkedList<>();
            while(rs.next()){
                names.add(rs.getString(1));
            }
            return names;
        }
        catch(SQLException ex){return null;}
    }
    public static OperationResult addDepartment(String id, String name, String abbreviation, String collegeID){
        String message = "Failed to add record!, no open connection.";
        if(connection.isConnected()){
            for(int i = 0 ; i < 26 ; i++){
                try{
                    connection.insert("department","'"+id+"','"+name+"','"+abbreviation+"','"+collegeID+"'");
                    return new OperationResult(true,"New record was added!");
                }
                catch(SQLException ex){
                    message = "Failed to add record!, "+ex.getMessage();
                }
            }
        }
        return new OperationResult(false,message);
    }
}
