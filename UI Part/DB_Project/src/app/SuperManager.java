/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.newCollegeDialog.NewCollegeDialog;
import com.newComTest.ConnectionManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ibrahim
 */
public class SuperManager {
    
    private static ConnectionManager connection;
    
    public static void startApp(){
        try{
            connection = new ConnectionManager("jdbc:derby://localhost:1527/TestDatabase","ibrahim","ibrahim");
            
            new NewCollegeDialog();
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
    
    public static void addDepartment(){
        
    }
}
