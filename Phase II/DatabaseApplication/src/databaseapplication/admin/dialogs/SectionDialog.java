/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin.dialogs;

import Frameworks.SectionsGenerator;
import Frameworks.UI.LabeledInputMethod;
import Frameworks.UI.MyComboBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;

/**
 *
 * @author Ibrahim
 */
public class SectionDialog extends AddEditDialog{
    private SectionsGenerator sectionsGenerator;
    private LabeledInputMethod maxSecNum,minSecNum,secInstructor,termNum,college,department, course;
    private MyComboBox tremComboBox, departmentComboBox, collegeComboBox,instructorComboBox, courseComboBox, maxComboBox, minComboBox;
    public SectionDialog(JFrame parent){
        super(parent);
        init();
        buildUI();
    }

    private void init() {
        this.maxSecNum = new LabeledInputMethod("Max Section Number:");
        this.minSecNum = new LabeledInputMethod("Min Section Number:");
        this.secInstructor = new LabeledInputMethod("Instructor:");
        this.termNum = new LabeledInputMethod("Term Number:");
        this.college = new LabeledInputMethod("College:");
        this.department = new LabeledInputMethod("Department:");
        this.course = new LabeledInputMethod("Course:");
    }

    private void buildUI() {
        super.addLabeledInputMethod(this.college);
        super.addLabeledInputMethod(this.department);
        super.addLabeledInputMethod(this.course);
        super.addLabeledInputMethod(this.termNum);
        super.addLabeledInputMethod(this.maxSecNum);
        super.addLabeledInputMethod(this.minSecNum);
        super.addLabeledInputMethod(this.secInstructor);
    }
    private class SelectionCangedEvent implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            
        }
        private void maxNumChanged(){
            
        }
        private void minNumChanged(){
            
        }
        private void termNumberChanged(){
            
        }
        private void collegeChanged(){
            
        }
        private void departmentChanged(){
            
        }
        private void courseChanged(){
            
        }
    }
}
