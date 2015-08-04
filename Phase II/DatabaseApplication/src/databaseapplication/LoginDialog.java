package databaseapplication;
import Frameworks.UI.OkCancelPanel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Ibrahim
 * @version 1.0
 * @created 04-Aug-2015 3:10:58 AM
 */
public class LoginDialog extends JDialog{

	private JComboBox loginType;
	private JLabel loginTypeLabel;
	private OkCancelPanel okCancelPanel;
	private JLabel passwordLabel;
	private JTextField passwordTextField;
	private JLabel usernameLabel;
	private JTextField usernameTextField;

	public LoginDialog(){

	}

	public String getLoginType(){
		return "";
	}

	public String getPassword(){
		return null;
	}

	public String getUserName(){
		return "";
	}

}