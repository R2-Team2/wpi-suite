/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.Note;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.NoteList;

/**
 * Class to display a note inside the EditRequirementPanel
 * @author Brian Froehlich
 *
 */
public class NotePanel extends JPanel {
	
	private Note note;
	
	/**
	 * Basic constructor for a NotePanel
	 * @param note Note to be displayed
	 */
	public NotePanel(Note note)
	{
		super();
		
		this.note = note;
		
		JLabel message = new JLabel(note.getMessage());
		String user = note.getUser();
		
		Date date = new Date(note.getTimestamp());
		Format format = new SimpleDateFormat("MMMMM d, yyyy 'at' hh:mm:ss aaa");
		String noteDate = format.format(date).toString();
		
		String info = user + " on " + noteDate;
		JLabel noteInfo = new JLabel(info);
		
		SpringLayout noteLayout = new SpringLayout();
		JPanel panel = new JPanel();
		panel.setLayout(noteLayout);
		
		SpringLayout messageLayout = new SpringLayout();
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(messageLayout);
		
		messageLayout.putConstraint(SpringLayout.WEST, message, 5,
				SpringLayout.WEST, messagePanel);
		messageLayout.putConstraint(SpringLayout.NORTH, message, 5,
				SpringLayout.NORTH, messagePanel);
		
		noteLayout.putConstraint(SpringLayout.WEST, messagePanel, 5,
				SpringLayout.WEST, panel);
		noteLayout.putConstraint(SpringLayout.NORTH, messagePanel, 5,
				SpringLayout.NORTH, panel);
		noteLayout.putConstraint(SpringLayout.EAST, noteInfo, 5,
				SpringLayout.EAST, panel);
		noteLayout.putConstraint(SpringLayout.NORTH, noteInfo, 5,
				SpringLayout.SOUTH, messagePanel);
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
