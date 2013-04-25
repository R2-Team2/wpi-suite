package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorPanel extends JPanel {

	private List<String> errorList;
	private JLabel errorMsg;
	
	/**
	 * Constructor for the error panel which displays errors as needed.
	 */
	public ErrorPanel()
	{
		super();
		
		errorList = new LinkedList<String>();
		errorMsg = new JLabel();
		errorMsg.setForeground(Color.RED);
		errorMsg.setAlignmentX(LEFT_ALIGNMENT);
	}
	
	/**
	 * Adds an error to the error list.
	 * @param msg the error to add
	 */
	public void displayError(String msg)
	{
		if(errorList.contains(msg)) return;
		this.errorList.add(msg);
		refreshErrors();
	}
	
	/**
	 * Removes all errors from the error list.
	 */
	public void removeAllErrors()
	{
		this.errorList.clear();
		refreshErrors();
	}
	
	/**
	 * Refreshes the error displayed at the bottom.
	 */
	public void refreshErrors()
	{
		errorMsg.setText("");
		for(String err : errorList)
		{
			errorMsg.setText(errorMsg.getText() + " " + err);
		}
	}
	
	/**
	 * @return whether there are outstanding errors
	 */
	public boolean hasErrors()
	{
		return this.errorList.size() == 0;
	}

	/**
	 * Removes the given error from the error list.
	 * @param msg the error to remove
	 */
	public void removeError(String msg) {
		this.errorList.remove(msg);
	}
}
