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
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.Transaction;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.TransactionHistory;

public class HistoryPanel extends JPanel {

	public HistoryPanel(Transaction transaction) {
		JLabel message = new JLabel(transaction.getMessage());
		String user = transaction.getUser();
		
		Date date = new Date(transaction.getTS());
		Format format = new SimpleDateFormat("MMMMM d, yyyy 'at' hh:mm:ss aaa");
		String transactionDate = format.format(date).toString();
		
		String info = user + " on " + transactionDate;
		JLabel transactionInfo = new JLabel(info);
		
		SpringLayout transactionLayout = new SpringLayout();
		this.setLayout(transactionLayout);
		
		SpringLayout messageLayout = new SpringLayout();
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(messageLayout);
		
		messageLayout.putConstraint(SpringLayout.WEST, message, 5,
				SpringLayout.WEST, messagePanel);
		messageLayout.putConstraint(SpringLayout.NORTH, message, 5,
				SpringLayout.NORTH, messagePanel);
		
		transactionLayout.putConstraint(SpringLayout.WEST, messagePanel, 5,
				SpringLayout.WEST, this);
		transactionLayout.putConstraint(SpringLayout.NORTH, messagePanel, 5,
				SpringLayout.NORTH, this);
		transactionLayout.putConstraint(SpringLayout.EAST, transactionInfo, 5,
				SpringLayout.EAST, this);
		transactionLayout.putConstraint(SpringLayout.NORTH, transactionInfo, 5,
				SpringLayout.SOUTH, messagePanel);
		
		messagePanel.add(message);
		this.add(messagePanel);
		this.add(transactionInfo);
		
	}
	
	
	public static JPanel createList(TransactionHistory list)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		ListIterator<Transaction> itt = list.getIterator(0);
		while(itt.hasNext())
		{
			panel.add(new HistoryPanel(itt.next()));
		}
		
		return panel;
	}

}
