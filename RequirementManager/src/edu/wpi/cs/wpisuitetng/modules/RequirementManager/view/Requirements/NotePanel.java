/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.Note;

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
		JLabel message = new JLabel(note.getMessage());
		String user = note.getUser();
		
		Date date = new Date(note.getTimestamp());
		Format format = new SimpleDateFormat("MMMMM d, yyyy 'at' hh:mm:ss aaa");
		String noteDate = format.format(date).toString();
		
		String info = user + " on " + noteDate;
		JLabel noteInfo = new JLabel(info);
		
		SpringLayout noteLayout = new SpringLayout();
		this.setLayout(noteLayout);
		
		SpringLayout messageLayout = new SpringLayout();
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(messageLayout);
		
		messageLayout.putConstraint(SpringLayout.WEST, message, 5,
				SpringLayout.WEST, messagePanel);
		messageLayout.putConstraint(SpringLayout.NORTH, message, 5,
				SpringLayout.NORTH, messagePanel);
		
		noteLayout.putConstraint(SpringLayout.WEST, messagePanel, 5,
				SpringLayout.WEST, this);
		noteLayout.putConstraint(SpringLayout.NORTH, messagePanel, 5,
				SpringLayout.NORTH, this);
		noteLayout.putConstraint(SpringLayout.EAST, noteInfo, 5,
				SpringLayout.EAST, this);
		noteLayout.putConstraint(SpringLayout.NORTH, noteInfo, 5,
				SpringLayout.SOUTH, messagePanel);
		
		messagePanel.add(message);
		this.add(messagePanel);
		this.add(noteInfo);
	}
}
