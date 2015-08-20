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

public class InstructorViewGradingComponentsDialog extends JFrame {
	
	public InstructorViewGradingComponentsDialog() {
                CommonMethods cm = PackageMainInterface.cm;
                Connection con = PackageMainInterface.con;
                
                String selected = cm.getFrom(cm, con
                    ,"select distinct COURSE_NUMBER from grading_component where INSTRUCTOR_ID = "+PackageMainInterface.instructorID
                    ,PackageMainInterface.instructorID+" dont have any grading compnenets"
                    ,"select a course");
                
                if(selected==null)
                    return;

                setLayout(new BorderLayout());
		JTable t = null;
                JTextField f = new JTextField("the grading components of section "+selected);
                f.setBackground(Color.LIGHT_GRAY);
                f.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
                f.setEditable(false);
                add(f,BorderLayout.NORTH);
                ScrollPane p = new ScrollPane();
                t = cm.CreateTable(cm.getConnection(),
                        "select name,max_points,weight from grading_component where course_number ="+selected);
                p.add(t);
                add(p,BorderLayout.CENTER);
		setSize((int) (t.getPreferredSize().width*1.25 +50 ),t.getPreferredSize().height*2 +50);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		setResizable(false);
		setVisible(true);

	}
}
