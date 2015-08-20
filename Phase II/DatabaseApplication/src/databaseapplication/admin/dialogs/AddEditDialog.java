/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin.dialogs;

import Frameworks.UI.LabeledInputMethod;
import Frameworks.UI.OkCancelPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ibrahim
 */
public class AddEditDialog extends JDialog{
    private OkCancelPanel okCancelPanel;
    private JPanel inputMethodContainer;
    private LinkedList<LabeledInputMethod> inputMethods;
    public AddEditDialog(JFrame parent){
        super(parent,true);
        init();
        buildUI();
    }
    private void init(){
        this.inputMethods = new LinkedList<>();
        this.okCancelPanel = new OkCancelPanel();
        this.inputMethodContainer = new JPanel();
        this.okCancelPanel.addCancelClickEvent(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }
    private void buildUI() {
        this.inputMethodContainer.setLayout(new GridBagLayout());
        super.add("South",this.okCancelPanel);
        super.add("Center",this.inputMethodContainer);
        super.setSize(300, 200);
        super.setLocationRelativeTo(super.getParent());
    }

    private void inputMethodsChanged(){
        for (LabeledInputMethod inputMethod : this.inputMethods) {
            try {
                this.inputMethodContainer.remove(inputMethod);
            }catch(Exception ex){System.out.println(ex);}
        }
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        
        for (int i = 0 ; i < this.inputMethods.size() ; i++) {
            c.gridy = i;
            this.inputMethodContainer.add(this.inputMethods.get(i),c);
        }
    }
    public void addLabeledInputMethod(LabeledInputMethod input){
        if(input != null){
            this.inputMethods.add(input);
            this.inputMethodsChanged();
        }
    }
    
    public void addOkButtonClickEvent(ActionListener e){
        this.okCancelPanel.addOkClickEvent(e);
    }

    

}
