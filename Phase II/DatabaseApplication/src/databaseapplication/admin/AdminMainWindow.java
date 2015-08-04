/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin;

import databaseapplication.SuperManager;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Ibrahim
 */
public class AdminMainWindow extends JFrame{
    
    private TablesList tablesList;
    
    public AdminMainWindow(){
        super("Admen");
        init();
        buildUI();
    }
    private void init(){
        this.tablesList = new TablesList(SuperManager.getTables());
    }
    private void buildUI(){
        super.setLayout(new BorderLayout());
        super.add("Left",this.tablesList);
        
        super.setSize(500,500);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }
}
