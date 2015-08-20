/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin.dialogs;

import Frameworks.UI.MyComboBox;
import Frameworks.UI.InputMethod;
import Frameworks.UI.LabeledInputMethod;
import Frameworks.UI.MyTextField;
import Frameworks.table.RowData;
import Frameworks.table.TableData;
import databaseapplication.SuperManager;
import java.awt.event.InputMethodListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ibrahim
 */
public class StudentDialog extends AddEditDialog{
    private LabeledInputMethod studentID,studentMajor,studentFirstName,studentLastName;
    private boolean isRowNull;
    public StudentDialog(JFrame parent) {
        super(parent);
        init();
        buildUI();
    }
    public StudentDialog(JFrame parent, RowData toEdit){
        this(parent);

        if(toEdit != null){
            this.isRowNull = false;
            this.studentID.setValue((String)toEdit.get(0));
            this.studentFirstName.setValue((String)toEdit.get(1));
            this.studentLastName.setValue((String)toEdit.get(2));
            this.studentMajor.setValue((String)toEdit.get(3));
        }
        else{
            JOptionPane.showMessageDialog(parent, "No selectet row!", "Info", JOptionPane.INFORMATION_MESSAGE);
            this.isRowNull = true;
        }
    }
    public boolean isRowNotNull(){
        return !this.isRowNull;
    }
    private void init(){
        this.studentID = new LabeledInputMethod("Student ID:");
        this.studentFirstName = new LabeledInputMethod("First Name:");
        this.studentLastName = new LabeledInputMethod("Last Name");
        this.studentMajor = new LabeledInputMethod("Major");
        
        this.studentID.setInputMethod(new MyTextField());
        this.studentFirstName.setInputMethod(new MyTextField());
        this.studentLastName.setInputMethod(new MyTextField());
        setForignKeyInputMethod();
    }
    
    private void setForignKeyInputMethod(){
        TableData keys = SuperManager.getConnectionManager().getForeignKeys();
        for(int i = 0 ; i < keys.rows() ; i++){
            String tableName = (String)keys.get(i, 0);
            if(tableName.compareToIgnoreCase("student") == 0){
                String column = (String)keys.get(i, 1);
                String refTable = (String)keys.get(i, 2);
                String refColumn = (String)keys.get(i, 3);
                TableData keysValues = SuperManager.getConnectionManager().getResultSetAsTable("select "+refColumn+" from "+refTable);
                MyComboBox comboBox = new MyComboBox();
                for(int j = 0 ; j < keysValues.rows() ; j++){
                    comboBox.addItem((String)keysValues.get(j, 0));
                }
                this.studentMajor.setInputMethod(comboBox);
            }
        }
    }
    
    private void buildUI(){
        super.addLabeledInputMethod(studentID);
        super.addLabeledInputMethod(this.studentFirstName);
        super.addLabeledInputMethod(this.studentLastName);
        super.addLabeledInputMethod(this.studentMajor);
    }
    public String getID(){
        return this.studentID.getValue();
    }
    public String getFirstName(){
        return this.studentFirstName.getValue();
    }
    public String getLastName(){
        return this.studentLastName.getValue();
    }
    public String getMajor(){
        return this.studentMajor.getValue();
    }
}
