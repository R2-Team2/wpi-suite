package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

public class RequirementButtonPanel extends JPanel
{
	private RequirementPanel parentPanel;
	private RequirementViewMode viewMode;
	
	private JButton buttonOK;
	private JButton buttonCancel;
	private JButton buttonClear;
	private JButton buttonDelete;
	private Requirement currentRequirement;
	private boolean changes;
	private boolean valid;
	
	public RequirementButtonPanel(RequirementPanel parentPanel, RequirementViewMode mode, Requirement curr)
	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.changes = false;
		this.valid = false;
		this.currentRequirement = curr;
		this.parentPanel = parentPanel;
		this.viewMode = mode;
		
		String okString;
		String cancelString = "Cancel";
		String clearString;
		String deleteString = "Delete";
		
		buttonOK = new JButton();
		buttonCancel = new JButton(cancelString);
		buttonClear = new JButton();
		buttonDelete = new JButton(deleteString);
		
		this.add(buttonOK);
		this.add(buttonClear);
		if(this.viewMode == RequirementViewMode.CREATING)
		{
			okString = "Create";
			clearString = "Clear";
		}
		else
		{
			okString = "Update";
			clearString = "Undo Changes";
			this.add(buttonDelete);
		}
		
		buttonOK.setText(okString);
		buttonClear.setText(clearString);
		this.add(buttonCancel);
		this.buttonOK.setEnabled(false);
		this.buttonClear.setEnabled(false);
		setupListeners();
	}
	
	/**
	 * Sets up the listeners for the buttons in the requirement button panel.
	 */
	private void setupListeners()
	{
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.OKPressed();
			}
		});

		buttonClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.clearPressed();
			}

		});
		
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.cancelPressed();
			}
		});
		
		buttonDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				parentPanel.deletePressed();
			}
		});
	}

	public void fireDeleted(boolean b) 
	{
		this.buttonDelete.setEnabled(b);
	}

	public void fireValid(boolean b) {
		valid = b;
		this.buttonOK.setEnabled(b && changes);
	}

	public void fireChanges(boolean b) {
		changes = b;
		this.buttonOK.setEnabled(b && valid);
		this.buttonClear.setEnabled(b);
	}

	public JButton getButtonClear() {
		return buttonClear;
	}
	
	public JButton getButtonDelete() {
		return buttonDelete;
	}
	
	public JButton getButtonOK() {
		return buttonOK;
	}
	
	public JButton getButtonCancel() {
		return buttonCancel;
	}
}
