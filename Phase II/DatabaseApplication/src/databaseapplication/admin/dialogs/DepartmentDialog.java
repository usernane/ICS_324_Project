/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin.dialogs;

import databaseapplication.admin.dialogs.AddEditDialog;
import Frameworks.UI.LabeledInputMethod;
import Frameworks.UI.MyComboBox;
import Frameworks.UI.MyTextField;
import Frameworks.table.RowData;
import Frameworks.table.TableData;
import databaseapplication.SuperManager;
import javax.swing.JFrame;

/**
 *
 * @author Ibrahim
 */
public class DepartmentDialog extends AddEditDialog{
     private LabeledInputMethod depID,deptName, collegeID;
    public DepartmentDialog(JFrame parent) {
        super(parent);
        init();
        buildUI();
    }
    public DepartmentDialog(JFrame parent, RowData toEdit){
        this(parent);
        if(toEdit != null){
            this.depID.setValue((String)toEdit.get(1));
            this.deptName.setValue((String)toEdit.get(0));
            this.collegeID.setValue((String)toEdit.get(2));
        }
    }
    private void setForignKeyInputMethod(){
        TableData keys = SuperManager.getConnectionManager().getForeignKeys();
        for(int i = 0 ; i < keys.rows() ; i++){
            String tableName = (String)keys.get(i, 0);
            if(tableName.compareToIgnoreCase("department") == 0){
                String refTable = (String)keys.get(i, 2);
                String refColumn = (String)keys.get(i, 3);
                TableData keysValues = SuperManager.getConnectionManager().getResultSetAsTable("select "+refColumn+" from "+refTable);
                MyComboBox comboBox = new MyComboBox();
                for(int j = 0 ; j < keysValues.rows() ; j++){
                    comboBox.addItem((String)keysValues.get(j, 0));
                }
                this.collegeID.setInputMethod(comboBox);
            }
        }
    }
    private void init(){
        this.depID = new LabeledInputMethod("Department ID:");
        this.deptName = new LabeledInputMethod("Name:");
        this.collegeID = new LabeledInputMethod("College: ");
        this.depID.setInputMethod(new MyTextField());
        this.deptName.setInputMethod(new MyTextField());

        setForignKeyInputMethod();
    }

    
    private void buildUI(){
        
        super.addLabeledInputMethod(this.deptName);
        super.addLabeledInputMethod(depID);
        super.addLabeledInputMethod(this.collegeID);

    }
    public String getDepartmentID(){
        return this.depID.getValue();
    }
    
    public String getDepartmentName(){
        return this.deptName.getValue();
    }

    public String getCollegeID() {
        return this.collegeID.getValue();
    }

}
