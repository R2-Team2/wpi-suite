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
import javax.swing.JTextArea;
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
		
		JTextArea message = new JTextArea(note.getMessage());
		message.setEditable(false);
		message.setBorder(BorderFactory.createLineBorder(Color.black));
		//JLabel message = new JLabel(note.getMessage());
		String user = note.getUser();
		
		Date date = new Date(note.getTimestamp());
		Format format = new SimpleDateFormat("MMMMM d, yyyy 'at' hh:mm:ss aaa");
		String noteDate = format.format(date).toString();
		
		String info = user + " on " + noteDate;
		JLabel noteInfo = new JLabel(info);
		
		// SpringLayout noteLayout = new SpringLayout();
		this.setLayout(new GridBagLayout());
		GridBagConstraints noteConstraints = new GridBagConstraints();
		
		noteConstraints.fill = GridBagConstraints.HORIZONTAL;
		noteConstraints.gridy = 0;
		this.add(message, noteConstraints);
		
		noteConstraints.anchor = GridBagConstraints.SOUTHEAST;
		noteConstraints.fill = GridBagConstraints.NONE;
		noteConstraints.gridy = 1;
		this.add(noteInfo, noteConstraints);
	}
	
	public static JPanel createList(NoteList list)
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		ListIterator<Note> itt = list.getIterator(0);
		while(itt.hasNext())
		{
			panel.add(new NotePanel(itt.next()),c);
		}
		
		return panel;
	}
}
