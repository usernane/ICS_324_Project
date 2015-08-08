/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Ibrahim
 */
public class QueryField extends JPanel{
    private JButton executButton;
    private JTextArea queryField;
    private JLabel label;
    
    public QueryField(){
        init();
        buildUI();
        
    }
    private void init(){
        this.executButton = new JButton("Execute Query");
        this.label = new JLabel("<html><font size = 4>Custom Query</font></html>");
        this.queryField = new JTextArea();
    }
    private void buildUI(){
        this.queryField.setSize(300, 300);
        super.setLayout(new BorderLayout());
        super.add("North",this.label);
        super.add("Center",this.queryField);
        super.add("South",this.executButton);
        super.setSize(400, 400);
    }
    public void addActionListener(ActionListener l){
        if(l != null){
            this.executButton.addActionListener(l);
        }
    }
    public String getQuery(){
        return this.queryField.getText();
    }
}
