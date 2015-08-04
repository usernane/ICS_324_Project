package Frameworks.UI;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Ibrahim
 * @version 1.0
 * @created 04-Aug-2015 3:03:04 AM
 */
public class OkCancelPanel extends JPanel{

	private JButton cancelButton;
	private JButton okButton;

	/**
	 * 
	 * @param cancelEvent
	 * @param okEvent
	 */
	public OkCancelPanel(ActionListener cancelEvent, ActionListener okEvent){
            super.setLayout(new FlowLayout());
            this.okButton = new JButton("Ok");
            this.cancelButton = new JButton("Cancel");
            if(okEvent != null){
                this.okButton.addActionListener(okEvent);
            }
            if(cancelEvent != null){
                this.cancelButton.addActionListener(cancelEvent);
            }
            super.add(this.cancelButton);
            super.add(this.okButton);
	}

	public OkCancelPanel(){
            this(null,null);
	}

	/**
	 * 
	 * @param event
	 */
	public void addCancelClickEvent(ActionListener event){
            if(event != null)
                this.cancelButton.addActionListener(event);
	}

	/**
	 * 
	 * @param event
	 */
	public void addOkClickEvent(ActionListener event){
            if(event != null)
                this.okButton.addActionListener(event);
	}

	/**
	 * 
	 * @param text
	 */
	public void setCancelButtonText(String text){
            this.cancelButton.setText(""+text);
	}

	/**
	 * 
	 * @param text
	 */
	public void setOkButtonText(String text){
            this.okButton.setText(""+text);
	}

}