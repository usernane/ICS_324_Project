/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin.dialogs;

import Frameworks.UI.LabeledInputMethod;
import Frameworks.UI.MyComboBox;
import Frameworks.UI.MyTextField;
import Frameworks.table.TableData;
import databaseapplication.SuperManager;
import javax.swing.JFrame;

/**
 *
 * @author Ibrahim
 */
public class MajorDialog extends AddEditDialog{
    private LabeledInputMethod majorCode, majorName, department;
    
    public MajorDialog(JFrame parent){
        super(parent);
        init();
        buildUI();
    }

    private void init() {
        this.majorCode = new LabeledInputMethod("Major Code:");
        this.majorName = new LabeledInputMethod("Major Name:");
        this.department = new LabeledInputMethod("Department:");
        
        this.majorName.setInputMethod(new MyTextField());
        this.majorCode.setInputMethod(new MyTextField());
        setForignKeyInputMethod();
    }

    private void buildUI() {
        super.addLabeledInputMethod(this.majorName);
        super.addLabeledInputMethod(this.majorCode);
        super.addLabeledInputMethod(this.department);
    }
    private void setForignKeyInputMethod(){
        TableData keys = SuperManager.getConnectionManager().getForeignKeys();
        for(int i = 0 ; i < keys.rows() ; i++){
            String tableName = (String)keys.get(i, 0);
            if(tableName.compareToIgnoreCase("major") == 0){
                String refTable = (String)keys.get(i, 2);
                String refColumn = (String)keys.get(i, 3);
                TableData keysValues = SuperManager.getConnectionManager().getResultSetAsTable("select "+refColumn+" from "+refTable);
                MyComboBox comboBox = new MyComboBox();
                for(int j = 0 ; j < keysValues.rows() ; j++){
                    comboBox.addItem((String)keysValues.get(j, 0));
                }
                this.department.setInputMethod(comboBox);
            }
        }
    }

    public String getMajorName() {
        return this.majorName.getValue();
    }

    public String getMajorID() {
        return this.majorCode.getValue();
    }

    public String getDepartment() {
        return this.department.getValue();
    }
}
