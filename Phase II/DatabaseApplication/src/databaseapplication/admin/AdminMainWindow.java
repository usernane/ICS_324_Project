/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin;

import databaseapplication.admin.dialogs.DepartmentDialog;
import databaseapplication.admin.dialogs.StudentDialog;
import databaseapplication.admin.dialogs.AddCourseDialog;
import databaseapplication.admin.dialogs.InstructorDialog;
import Frameworks.OperationResult;
import Frameworks.table.RowData;
import databaseapplication.SuperManager;
import databaseapplication.admin.dialogs.CollegeDialog;
import databaseapplication.admin.dialogs.MajorDialog;
import databaseapplication.admin.dialogs.SectionDialog;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ibrahim
 */
public class AdminMainWindow extends JFrame{
    
    private TablesList tablesList;
    private AppDatabaseEditTable dbTable;
    private ActionsPanel panel;
    private QueryField queryField;
    
    public AdminMainWindow(){
        super("Admen");
        init();
        buildUI();
        
    }
    private void init(){
        this.tablesList = new TablesList(SuperManager.getTables());
        this.dbTable = new AppDatabaseEditTable(SuperManager.getConnectionManager());
        this.panel = new ActionsPanel();
        this.queryField = new QueryField();
        this.queryField.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String query = queryField.getQuery();
                dbTable.updateData(SuperManager.getConnectionManager().getResultSetAsTable(query));
            }
        });
        this.tablesList.addListSelectionListener(new ListSelectionListener(){
        
            @Override
            public void valueChanged(ListSelectionEvent e) {
                dbTable.selectData((String)tablesList.getSelectedValue());
            }
        });
        this.panel.setRemoveRecordAction(new RemoveAction(this));
        this.panel.setAddRecordAction(new AddAction(this));
        this.panel.addEditRecordAction(new ModifyAction(this) );
    }
    private void buildUI(){
        super.setDefaultCloseOperation(3);
        super.setLayout(new BorderLayout());
        super.add("West",this.tablesList);
        super.add("Center",this.dbTable);
        super.add("East",this.panel);
        super.add("South",this.queryField);
        super.setSize(500,500);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        
    }
    private class ModifyAction implements ActionListener{
        private JFrame Parent;

        private ModifyAction(JFrame aThis) {
            this.Parent = aThis;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(dbTable.getSlectedTableName() == null){
                JOptionPane.showMessageDialog(Parent, "No selected table!", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(dbTable.getSlectedTableName().compareToIgnoreCase("student") == 0){
                showStudentDialog();
            }
            else if(dbTable.getSlectedTableName().compareToIgnoreCase("instructor") == 0){
                showInstructorDialog();
            }
            else if(dbTable.getSlectedTableName().compareToIgnoreCase("college") == 0){
                showAddCollegeDialog();
            }
            else if(dbTable.getSlectedTableName().compareToIgnoreCase("course") == 0){
                showAddCourseDialog();
            }
            else if(dbTable.getSlectedTableName().compareToIgnoreCase("department") == 0){
                showDepartmentDialog();
            }
            else if(dbTable.getSlectedTableName().compareToIgnoreCase("major") == 0){
                showAddMajorDialog();
            }
            else if(dbTable.getSlectedTableName().compareToIgnoreCase("section") == 0){
                showAddSectionDialog();
            }
            else{
                JOptionPane.showMessageDialog(Parent, "You are not allowed to modify this table!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        private void showStudentDialog() {
            final StudentDialog dialog = new StudentDialog(this.Parent,dbTable.getSelectedRow());
            if(!dialog.isRowNotNull()){
                return;
            }
            dialog.addOkButtonClickEvent(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String studentId = dialog.getID(); 
                    String fName = dialog.getFirstName();
                    String lName = dialog.getLastName();
                    String major = dialog.getMajor();
                    OperationResult result = SuperManager.update("student", "id = "+studentId+", first_name = '"+fName+"', last_name = '"+lName+"', major_code = '"+major+"'"," id = "+dbTable.getSelectedRow().get(0));
                    if(result.getResult()){
                        JOptionPane.showMessageDialog(Parent, "Record was modifyed!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        dialog.setVisible(false);
                        dialog.dispose();
                        dbTable.selectData("student");
                    }
                    else{
                        JOptionPane.showMessageDialog(Parent, result.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            dialog.setVisible(true); 
        }

        private void showInstructorDialog() {
            
        }

        private void showAddCollegeDialog() {
            
        }

        private void showAddCourseDialog() {
            
        }

        private void showDepartmentDialog() {
            
        }

        private void showAddMajorDialog() {
            
        }

        private void showAddSectionDialog() {
            
        }
        
    }
    private class AddAction implements ActionListener{
        private JFrame Parent;

        public AddAction(JFrame Parent){
            this.Parent = Parent;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(dbTable.getSlectedTableName() == null){
                JOptionPane.showMessageDialog(Parent, "No selected table!", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(dbTable.getSlectedTableName().compareToIgnoreCase("student") == 0){
                showStudentDialog();
            }
            else if(dbTable.getSlectedTableName().compareToIgnoreCase("instructor") == 0){
                showInstructorDialog();
            }
            else if(dbTable.getSlectedTableName().compareToIgnoreCase("college") == 0){
                showAddCollegeDialog();
            }
            else if(dbTable.getSlectedTableName().compareToIgnoreCase("course") == 0){
                showAddCourseDialog();
            }
            else if(dbTable.getSlectedTableName().compareToIgnoreCase("department") == 0){
                showDepartmentDialog();
            }
            else if(dbTable.getSlectedTableName().compareToIgnoreCase("major") == 0){
                showAddMajorDialog();
            }
            else if(dbTable.getSlectedTableName().compareToIgnoreCase("section") == 0){
                showAddSectionDialog();
            }
            else{
                JOptionPane.showMessageDialog(Parent, "You are not allowed to modify this table!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        private void showAddSectionDialog(){
            SectionDialog dialog = new SectionDialog(this.Parent);
            
            dialog.setVisible(true);
        }
        private void showAddMajorDialog(){
            final MajorDialog dialog = new MajorDialog(this.Parent);
            dialog.addOkButtonClickEvent(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                    String majorName = dialog.getMajorName(); 
                    String majorId = dialog.getMajorID();
                    String department = dialog.getDepartment();
                    OperationResult result = SuperManager.insert("major", "'"+majorName+"','"+majorId+"','"+department+"'");
                    if(result.getResult()){
                        JOptionPane.showMessageDialog(Parent, "New record was inserted!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        dialog.setVisible(false);
                        dialog.dispose();
                        dbTable.selectData("major");
                    }
                    else{
                        JOptionPane.showMessageDialog(Parent, result.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
                    }
                }
                });
                dialog.setVisible(true);
        }
        private void showAddCollegeDialog(){
            final CollegeDialog dialog = new CollegeDialog(this.Parent);
            dialog.addOkButtonClickEvent(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                    String collName = dialog.getCollegeName(); 
                    String collId = dialog.getCollegeID();
                    OperationResult result = SuperManager.insert("college", "'"+collName+"','"+collId+"'");
                    if(result.getResult()){
                        JOptionPane.showMessageDialog(Parent, "New record was inserted!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        dialog.setVisible(false);
                        dialog.dispose();
                        dbTable.selectData("college");
                    }
                    else{
                        JOptionPane.showMessageDialog(Parent, result.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
                    }
                }
                });
                dialog.setVisible(true);
        }
        private void showDepartmentDialog(){
            final DepartmentDialog dialog = new DepartmentDialog(this.Parent);
            dialog.addOkButtonClickEvent(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                    String depName = dialog.getDepartmentName(); 
                    String depId = dialog.getDepartmentID();
                    String collegeId = dialog.getCollegeID();
                    OperationResult result = SuperManager.insert("department", "'"+depName+"','"+depId+"','"+collegeId+"'");
                    if(result.getResult()){
                        JOptionPane.showMessageDialog(Parent, "New record was inserted!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        dialog.setVisible(false);
                        dialog.dispose();
                        dbTable.selectData("department");
                    }
                    else{
                        JOptionPane.showMessageDialog(Parent, result.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
                    }
                }
                });
                dialog.setVisible(true);
        }
        private void showAddCourseDialog(){
            int lastRowNum = dbTable.rows();
                final AddCourseDialog dialog = new AddCourseDialog(this.Parent,lastRowNum);
                dialog.addOkButtonClickEvent(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                    String courseTitle = dialog.getCourseTitle(); 
                    String courseNum = dialog.getNumber();
                    String courseLevel = dialog.getCourseLevel();
                    String major = dialog.getMajor();
                    OperationResult result = SuperManager.insert("course", ""+courseNum+",'"+courseTitle+"',"+courseLevel+",'"+major+"'");
                    if(result.getResult()){
                        JOptionPane.showMessageDialog(Parent, "New record was inserted!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        dialog.setVisible(false);
                        dialog.dispose();
                        dbTable.selectData("course");
                    }
                    else{
                        JOptionPane.showMessageDialog(Parent, result.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
                    }
                }
                });
                dialog.setVisible(true);
        }
        private void showInstructorDialog(){
            final InstructorDialog dialog = new InstructorDialog(this.Parent);
            dialog.addOkButtonClickEvent(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String instId = dialog.getID(); 
                    String fName = dialog.getFirstName();
                    String lName = dialog.getLastName();
                    OperationResult result = SuperManager.insert("instructor", ""+instId+",'"+fName+"','"+lName+"'");
                    if(result.getResult()){
                        JOptionPane.showMessageDialog(Parent, "New record was inserted!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        dialog.setVisible(false);
                        dialog.dispose();
                        dbTable.selectData("instructor");
                    }
                    else{
                        JOptionPane.showMessageDialog(Parent, result.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            dialog.setVisible(true); 
        }
        private void showStudentDialog(){
            final StudentDialog dialog = new StudentDialog(this.Parent);
            dialog.addOkButtonClickEvent(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String studentId = dialog.getID(); 
                    String fName = dialog.getFirstName();
                    String lName = dialog.getLastName();
                    String major = dialog.getMajor();
                    OperationResult result = SuperManager.insert("student", ""+studentId+",'"+fName+"','"+lName+"','"+major+"'");
                    if(result.getResult()){
                        JOptionPane.showMessageDialog(Parent, "New record was inserted!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        dialog.setVisible(false);
                        dialog.dispose();
                        dbTable.selectData("student");
                    }
                    else{
                        JOptionPane.showMessageDialog(Parent, result.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            dialog.setVisible(true); 
        }
    }
    private class RemoveAction implements ActionListener{
        
        private JFrame Parent;
        public RemoveAction(JFrame Parent){
            this.Parent = Parent;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedTableName = (String)tablesList.getSelectedValue();
                RowData selectedRow = dbTable.getSelectedRow();
                if(selectedRow == null){
                    JOptionPane.showMessageDialog(Parent, "No row was selected!", "Delete", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int retVal = JOptionPane.showConfirmDialog(this.Parent, "Are you sure that you would like to remove the record?", "Remove Record", JOptionPane.YES_NO_OPTION);
                if(retVal == JOptionPane.YES_OPTION){
                    String [] tableFields = dbTable.getColumnsNames();
                    String deleteCondition = "";
                    for(int i = 0 ; i < tableFields.length ; i++){
                        if(i != tableFields.length - 1){
                            String selectedCellData = (String)selectedRow.get(i);
                            try{
                                Integer.parseInt(selectedCellData);
                                deleteCondition+=""+tableFields[i]+" = "+selectedCellData+" and ";
                            }
                            catch(Exception ex){
                                deleteCondition+=""+tableFields[i]+" = '"+selectedCellData+"' and ";
                            }
                            
                        }
                        else{
                            String selectedCellData = (String)selectedRow.get(i);
                            try{
                                Integer.parseInt(selectedCellData);
                                deleteCondition+=""+tableFields[i]+" = "+selectedCellData+"";
                            }
                            catch(Exception ex){
                                deleteCondition+=""+tableFields[i]+" = '"+selectedCellData+"' ";
                            }
                        }
                        
                        System.out.println(deleteCondition);
                    }
                    OperationResult result = SuperManager.delete(selectedTableName,deleteCondition);
                    if(result.getResult()){
                        JOptionPane.showMessageDialog(Parent, result.getMessage(), "Delete", JOptionPane.INFORMATION_MESSAGE);
                        dbTable.selectData(selectedTableName);
                    }
                    else{
                        JOptionPane.showMessageDialog(Parent, result.getMessage(), "Delete", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
        }
        
    }
}
