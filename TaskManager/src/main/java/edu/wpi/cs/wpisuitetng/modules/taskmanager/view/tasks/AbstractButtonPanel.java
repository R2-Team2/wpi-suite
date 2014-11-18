package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class AbstractButtonPanel extends JPanel {
	//Class Variables
	protected AbstractTaskPanel parentPanel;

	protected JButton buttonSave;
	protected JButton buttonCreate;
	protected JButton buttonCancel;



	/**
	 * Sets up the listeners for the buttons in the New Task Button Panel
	 */
	protected void setupListeners() {
		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.cancelPressed();
			}

		});
	}

}
