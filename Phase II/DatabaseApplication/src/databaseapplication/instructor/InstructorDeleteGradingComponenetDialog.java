package databaseapplication.instructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import databaseapplication.CommonMethods;

public class InstructorDeleteGradingComponenetDialog{

	InstructorDeleteGradingComponenetDialog(){
		
                CommonMethods cm = PackageMainInterface.cm;
                Connection con = PackageMainInterface.con;
		
                String course = cm.getFrom(cm, con
                        ,"select distinct course_number from grading_component where INSTRUCTOR_ID = "+PackageMainInterface.instructorID
            	   	 ,"there is no course assigned to be supervised by instructor "+PackageMainInterface.instructorID
               		 ,"select a course");
                if(course==null)
                    return;

        
		ArrayList<String> numbers = null;
		try {
			ResultSet r = con.createStatement().executeQuery("select name from grading_component where course_number = "+course);
			if(!r.next()){
				JOptionPane.showMessageDialog(null,"there is no grading component in "+course);
				return;
			}
			numbers = new ArrayList<>();
			do{
				numbers.add(r.getString(1));
			}while(r.next());
			
		} catch (SQLException e1){}
		
		String selected = cm.Combo(numbers.toArray(), "select Component");
		if(selected==null)
			return;
				
		try {
			con.createStatement().executeQuery("delete from grading_component where course_number = "+course+"and name = \'"+selected+"\'");
			JOptionPane.showMessageDialog(null,"succesfully deleted "+selected+" grading component from course numbered"+course);
		} catch (SQLException e1) {}
	}
}
