package databaseapplication.instructor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import databaseapplication.CommonMethods;

public class InstructorViewActivityPointsDialog extends JFrame {
	
		public InstructorViewActivityPointsDialog() {

			CommonMethods cm = PackageMainInterface.cm;
                        Connection con = PackageMainInterface.con;

                String activity = cm.getFrom(cm, con
                        ,"select id from grading_component where INSTRUCTOR_ID = "+PackageMainInterface.instructorID
                        ,"there is no activity assigned by instructor "+PackageMainInterface.instructorID
                        , "select an activity");
   		if(activity==null)
                        return;

                setLayout(new BorderLayout());
                JTable t = null;
                try {
                    JTextField f = new JTextField("the points for activity "+activity);
                    f.setBackground(Color.LIGHT_GRAY);
                    f.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
                    f.setEditable(false);
                    add(f,BorderLayout.NORTH);
                    ScrollPane p = new ScrollPane();
                    t = cm.CreateTable(con,
                                    "select Student.id, CONCAT(CONCAT(first_name, ' '), last_name) name,POINT.EARNED_POINTS EARNED from STUDENT join POINT join GRADING_COMPONENT on(POINT.GRADING_COMPONENT_ID=GRADING_COMPONENT.ID) on (STUDENT.ID = POINT.ENROLLMENT_STUDENT_ID) where GRADING_COMPONENT.ID = "+activity+" order by POINT.GRADING_COMPONENT_ID asc");
                    p.add(t);

                    ResultSet r = con.createStatement().executeQuery("select avg(POINT.EARNED_POINTS) Average from STUDENT join POINT join GRADING_COMPONENT on(POINT.GRADING_COMPONENT_ID=GRADING_COMPONENT.ID) on (STUDENT.ID = POINT.ENROLLMENT_STUDENT_ID) where GRADING_COMPONENT.ID ="+activity+" ");
                    r.next();
                    JTextField text = new JTextField("the average is " + r.getString(1));
                    text.setEditable(false);
                    text.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
                    add(p,BorderLayout.CENTER);
                    add(text,BorderLayout.SOUTH);
                } catch (SQLException e) {}
                setSize((int) (t.getPreferredSize().width*1.25 +50 ),t.getPreferredSize().height*2 +50);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
                setResizable(false);
                setVisible(true);

		}
}
