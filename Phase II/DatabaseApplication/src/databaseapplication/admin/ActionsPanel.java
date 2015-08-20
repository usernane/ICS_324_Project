/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Ibrahim
 */
public class ActionsPanel extends JPanel{
    private JButton addRecordButton;
    private JButton removeButton;
    private JButton editButton;
    public ActionsPanel(){
        init();
        buildUI();
    }
    private void init(){
        this.removeButton = new JButton("Remove Selecte Record");
        this.addRecordButton = new JButton("Add New Record");
        this.editButton = new JButton("Edit Selected Record");
    }
    private void buildUI(){
        super.setLayout(new GridLayout(3,1));
        super.add(this.addRecordButton);
        super.add(this.removeButton);
        super.add(this.editButton);
    }
    public void setAddRecordAction(ActionListener l){
        this.addRecordButton.addActionListener(l);
    }
    public void setRemoveRecordAction(ActionListener l){
        this.removeButton.addActionListener(l);
    }
    public void addEditRecordAction(ActionListener l){
        this.editButton.addActionListener(l);
    }
}
