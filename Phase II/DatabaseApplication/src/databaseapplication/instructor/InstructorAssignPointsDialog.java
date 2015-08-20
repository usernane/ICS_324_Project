package databaseapplication.instructor;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import databaseapplication.CommonMethods;

public class InstructorAssignPointsDialog extends JFrame {

	private JTable table;
	private int max;
	private String section;
	private String componenetID; 
	public InstructorAssignPointsDialog(){

		CommonMethods cm = PackageMainInterface.cm;
                final Connection con = PackageMainInterface.con;
		
		final String section = cm.getFrom(cm, con
         	   	    ,"select REFRENCE_NUMBER from section where INSTRUCTOR_ID = "+PackageMainInterface.instructorID
            	   	 ,"there is no section taught by instructor "+PackageMainInterface.instructorID
               		 ,"select a section");
                if(section==null)
                	return;
                
		String course = section.substring(0, section.length()-1);/*		FIXME		*/
		
		String component = cm.getFrom(cm, con
         	   	    ,"select name FROM GRADING_COMPONENT where COURSE_NUMBER = "+course
            	   	 ,"there is no component"
               		 ,"select a componenet");
                if(component==null)
                    return;

                
		try {
			ResultSet r = con.createStatement().executeQuery("select id from grading_component where COURSE_NUMBER = "+course+" and name = '"+component+"'");
			r.next();
			componenetID = r.getString(1);
                        
                        table = cm.CreateTable(con, "select distinct id,concat(concat(first_name,' '),last_name) as name from STUDENT join ENROLLMENT on (ENROLLMENT.STUDENT_ID=STUDENT.ID) where id NOT in (select ENROLLMENT_STUDENT_ID from point where grading_component_id = "+componenetID+")");
			if(table.getRowCount()==1){
				JOptionPane.showMessageDialog(null,"there is no one to assign grade for");
				return;
			}
			r = con.createStatement().executeQuery("select max_points from GRADING_COMPONENT where COURSE_NUMBER = "+course+" and name = '"+component+"'");
			r.next();
			max = Integer.parseInt(r.getString(1));
		} catch (SQLException e) {}
		
		
		final DefaultTableModel model = (DefaultTableModel)table.getModel();
	    model.addColumn("");
	    model.setValueAt("POINTS("+max+")", 0, 2);
	    
	    Button b = new Button("submit");
	    b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int rows = model.getRowCount();
                        try{
                            for (int i=1;i<rows;i++) {
                                if(model.getValueAt(i, 2)==null){
                                    JOptionPane.showMessageDialog(new JFrame(), "there is cell without value",null,JOptionPane.ERROR_MESSAGE);
                                        return;
                                }
                                else if(Integer.parseInt(model.getValueAt(i, 2).toString()) > max){
                                    JOptionPane.showMessageDialog(new JFrame(), "the value : "+Integer.parseInt(model.getValueAt(i, 2).toString())+" exceeds the maximum("+max+")",null,JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            }//for

                            for (int i=1;i<rows;i++) {
                                try {
                                    con.createStatement().executeQuery("INSERT INTO POINT VALUES ('"+model.getValueAt(i, 2).toString()+"', '"+componenetID+"', '"+model.getValueAt(i, 0).toString()+"', '"+section+"')");
                                } catch (SQLException e1) {}
                            }
                            dispose();
                        }
                        catch(NumberFormatException exception){
                            JOptionPane.showMessageDialog(new JFrame(), "there is a cell with a wrong input",null,JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }	
                });
	    setLayout(new BorderLayout());
	    add(table);
	    add(b,BorderLayout.SOUTH);
		setSize(300,400);
		setVisible(true);
			
	}
}
