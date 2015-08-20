/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks.UI;

import javax.swing.JTextField;

/**
 *
 * @author Ibrahim
 */
public class MyTextField extends JTextField implements InputMethod{

    @Override
    public String getValue() {
        return super.getText();
    }

    @Override
    public void setValue(String value) {
        super.setText(value);
    }
    
}
