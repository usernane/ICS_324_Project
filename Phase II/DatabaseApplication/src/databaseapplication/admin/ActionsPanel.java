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
    public ActionsPanel(){
        init();
        buildUI();
    }
    private void init(){
        this.removeButton = new JButton("Remove");
        this.addRecordButton = new JButton("Add");
    }
    private void buildUI(){
        super.setLayout(new GridLayout(2,1));
        super.add(this.addRecordButton);
        super.add(this.removeButton);
    }
    public void setAddRecordAction(ActionListener l){
        
    }
    public void setRemoveRecordAction(ActionListener l){
        
    }
}
