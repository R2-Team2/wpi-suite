package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.Transaction;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.TransactionHistory;

public class HistoryPanel extends JPanel {

	public HistoryPanel(Transaction transaction) {
		
		this.setBorder(BorderFactory.createLineBorder(Color.black)); //Set note border
		
		JTextArea message = new JTextArea(transaction.getMessage());
		message.setEditable(false);
		// Give the message a black border with 2px padding inside
		Border b = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black), 
	            BorderFactory.createEmptyBorder(2, 2, 2, 2));
		message.setBorder(b);

		String user = transaction.getUser();
		
		Date date = new Date(transaction.getTS());
		Format format = new SimpleDateFormat("MMMMM d, yyyy 'at' hh:mm:ss aaa");
		String transactionDate = format.format(date).toString();
		
		JLabel transactionInfo = new JLabel(user + " on " + transactionDate);
		
		// SpringLayout noteLayout = new SpringLayout();
		this.setLayout(new GridBagLayout());
		GridBagConstraints noteConstraints = new GridBagConstraints();
		
		noteConstraints.fill = GridBagConstraints.HORIZONTAL;
		noteConstraints.gridy = 0; //Row 0
		noteConstraints.weightx = 1; //Fill the width
		noteConstraints.insets = new Insets(2,2,2,2); //2px margin
		this.add(message, noteConstraints);
		
		noteConstraints.anchor = GridBagConstraints.SOUTHEAST;
		noteConstraints.fill = GridBagConstraints.NONE;
		noteConstraints.gridy = 1; //Row 2
		this.add(transactionInfo, noteConstraints);
	}
	
	
	public static JPanel createList(TransactionHistory list)
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;//Fill horizontally
		c.gridy = 0; //Row 0
		c.insets = new Insets(5,5,5,5);
		
		ListIterator<Transaction> itt = list.getIterator(0);
		while(itt.hasNext())
		{
			//Create a new NotePanel for each Note and add it to the panel
			panel.add(new HistoryPanel(itt.next()),c);
			c.gridy++; //Next Row
		}
		
		//Create a dummy panel to take up space at the bottom
		c.weighty = 1;
		JPanel dummy = new JPanel();
		dummy.setBackground(Color.WHITE);
		panel.add(dummy,c);
		
		return panel;
	}

}
