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

public class InstructorViewPointsDialog extends JFrame{
	public InstructorViewPointsDialog() {
            CommonMethods cm = PackageMainInterface.cm;
            Connection con = PackageMainInterface.con;
		
                String section = cm.getFrom(cm, con
                    ,"select REFRENCE_NUMBER from section where INSTRUCTOR_ID = "+PackageMainInterface.instructorID
                    ,"there is no section taught by instructor "+PackageMainInterface.instructorID
                    ,"select reference number");
                if(section==null)
                    return;

		setLayout(new BorderLayout());
		JTable t = null;
                JTextField f = new JTextField("the points");
                f.setBackground(Color.LIGHT_GRAY);
                f.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
                f.setEditable(false);
                add(f,BorderLayout.NORTH);
                ScrollPane p = new ScrollPane();
                t = cm.CreateTable(con,
                        "select Student.id, CONCAT(CONCAT(first_name, ' '), last_name) name,POINT.EARNED_POINTS EARNED,GRADING_COMPONENT.NAME \"IN\" from STUDENT join POINT join GRADING_COMPONENT on(POINT.GRADING_COMPONENT_ID=GRADING_COMPONENT.ID) on (STUDENT.ID = POINT.ENROLLMENT_STUDENT_ID) where ENROLLMENT_REFRENCE_NUMBER = "+section+" order by POINT.GRADING_COMPONENT_ID asc,\"ID\" asc");
                p.add(t);
                add(p,BorderLayout.CENTER);
		setSize((int) (t.getPreferredSize().width*1.25 +50 ),t.getPreferredSize().height*2 +50);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		setResizable(false);
		setVisible(true);

	}
}
