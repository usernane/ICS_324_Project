/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin;

import java.util.LinkedList;
import javax.swing.JList;

/**
 *
 * @author Ibrahim
 */
public class TablesList extends JList<Object>{
    
    public TablesList(LinkedList<String> tables){
        super(tables.toArray());
    }
    
}
