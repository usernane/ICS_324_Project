
package databaseapplication.student;

import databaseapplication.SuperManager;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
public class SectionsDialog extends JDialog {
JFrame f = new JFrame("The available sections");



    public SectionsDialog() throws SQLException  {
    Connection conn ;
    String query ;
    conn = SuperManager.getConnectionManager().getConnection();      
    String elements[]={"1","2","3","4","5"};  
    JComboBox cb=new JComboBox(elements);   
    cb.setBounds(50, 50,90,20);
    f.add(cb);
      String Rnum = "";
        String Snum = "";
        String Iid = "";
        f.setLayout(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNamess = {"REFRENCE_NUMBER","SECTION_NUMBER", "INSTRUCTOR_ID"};
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
    		
      query = "SELECT * FROM SECTION  where REFRENCE_NUMBER = \'" + StudentMainWindow.secRefNumInput.getText()+"\'";
      StudentMainWindow.s = conn.createStatement ();
      StudentMainWindow.r = StudentMainWindow.s.executeQuery(query);
       
            try{
            int i = 0;
            
            while (StudentMainWindow.r.next()) {
                Rnum = StudentMainWindow.r.getString("REFRENCE_NUMBER");
                Snum = StudentMainWindow.r.getString("SECTION_NUMBER");
                Iid = StudentMainWindow.r.getString("INSTRUCTOR_ID");
                model.addRow(new Object[]{Rnum, Snum, Iid});
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
    f.add(cb);  
    f.add(scroll);  
    f.setSize(500, 500);
    f.setResizable(false);
    f.setVisible(true);
    
     

                
    

    }


    

    }

   



