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
public class AddCourseDialog extends AddEditDialog{
    private LabeledInputMethod number, title, level, majorCode;
    private int prevCourseNum;
    public AddCourseDialog(JFrame parent, int prevCourseNum) {
        super(parent);
        this.prevCourseNum = prevCourseNum;
        init();
        buildUI();
    }
    private void init(){
        this.number = new LabeledInputMethod("Course Number:");
        this.title = new LabeledInputMethod("Course Title:");
        this.level = new LabeledInputMethod("Level:");
        this.majorCode = new LabeledInputMethod("Major:");
        
        MyTextField field = new MyTextField();
        field.setEditable(false);
        field.setValue((this.prevCourseNum+1)+"");
        this.number.setInputMethod(field);
        this.title.setInputMethod(new MyTextField());
        MyComboBox box = new MyComboBox();
        for(int i = 0 ; i < 1000 ; i++){
            box.addItem(""+i);
        }
        this.level.setInputMethod(box);
        setForignKeyInputMethod();
    }
    private void buildUI(){
        super.addLabeledInputMethod(this.number);
        super.addLabeledInputMethod(this.title);
        super.addLabeledInputMethod(this.level);
        super.addLabeledInputMethod(this.majorCode);
    }
    private void setForignKeyInputMethod(){
        TableData keys = SuperManager.getConnectionManager().getForeignKeys();
        for(int i = 0 ; i < keys.rows() ; i++){
            String tableName = (String)keys.get(i, 0);
            if(tableName.compareToIgnoreCase("course") == 0){
                String refTable = (String)keys.get(i, 2);
                String refColumn = (String)keys.get(i, 3);
                TableData keysValues = SuperManager.getConnectionManager().getResultSetAsTable("select "+refColumn+" from "+refTable);
                MyComboBox comboBox = new MyComboBox();
                for(int j = 0 ; j < keysValues.rows() ; j++){
                    comboBox.addItem((String)keysValues.get(j, 0));
                }
                this.majorCode.setInputMethod(comboBox);
            }
        }
    }
    public String getNumber(){
        return this.number.getValue();
    }
    public String getCourseTitle(){
        return this.title.getValue();
    }
    public String getCourseLevel(){
        return this.level.getValue();
    }
    public String getMajor(){
        return this.majorCode.getValue();
    }
}
