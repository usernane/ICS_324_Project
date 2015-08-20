/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.instructor;

import databaseapplication.CommonMethods;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ibra5_000
 */
class InstructorDeleteGradingCutoffDialog {

    public InstructorDeleteGradingCutoffDialog() {
        CommonMethods cm = PackageMainInterface.cm;
        Connection con = PackageMainInterface.con;
	
        String section = cm.getFrom(cm, con
                ,"select REFRENCE_NUMBER from section where INSTRUCTOR_ID = "+PackageMainInterface.instructorID
                ,"there is no section taught by instructor "+PackageMainInterface.instructorID
                ,"select a section");
        if(section==null)
            return;
        
        String cutoff = cm.getFrom(cm, con
                ,"select concat(concat(letter_grade,','),value) from grading_cutoffs where sec_ref_num = "+section
                ,"there is no cutoffs in section "+section
                ,"select a cutoff to delete");
        if(cutoff==null)
            return;
        
                try {
			con.createStatement().executeQuery("delete from grading_cutoffs where letter_grade = '"+cutoff.split(",")[0]+"' and value ="+cutoff.split(",")[1]+" and SEC_REF_NUM = "+section);
			JOptionPane.showMessageDialog(null,"succesfully deleted "+cutoff+" from the list of cutoffs for section "+section);
		} catch (SQLException e1) {}
    }
    
}
