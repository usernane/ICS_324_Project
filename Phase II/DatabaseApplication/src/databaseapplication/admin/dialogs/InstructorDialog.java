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

/**
 *
 * @author Ibrahim
 */
public class InstructorDialog extends AddEditDialog{
    private LabeledInputMethod instructortID,instFirstName,instLastName;
    public InstructorDialog(JFrame parent) {
        super(parent);
        init();
        buildUI();
    }
    public InstructorDialog(JFrame parent, RowData toEdit){
        this(parent);
        if(toEdit != null){
            this.instructortID.setValue((String)toEdit.get(0));
            this.instFirstName.setValue((String)toEdit.get(1));
            this.instLastName.setValue((String)toEdit.get(2));
        }
    }
    private void init(){
        this.instructortID = new LabeledInputMethod("Instructor ID:");
        this.instFirstName = new LabeledInputMethod("First Name:");
        this.instLastName = new LabeledInputMethod("Last Name");
        
        this.instructortID.setInputMethod(new MyTextField());
        this.instFirstName.setInputMethod(new MyTextField());
        this.instLastName.setInputMethod(new MyTextField());

    }
    
    
    private void buildUI(){
        super.addLabeledInputMethod(instructortID);
        super.addLabeledInputMethod(this.instFirstName);
        super.addLabeledInputMethod(this.instLastName);

    }
    public String getID(){
        return this.instructortID.getValue();
    }
    public String getFirstName(){
        return this.instFirstName.getValue();
    }
    public String getLastName(){
        return this.instLastName.getValue();
    }
}
