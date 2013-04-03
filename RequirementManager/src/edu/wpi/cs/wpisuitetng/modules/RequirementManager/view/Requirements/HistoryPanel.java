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

	public HistoryPanel(String userNtime, String transactionMsgs) {
		
		this.setBorder(BorderFactory.createLineBorder(Color.black)); //Set note border
			
		JTextArea message = new JTextArea(transactionMsgs);
		message.setEditable(false);
		// Give the message a black border with 2px padding inside
		Border b = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black), 
	            BorderFactory.createEmptyBorder(2, 2, 2, 2));
		message.setBorder(b);

		JLabel transactionInfo = new JLabel(userNtime);
		
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
		
		// create iterator for transaction history
		ListIterator<Transaction> historyIterator = list.getIterator(0);
		
		// initialize thisTimeStamp, lastTimeStamp and thisDateTime
		long thisTimeStamp;
		long lastTimeStamp = 0;
		String thisDateTime;
		
		// initialize thisMessage and transactionMsgs
		String thisMessage;
		String transactionMsgs = "";
		
		// initialize thisUser and UserTime
		String thisUser;
		String UserTime = "";
		
		// initialize date and format
		Date date;
		Format format;
		
		// iterate through the transaction history of the requirement
		while(historyIterator.hasNext()) {
			// extract the next transaction
			Transaction thisTransaction = historyIterator.next();
			
			// extract time stamp of this transaction 
			thisTimeStamp = thisTransaction.getTS();
			
			// store the next index
			int nextIndex = historyIterator.nextIndex();
			
			// if this is the first transaction, store a new user and time stamp for display
			if (nextIndex == 1) {
				// convert the time stamp to date and time
				date = new Date(thisTimeStamp);
			    format = new SimpleDateFormat("MMMMM d, yyyy 'at' hh:mm:ss aaa");
			    thisDateTime = format.format(date).toString();
			    
			    // extract user
			    thisUser = thisTransaction.getUser();
			    
			    // combine user and date&time into single string
				UserTime = (thisUser + " on " + thisDateTime);		
				
				// extract this transaction's message
				transactionMsgs = thisTransaction.getMessage();				
			}
			
			// if this is not the first transaction, and
			// if the time stamp of this transaction does not match the time stamp of
			// the last transaction, create a new history panel for the last set of 
			// transactions and store a new user and time stamp for display in next panel
			else {				
				if (!(thisTimeStamp == lastTimeStamp)) {
					// Create a new HistoryPanel for the transactions and add it to the panel
					panel.add(new HistoryPanel(UserTime, transactionMsgs), c);
					// Next Row
					c.gridy++; 
					
					// set transaction string combination
					transactionMsgs = thisTransaction.getMessage();
					
					// convert the time stamp to date and time
					date = new Date(thisTimeStamp);
				    format = new SimpleDateFormat(("MMMMM d, yyyy 'at' hh:mm:ss aaa"));
				    thisDateTime = format.format(date).toString();
				    
				    // extract user
				    thisUser = thisTransaction.getUser();
				    
				    // combine user and date&time into single string
					UserTime = (thisUser + " on " + thisDateTime);					
				}			
			}	
			
			// append this transaction's message to the transaction string combination
			// with a line break first if there is a new addition 
			if (thisTimeStamp == lastTimeStamp) {				
				// extract this transaction's message
				thisMessage = thisTransaction.getMessage();
				
				// append
				transactionMsgs = transactionMsgs + "\n" + thisMessage;
			}
						
			if (!(historyIterator.hasNext())) {
				// Create a new HistoryPanel for the transactions and add it to the panel
				panel.add(new HistoryPanel(UserTime, transactionMsgs), c);
				// Next Row
				c.gridy++; 
			}
			
			// store current time stamp for comparison during next iteration 
			lastTimeStamp = thisTimeStamp;
		}		
		
		//Create a dummy panel to take up space at the bottom
		c.weighty = 1;
		JPanel dummy = new JPanel();
		dummy.setBackground(Color.WHITE);
		panel.add(dummy,c);
		
		return panel;
	}

}
