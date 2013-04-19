package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.NotePanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementViewMode;

public class RequirementNotePanel extends JPanel implements RequirementTab {
	private RequirementTabsPanel parentPanel;
	private RequirementViewMode viewMode;
	private Requirement currentRequirement;
	
	private JTextArea noteMessage;
	private JScrollPane noteScroll;
	private JButton buttonAddNote;
	private JButton buttonClear;
	private JLabel errorMsg;
	
	public RequirementNotePanel(RequirementTabsPanel parent, RequirementViewMode vm, Requirement current) {
		this.currentRequirement = current;
		this.viewMode = vm;
		this.parentPanel = parent;
		
		this.noteMessage = new JTextArea();
		this.noteScroll = new JScrollPane();

		// Buttons to be added to the bottom of the NotePanel
		buttonAddNote = new JButton("Add Note");
		buttonClear = new JButton("Clear");

		// Create text area for note to be added
		noteMessage.setLineWrap(true); // If right of box is reach, goes down a
										// line
		noteMessage.setWrapStyleWord(true); // Doesn't chop off words

		// Error message label in case no note was included
		errorMsg = new JLabel();

		// Layout manager for entire note panel
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();

		// Layout manager for panel that contains the buttons
		GridBagLayout bottomLayout = new GridBagLayout();
		JPanel bottomPanel = new JPanel(bottomLayout);
		GridBagConstraints bc = new GridBagConstraints();

		// Create new scroll pane for notes
		noteScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Always show scroll bar

		c.fill = GridBagConstraints.BOTH; // Fill grid cell with elements
		c.weightx = 1; // Fill horizontal space
		c.weighty = 1; // Fill all the vertical space
		this.add(noteScroll, c);

		c.gridy = 1; // Row 1
		c.weighty = .2; // Fill 0% of vertical space
		this.add(noteMessage, c);

		bc.anchor = GridBagConstraints.WEST; // Anchor buttons to west of bottom
												// panel
		bottomPanel.add(buttonAddNote, bc); // Include "Add note" button to
											// bottom panel

		bc.gridx = 1; // Column 1
		bottomPanel.add(buttonClear, bc); // Include "Clear" button to bottom
											// panel

		bc.gridx = 2; // Column 2
		bottomPanel.add(errorMsg, bc); // Add error message label to bottom
										// panel

		c.weighty = 0; // Do not stretch
		c.gridy = 2; // Row 2
		c.fill = GridBagConstraints.NONE; // Do not fill cell
		c.anchor = GridBagConstraints.WEST; // Anchor buttons to west of panel
		this.add(bottomPanel, c); // Add buttons to the panel
		
		this.setupListeners();
		this.refresh();
	}
	
	private void refresh()
	{
		noteScroll.setViewportView(NotePanel.createList(currentRequirement.getNotes()));
	}
	
	private void setupListeners()
	{
		// Listener for add note button
		buttonAddNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Display error message if there is no text in noteMessage
				if (noteMessage.getText().length() <= 0) {
					errorMsg.setText(" Error: Must add text to create note.");
				} else {

					String msg = noteMessage.getText(); // Get text from
														// noteMessage

					// Clear all text areas
					noteMessage.setText("");
					errorMsg.setText("");

					// Add note to requirement
					currentRequirement.getNotes().add(msg);

					refresh();

					// Update database so requirement stores new note
					UpdateRequirementController.getInstance()
							.updateRequirement(currentRequirement);
				}
			}
		});

		// Listener for the Clear button
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clear all text fields
				noteMessage.setText("");
				errorMsg.setText("");
			}
		});
	}

	@Override
	public boolean readyToRemove() {
		return noteMessage.getText().length() == 0;
	}
}
