package databaseapplication.student;






import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aqeil54
 */
public class StudentViewCoursesDialog extends JDialog {
JFrame f = new JFrame("The available courses");
    public StudentViewCoursesDialog() throws SQLException  {
    

    Connection conn ;
    String query ;
    conn = StudentMainWindow.conn;//DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","201236760");
  
    
    
    f.setLayout(new FlowLayout());

 

        String courseNum = "";
        String title = "";
        String level = "";
        String MagCod = "";

   
        f.setLayout(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNamess = {"Course Number", "Title", "Level", "MAJOR_CODE"};
        model.setColumnIdentifiers(columnNamess);
        JTable table = new JTable(model);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    		
		query = "SELECT * FROM COURSE";
		  StudentMainWindow.s = conn.createStatement ();
		  StudentMainWindow.r = StudentMainWindow.s.executeQuery(query);
       
            try{
            int i = 0;
            
            while (StudentMainWindow.r.next()) {
                courseNum = StudentMainWindow.r.getString("number");
                title = StudentMainWindow.r.getString("TITLE");
                level = StudentMainWindow.r.getString("level");
                MagCod = StudentMainWindow.r.getString("MAJOR_CODE");
                model.addRow(new Object[]{courseNum, title, level, MagCod});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
       
    f.add(scroll);
    f.setSize(400, 300);
    f.setResizable(false);
    f.setVisible(true);
    


  }

    
}

   



