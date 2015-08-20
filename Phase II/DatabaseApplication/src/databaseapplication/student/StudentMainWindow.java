
package databaseapplication.student;


import Frameworks.OperationResult;
import databaseapplication.SuperManager;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author aqeil54
 */


public class StudentMainWindow extends JFrame implements ActionListener
{
JFrame jFrame = new JFrame("The available sections");
public static int height = 250;
public static int width = 800;
private String studentID ;

JPanel pane1 = new JPanel();

JPanel pane2 = new JPanel();
JPanel pane3 = new JPanel();
public  GetCurrentDate gd;

JLabel lb1 = new JLabel("Enter the course number:");
JLabel lb2 = new JLabel("Enter the course number:");
JLabel lb3 = new JLabel("Enter Your ID");
JLabel lb4 = new JLabel();

JButton registerButton = new JButton("Register");
JButton b22 = new JButton();
JButton viewCoursesButton = new JButton("View Courses");
JButton getSectionsButton = new JButton("Get Sections");
JButton dropCourseButton = new JButton("Drop");
JButton viewButton = new JButton("View");


ArrayList<String> elements = new ArrayList<>();
String[] strigs;
DefaultComboBoxModel model ;
protected static TextField  secRefNumInput = new TextField  (20);
protected static TextField  lblInput2 = new TextField  (20);
protected static TextField  lblInput3 = new TextField  (20);

protected  TextArea  lblOutput1 = new TextArea  ();
TextArea  lblOutput2 = new TextArea  ();
TextArea  lblOutput3 = new TextArea  ();


	//  protected static String connStr ;
	  static Connection conn ;
	  protected String query ;
          protected String query2 ;
          protected String query3 ;
	  protected static Statement s ;
	  protected static ResultSet r ;
          protected static ResultSet r2 ;
          protected static ResultSet r3 ;
           
	  public StudentMainWindow(String studentID) throws SQLException
  {
      super("Student Main Window");
      this.studentID = studentID;
      conn = SuperManager.getConnectionManager().getConnection();
    
    jFrame.setSize(width,height);
    
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setLayout(new FlowLayout());
    pane1.setLayout(new GridLayout(0, 4));
    pane1.add(lb1);
    pane1.add(secRefNumInput);
    pane1.add(registerButton);
    pane1.add(viewCoursesButton);
    pane1.add(getSectionsButton);
    pane1.add(lb4);
    
    //Combo(CourseNum);
    
    //pane1.add(c1);
    pane1.setBorder(new javax.swing.border.TitledBorder("Registering courses"));
    
    jFrame.add(pane1);
    

    
    pane2.add(lb2);
    pane2.add(lblInput2);
    pane2.add(dropCourseButton);
    pane2.setBorder(new javax.swing.border.TitledBorder("Dropping courses"));
    
   jFrame.add(pane2);
    
    
   pane3.add(lb3);
    pane3.add(lblInput3);
    pane3.add(viewButton);
    pane3.setBorder(new javax.swing.border.TitledBorder("Viewing grades"));
    
   jFrame.add(pane3);
    
    
   
    jFrame.setVisible(true);
    registerButton.addActionListener(this);
    viewCoursesButton.addActionListener(this);
    registerButton.addActionListener(this);
    getSectionsButton.addActionListener(this);
    dropCourseButton.addActionListener(this);
    viewButton.addActionListener(this);
	setResizable(false);
  }

	@Override
	public void actionPerformed(ActionEvent ev) {
		
        
        try {

             if (ev.getSource() == registerButton){ 
            int ss =Integer.parseInt(secRefNumInput.getText());
            query = "INSERT into ENROLLMENT (REG_DATE, STUDENT_ID, SECTION_REFRENCE_NUMBER) values ('2015-08-10','"+this.studentID +"' ,'"+ss+"') ";           
                   OperationResult result = SuperManager.executeQuery(query);
                   if(result.getResult()){
                       JOptionPane.showMessageDialog(this, result.getMessage());
                   }
                   else{
                       JOptionPane.showMessageDialog(this, result.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                   }
            }    
            else if (ev.getSource() == viewCoursesButton){
           new StudentViewCoursesDialog();
              }
            else if (ev.getSource() == getSectionsButton){
            new SectionsDialog();

            
               }
            
            
            else if (ev.getSource() == dropCourseButton){
            int ss2 =Integer.parseInt(lblInput2.getText());
            query = "DELETE from ENROLLMENT where SECTION_REFRENCE_NUMBER = \'" + ss2+"\'";
            
                   s = conn.createStatement ();
                   r = s.executeQuery(query);
                             
            }
            else if (ev.getSource() == viewButton){
            new StudentsGradesDialog(); 
            }
            
            else{}       
            }

            catch (Exception e){
            }


            }	
	  
}


