package databaseapplication;

import Frameworks.OperationResult;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import databaseapplication.instructor.PackageMainInterface;

public class CommonMethods {
	
	public Connection getConnection(){
		String[][] users = {
                                    //{"jdbc:oracle:thin:@localhost:1521:xe","ibra5him","1415620."}
                                    //,{"jdbc:derby://localhost:1527/ProjectDB","ibra5him","1415620."}                                    
                                    {"jdbc:derby://localhost:1527/ProjectDB","ibrahim","ibrahim"}
                                    //,{"jdbc:oracle:thin:@localhost:1521:xe","system","201236760"}
                                    //,{"jdbc:oracle:thin:@ics-db.ccse.kfupm.edu.sa:1521:xe","s201224780","201224780"}
                                    };
		
		for (String[] x : users) {
			try {
				return DriverManager.getConnection(x[0],x[1],x[2]);					
			} catch (SQLException e) {}			
		}
		return null;//unsuccesful
	}

	public JTable CreateTable(Connection conn,String query) {
		          JTable a = null;
            try {
                Statement s = conn.createStatement();
                ResultSet r = s.executeQuery(query);
                int rows = getRows(r);
             
                a = new JTable(rows + 1, r.getMetaData().getColumnCount());
  
                a.setRowHeight(20);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                a.setDefaultRenderer(String.class, centerRenderer);
                r = s.executeQuery(query);
                
                for (int i = 0; i < a.getColumnCount(); i++) {//attributes
                    
                    a.setValueAt(r.getMetaData().getColumnLabel(i + 1), 0, i);
                    a.getColumnModel().getColumn(i).setPreferredWidth(r.getMetaData().getColumnLabel(i + 1).length() * 10);
                }
                
                for (int j = 1; j < a.getRowCount(); j++) {
                    r.next();                    
                    for (int i = 0; i < a.getColumnCount(); i++) {
                        a.setValueAt(r.getString(i + 1), j, i);
                        if (r.getString(i + 1) != null) {
                            if (a.getColumnModel().getColumn(i).getPreferredWidth() < r.getString(i + 1).length() * 10) {
                                a.getColumnModel().getColumn(i).setPreferredWidth(r.getString(i + 1).length() * 10);
                            }
                        }
                    }
                }//for rows
            } catch (SQLException sQLException) {
            }
		return a;//table
	}
    private int getRows(ResultSet r){
            ResultSet temp = r;
            int i = 0;
            try {
                while(temp.next())
                    i++;
            } catch (SQLException ex) {}
                return i;
        }
        
	public JFrame CentralizeFrame(JFrame f){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
		f.setResizable(false);
		f.setVisible(true);
		return f;
	}

	public String Combo(Object[] objs, String msg){
		String a = (String) JOptionPane.showInputDialog(
				null,msg,null,JOptionPane.QUESTION_MESSAGE,null,objs,objs[0]);
		
		return a;
	}

	public String getFrom(CommonMethods cm, Connection con,String query,String msg,String choose) {
		String selection;
		ArrayList<String> choices = null;
		try {
		    OperationResult o = SuperManager.executeQuery(query);
                    ResultSet r = SuperManager.getResultSet();
                    System.out.println(query);
		    if(!o.getResult()){
		        JOptionPane.showMessageDialog(null,o.getMessage());
		        return null;
		    }
                    choices = new ArrayList<>();
                    while(r.next()){
                        choices.add(r.getString(1));
                        System.out.println(r.getString(1));
                    }
		    

		} catch (SQLException e){System.out.println(e);}

		selection = cm.Combo(choices.toArray(), choose);
		return selection;
	}

}
