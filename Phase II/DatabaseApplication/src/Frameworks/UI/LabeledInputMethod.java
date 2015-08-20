/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks.UI;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ibrahim
 */
public class LabeledInputMethod extends JPanel{
    private JLabel label;
    private InputMethod input;
    
    public LabeledInputMethod(String label){
        this.label = new JLabel(""+label);
 
        super.setLayout(new GridLayout(1,2));
        super.add(this.label);
        
    }
    public void setInputMethod(InputMethod input){
        if(this.input != null){
            super.remove((Component)this.input);
        }
        this.input = input;
        super.add((Component)input);
    }
    public String getValue(){
        return this.input.getValue();
    }

    public void setValue(String string) {
        this.input.setValue(string);
    }
}
