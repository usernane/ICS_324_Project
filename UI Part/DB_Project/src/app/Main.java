/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author Ibrahim
 */
import app.Dialogs.NewCollegeDialog;
import javax.swing.UIManager;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){}
        //ConnectionManager c = new ConnectionManager("jdbc:derby://localhost:1527/TestDatabase","ibrahim","ibrahim");
        //System.out.println(c.getState());
        SuperManager.startApp();
    }
    
}
