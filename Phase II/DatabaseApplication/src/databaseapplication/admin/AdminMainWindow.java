/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin;

import databaseapplication.SuperManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ibrahim
 */
public class AdminMainWindow extends JFrame{
    
    private TablesList tablesList;
    private AppDatabaseEditTable dbTable;
    private ActionsPanel panel;
    private QueryField queryField;
    
    public AdminMainWindow(){
        super("Admen");
        init();
        buildUI();
        
    }
    private void init(){
        this.tablesList = new TablesList(SuperManager.getTables());
        this.dbTable = new AppDatabaseEditTable(SuperManager.getConnectionManager());
        this.panel = new ActionsPanel();
        this.queryField = new QueryField();
        this.queryField.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String query = queryField.getQuery();
                dbTable.updateData(SuperManager.getConnectionManager().getResultSetAsTable(query));
            }
        });
        this.tablesList.addListSelectionListener(new ListSelectionListener(){
        
            @Override
            public void valueChanged(ListSelectionEvent e) {
                dbTable.selectData((String)tablesList.getSelectedValue());
            }
        });
    }
    private void buildUI(){
        super.setDefaultCloseOperation(3);
        super.setLayout(new BorderLayout());
        super.add("West",this.tablesList);
        super.add("Center",this.dbTable);
        super.add("East",this.panel);
        super.add("South",this.queryField);
        super.setSize(500,500);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        
    }
}
