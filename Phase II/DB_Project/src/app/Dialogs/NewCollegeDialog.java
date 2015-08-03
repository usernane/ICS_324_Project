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
public class NewCollegeDialog extends JDialog{
    private JButton okButton,cancelButton;
    private JTextField nameField, abbreviationField;
    private JLabel nameLabel, abbreviationLabel, uniqueIDLabel;
    private JPanel okCancelContainer;
    private JComboBox<String> collegeIDCompoBox;
    
    public NewCollegeDialog(){
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
        this.collegeIDCompoBox = new JComboBox<>();
        this.uniqueIDLabel = new JLabel("College  Identifier:");
        char c = 'A';
        for(int i = 'A'  ; i <= 'Z' ; i++){
            this.collegeIDCompoBox.addItem(""+c);
            c++;
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
        super.add(this.uniqueIDLabel,c);
        c.gridx = 1;
        c.gridy = 2;
        super.add(this.collegeIDCompoBox,c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        this.okCancelContainer.setLayout(new FlowLayout());
        this.okCancelContainer.add(this.cancelButton);
        this.okCancelContainer.add(this.okButton);
        super.add(this.okCancelContainer,c);
        super.setVisible(true);
        
    }
    public String getCollegeName(){
        return this.nameField.getText();
        
    }
    public String getAabbreviation(){
        return this.abbreviationField.getText().toUpperCase();
    }
    public String getUniqueID(){
        return (String)this.collegeIDCompoBox.getSelectedItem();
    }
    private class EventClass implements ActionListener{
        
        private Component parent;
        public EventClass(Component parent){
            this.parent = parent;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == okButton){
                if(getCollegeName().length() != 0){
                    if(getAabbreviation().length() != 0){
                        OperationResult result = SuperManager.addCollege(getUniqueID(), getCollegeName(), getAabbreviation());
                        if(result.getResult()){
                            JOptionPane.showMessageDialog(this.parent, result.getMessage(),"Added!" ,JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(this.parent, result.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
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
