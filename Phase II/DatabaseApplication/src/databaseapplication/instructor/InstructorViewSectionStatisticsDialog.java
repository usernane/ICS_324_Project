package databaseapplication.instructor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import databaseapplication.CommonMethods;

public class InstructorViewSectionStatisticsDialog extends JFrame{
	public InstructorViewSectionStatisticsDialog() {
                CommonMethods cm = PackageMainInterface.cm;
                Connection con = PackageMainInterface.con;		
                String section = cm.getFrom(cm, con
                        ,"select distinct REFRENCE_NUMBER from section where INSTRUCTOR_ID = "+PackageMainInterface.instructorID
            	   	 ,"there is no course assigned to instructor "+PackageMainInterface.instructorID
               		 ,"select a section");
           	if(section==null)
                	return;
		
		setLayout(new BorderLayout());
		JTable t = null;
                JTextField f = new JTextField("Statistics about section "+section);
                f.setBackground(Color.LIGHT_GRAY);
                f.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
                f.setEditable(false);
                add(f,BorderLayout.NORTH);
                ScrollPane p = new ScrollPane();
                t = cm.CreateTable(cm.getConnection(),
                        "select NAME,MAX_POINTS \"OUT OF\",WEIGHT,MINIMUM,AVERAGE,MAXIMUM from GRADING_COMPONENT name join (select GRADING_COMPONENT.NAME x,min(POINT.EARNED_POINTS) MINIMUM, avg(POINT.EARNED_POINTS) AVERAGE , max(POINT.EARNED_POINTS) MAXIMUM from STUDENT join POINT join GRADING_COMPONENT on(POINT.GRADING_COMPONENT_ID=GRADING_COMPONENT.ID) on (STUDENT.ID = POINT.ENROLLMENT_STUDENT_ID) where ENROLLMENT_REFRENCE_NUMBER = "+section+"group by GRADING_COMPONENT.NAME) on (name = x) ORDER BY NAME.ID");
                p.add(t);
                add(p,BorderLayout.CENTER);
		setSize((int) (t.getPreferredSize().width*1.25 +50 ),t.getPreferredSize().height*2 +50);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		setResizable(false);
		setVisible(true);

	}
}
