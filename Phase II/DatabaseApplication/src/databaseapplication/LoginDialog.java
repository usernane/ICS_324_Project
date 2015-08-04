package databaseapplication;
import Frameworks.UI.OkCancelPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

	private JComboBox<String> loginType;
	private OkCancelPanel okCancelPanel;
	private JLabel passwordLabel;
        private JLabel usernameLabel;
        private JLabel loginTypeLabel;
	private JTextField passwordTextField;
	private JTextField usernameTextField;

	public LoginDialog(){
            init();
            buildUI();
	}
        private void init(){
            this.loginType = new JComboBox<>();
            this.loginTypeLabel = new JLabel("Login Type:");
            this.passwordLabel = new JLabel("Password:");
            this.usernameLabel = new JLabel("Username:");
            this.okCancelPanel = new OkCancelPanel();
            this.passwordTextField = new JTextField();
            this.usernameTextField = new JTextField();
            super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            this.loginType.addItem("Admin");
            this.loginType.addItem("Instructor");
            this.loginType.addItem("Student");
        }
        private void buildUI(){
            super.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 1;
                    
            c.gridx = 0;
            c.gridy = 0;
            super.add(this.loginTypeLabel,c);
            
            c.gridx = 1;
            c.gridy = 0;
            super.add(this.loginType,c);
            
            c.gridx = 0;
            c.gridy = 1;
            super.add(this.usernameLabel,c);
            
            c.gridx = 1;
            c.gridy = 1;
            super.add(this.usernameTextField,c);
            
            c.gridx = 0;
            c.gridy = 2;
            super.add(this.passwordLabel,c);
            
            c.gridx = 1;
            c.gridy = 2;
            super.add(this.passwordTextField,c);
            
            c.gridx = 0;
            c.gridy = 3;
            c.gridwidth = 2;
            super.add(this.okCancelPanel,c);
            
            super.setSize(400, 150);
            super.setResizable(false);
            super.setVisible(true);
        }
	public String getLoginType(){
		return  (String)this.loginType.getSelectedItem();
	}

	public String getPassword(){
		return this.passwordTextField.getText();
	}

	public String getUserName(){
		return this.usernameTextField.getText();
	}

}