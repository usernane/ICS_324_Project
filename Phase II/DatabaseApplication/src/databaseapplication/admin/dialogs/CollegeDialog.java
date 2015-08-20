/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin.dialogs;

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
public class CollegeDialog extends AddEditDialog{
    private LabeledInputMethod collegeID,collegeName;
    public CollegeDialog(JFrame parent) {
        super(parent);
        init();
        buildUI();
    }
    public CollegeDialog(JFrame parent, RowData toEdit){
        this(parent);
        if(toEdit != null){
            this.collegeID.setValue((String)toEdit.get(0));
            this.collegeName.setValue((String)toEdit.get(1));
        }
    }
    private void init(){
        this.collegeID = new LabeledInputMethod("College ID:");
        this.collegeName = new LabeledInputMethod("Name:");
        
        this.collegeID.setInputMethod(new MyTextField());
        this.collegeName.setInputMethod(new MyTextField());
    }

    
    private void buildUI(){
        super.addLabeledInputMethod(this.collegeName);
        super.addLabeledInputMethod(collegeID);
    }
    public String getCollegeID(){
        return this.collegeID.getValue();
    }
    
    public String getCollegeName(){
        return this.collegeName.getValue();
    }
}
