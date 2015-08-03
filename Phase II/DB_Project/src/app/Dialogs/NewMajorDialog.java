/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Dialogs;

import app.OperationResult;
import app.SuperManager;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ibrahim
 */
public class NewMajorDialog extends JDialog{
    private JButton okButton,cancelButton;
    private JTextField nameField, abbreviationField;
    private JLabel nameLabel, abbreviationLabel, departmentIDLabel;
    private JPanel okCancelContainer;
    private JComboBox<String>  departmentIDCombobox;
    
    public NewMajorDialog(){
        this.init();
        this.buildUI();
    }
    private void init(){
        super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.okButton = new JButton("OK");
        this.cancelButton = new JButton("Cancel");
        this.abbreviationField = new JTextField();
        this.nameField = new JTextField();
        this.nameLabel = new JLabel("College Name: ");
        this.abbreviationLabel = new JLabel("Aabbreviation:");
        this.okCancelContainer = new JPanel();
        this.okButton.addActionListener(new EventClass(this));
        this.cancelButton.addActionListener(new EventClass(this));
        this.departmentIDLabel = new JLabel("College:");
        this.departmentIDCombobox = new JComboBox<>();
        
        for(int i = 0 ; i < SuperManager.getCollegesNames().size() ; i++){
            this.departmentIDCombobox.addItem(SuperManager.getDepartmentsNames().get(i));
        }
    }
    
    private void buildUI() {
        super.setSize(500, 150);
        super.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        super.add(this.nameLabel,c);
        
        c.gridx = 1;
        c.gridy = 0;
        super.add(this.nameField,c);
        
        c.gridx = 0;
        c.gridy = 1;
        super.add(this.abbreviationLabel,c);
        
        c.gridx = 1;
        c.gridy = 1;
        super.add(this.abbreviationField,c);
        
        c.gridx = 0;
        c.gridy = 2;
        super.add(this.departmentIDLabel,c);
        c.gridx = 1;
        c.gridy = 2;
        super.add(this.departmentIDCombobox,c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        
        this.okCancelContainer.setLayout(new FlowLayout());
        this.okCancelContainer.add(this.cancelButton);
        this.okCancelContainer.add(this.okButton);
        super.add(this.okCancelContainer,c);
        super.setVisible(true);
        
    }
    public String getDepartmentName(){
        return this.nameField.getText();
        
    }
    public String getAabbreviation(){
        return this.abbreviationField.getText().toUpperCase();
    }
   
    private class EventClass implements ActionListener{
        
        private Component parent;
        public EventClass(Component parent){
            this.parent = parent;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == okButton){
                if(getDepartmentName().length() != 0){
                    if(getAabbreviation().length() != 0){
                        int collegeIndex = departmentIDCombobox.getSelectedIndex();
                        /*OperationResult result = SuperManager.addDepartment(getDepartmentName(), getAabbreviation(),SuperManager.getCollegeIDs().get(collegeIndex));
                        if(result.getResult()){
                            JOptionPane.showMessageDialog(this.parent, result.getMessage(),"Added!" ,JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(this.parent, result.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }*/
                    }
                    else{
                        JOptionPane.showMessageDialog(this.parent, "Error: Aabbreviation field can not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this.parent, "Error: Name field can not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
            else if(e.getSource() == cancelButton){
                setVisible(false);
                dispose();
            }
        }
        
    }
}
