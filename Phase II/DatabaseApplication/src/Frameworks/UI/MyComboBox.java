/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks.UI;

import javax.swing.JComboBox;

/**
 *
 * @author Ibrahim
 */
public class MyComboBox extends JComboBox<String> implements InputMethod{

    @Override
    public String getValue() {
        return (String)super.getSelectedItem();
    }

    @Override
    public void setValue(String value) {
        super.setSelectedItem(value);
    }
    
}
