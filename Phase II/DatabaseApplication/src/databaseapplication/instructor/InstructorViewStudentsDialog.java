package databaseapplication.instructor;

import Frameworks.UI.DataTable;
import Frameworks.table.TableData;
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
import databaseapplication.SuperManager;

public class InstructorViewStudentsDialog extends JFrame{
	
	//private JTable jTable;
        private DataTable table;
        
	InstructorViewStudentsDialog(){
            this.table = new DataTable();
            CommonMethods cm = PackageMainInterface.cm;
            Connection con = PackageMainInterface.con;
            String section = cm.getFrom(cm, con
            			,"select REFRENCE_NUMBER from section where instructor_id = "+PackageMainInterface.instructorID
            			,"there is no section taught by instructor "+PackageMainInterface.instructorID
            			,"select a section");
            if(section==null)
                return;

            setLayout(new BorderLayout());
            JTextField textField = new JTextField("the list of students");
            textField.setBackground(Color.LIGHT_GRAY);
            textField.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
            textField.setEditable(false);
            add(textField,BorderLayout.NORTH);
            
           // ScrollPane p = new ScrollPane();
            TableData data  = SuperManager.getConnectionManager().getResultSetAsTable(
                    "select student_id, first_name, last_name from student join enrollment on (student.id = enrollment.student_id and enrollment.SECTION_REFRENCE_NUMBER = '"+section+"')");
           // p.add(jTable);
            this.table.updateData(data);
            super.add(this.table,BorderLayout.CENTER);
            super.setSize((int) (this.table.getPreferredSize().width*1.25 +50 ),this.table.getPreferredSize().height*2 +50);

            //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            super.setLocationRelativeTo(null);
            super.setResizable(false);
            super.setVisible(true);
	}
}
