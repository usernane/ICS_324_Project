/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication;

import Frameworks.ConnectionManager;
import Frameworks.UI.DataTable;
import Frameworks.UI.DataTableModel;
import Frameworks.table.RowData;
import Frameworks.table.TableData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Ibrahim
 */
public class IbrahimAliTestingFile {
    public static void main(String [] a){
        //howToUseTable();
        testAddData();
    }
    
    public static void testAddData(){
        //create a connection
        final ConnectionManager m = new ConnectionManager("jdbc:derby://localhost:1527/ProjectDB","ibrahim","ibrahim");
        //open the connection
        m.openConnection();
        //frame to add the table to
        JFrame tmp = new JFrame("Hi");
        tmp.setDefaultCloseOperation(3);
        
        //this is our table
        final DataTable d = new DataTable();
        
        tmp.add(d);
        tmp.setSize(500, 500);
        tmp.setLocationRelativeTo(null);
        
        
        //how to use queries to update the table
        //d.updateData(m.getResultSetAsTable("select * from student"));

        JButton button = new JButton("Run");
        final JTextField queryField = new JTextField();
        tmp.add("North",queryField);
        tmp.add("South",button);
        tmp.setVisible(true);
        button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                RowData data = new RowData(5);
                data.set(true, 0);
                data.set("Nice", 1);
                data.set("Work", 2);
                data.set(new java.sql.Date(2015,12,12), 3);
                data.set(":P", 4);
                d.addRow(data);
                System.out.println(""+d.getValueAt(0,0));
            }
        });
        d.setValueAt("TEST", 0, 3);
    }
    public static void howToUseTable(){
        //create a connection
        final ConnectionManager m = new ConnectionManager("jdbc:derby://localhost:1527/ProjectDB","ibrahim","ibrahim");
        //open the connection
        m.openConnection();
        //frame to add the table to
        JFrame tmp = new JFrame("Hi");
        tmp.setDefaultCloseOperation(3);
        
        //this is our table
        final DataTable d = new DataTable();
        
        tmp.add(d);
        tmp.setSize(500, 500);
        tmp.setLocationRelativeTo(null);
        tmp.setVisible(true);
        
        //how to use queries to update the table
        //d.updateData(m.getResultSetAsTable("select * from student"));

        JButton button = new JButton("Run");
        final JTextField queryField = new JTextField();
        tmp.add("North",queryField);
        tmp.add("South",button);
        button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //this is how to use queries to update the table
                TableData data = m.getResultSetAsTable(queryField.getText());
                d.updateData(data);
                System.out.println(""+data.getColumnName(0));
            }
        });
        d.setValueAt("TEST", 0, 3);
    }
}
