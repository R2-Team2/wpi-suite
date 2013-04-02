/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.Note;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.NoteList;

/**
 * Class to display a note inside the EditRequirementPanel
 * @author Brian Froehlich
 *
 */
public class NotePanel extends JPanel {
	
	/**
	 * Basic constructor for a NotePanel
	 * @param note Note to be displayed
	 */
	public NotePanel(Note note)
	{
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel message = new JLabel(note.getMessage());
		String user = note.getUser();
		
		Date date = new Date(note.getTimestamp());
		Format format = new SimpleDateFormat("MMMMM d, yyyy 'at' hh:mm:ss aaa");
		String noteDate = format.format(date).toString();
		
		String info = user + " on " + noteDate;
		JLabel noteInfo = new JLabel(info);
		
		// SpringLayout noteLayout = new SpringLayout();
		GridBagLayout noteLayout = new GridBagLayout();
		this.setLayout(noteLayout);
		GridBagConstraints noteConstraints = new GridBagConstraints();
		
		// SpringLayout messageLayout = new SpringLayout();
		GridBagLayout messageLayout = new GridBagLayout();
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(messageLayout);
		messagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagConstraints msgConstraints = new GridBagConstraints();
		/*
		messageLayout.putConstraint(SpringLayout.WEST, message, 5,
				SpringLayout.WEST, messagePanel);
		messageLayout.putConstraint(SpringLayout.NORTH, message, 5,
				SpringLayout.NORTH, messagePanel);
		
		noteLayout.putConstraint(SpringLayout.WEST, messagePanel, 5,
				SpringLayout.WEST, this);
		noteLayout.putConstraint(SpringLayout.NORTH, messagePanel, 5,
				SpringLayout.NORTH, this);
		noteLayout.putConstraint(SpringLayout.EAST, messagePanel, 5,
				SpringLayout.EAST, this);
		noteLayout.putConstraint(SpringLayout.SOUTH, messagePanel, 85,
				SpringLayout.NORTH, this);
		noteLayout.putConstraint(SpringLayout.EAST, noteInfo, 5,
				SpringLayout.EAST, this);
		noteLayout.putConstraint(SpringLayout.NORTH, noteInfo, 5,
				SpringLayout.SOUTH, messagePanel);
		*/
		msgConstraints.weightx = 1; // Fill horizontal space
		msgConstraints.weighty = 0; // Fill vertical space
		messagePanel.add(message, msgConstraints);
		
		noteConstraints.fill = GridBagConstraints.HORIZONTAL;
		noteConstraints.weightx = 1; // Fill horizontal space
		noteConstraints.weighty = 0; // Fill 80% of vertical space
		this.add(messagePanel, noteConstraints);
		
		noteConstraints.anchor = GridBagConstraints.SOUTHEAST;
		noteConstraints.fill = GridBagConstraints.NONE;
		noteConstraints.gridy = 1;
		noteConstraints.weighty = 0; // Fill 80% of vertical space
		this.add(noteInfo, noteConstraints);
	}
	
	public static JPanel createList(NoteList list)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		ListIterator<Note> itt = list.getIterator(0);
		while(itt.hasNext())
		{
			panel.add(new NotePanel(itt.next()));
		}
		
		return panel;
	}
}
