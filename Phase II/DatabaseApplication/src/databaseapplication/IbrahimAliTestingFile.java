/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication;

import Frameworks.ConnectionManager;
import Frameworks.UI.DataTable;
import Frameworks.UI.DataTableModel;
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
        
    }
    
    
    public static void howToUseTable(){
        //create a connection
        final ConnectionManager m = new ConnectionManager("jdbc:derby://localhost:1527/ProjectDB","ibrahim","ibrahim");
        
        m.openConnection();
        JFrame tmp = new JFrame("Hi");
        tmp.setDefaultCloseOperation(3);
        final DataTable d = new DataTable();
        tmp.add(d);
        tmp.setSize(500, 500);
        tmp.setLocationRelativeTo(null);
        tmp.setVisible(true);
        d.updateData(m.getResultSetAsTable("select * from student"));
        d.addRow();
        DataTableModel model = d.getModel();
        System.out.println(model.getColumnName(0));
        JButton button = new JButton("Run");
        final JTextField queryField = new JTextField();
        tmp.add("North",queryField);
        tmp.add("South",button);
        button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                d.updateData(m.getResultSetAsTable(queryField.getText()));
            }
        });
        d.setValueAt("TEST", 0, 3);
    }
}
