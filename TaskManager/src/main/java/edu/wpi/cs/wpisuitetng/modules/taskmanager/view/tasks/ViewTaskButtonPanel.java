package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ViewTaskButtonPanel extends AbstractButtonPanel{
	protected ViewTaskPanel parentPanel;
	
	public ViewTaskButtonPanel(ViewTaskPanel parentPanel)
	{
		//Set Panel Layout
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//Set Parent Panel
		this.parentPanel = parentPanel;
		
		//Set Button Messages
		String editString = "Edit";
		String cancelString = "Cancel";
		
		//Create Buttons
		buttonEdit = new JButton(editString);
		buttonCancel = new JButton(cancelString);
		this.add(buttonEdit);
		this.add(buttonCancel);
		super.setupListeners();
		this.setupListeners();
	}
	
	protected void setupListeners() {
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.editPressed();
			}
		});
	}
}
