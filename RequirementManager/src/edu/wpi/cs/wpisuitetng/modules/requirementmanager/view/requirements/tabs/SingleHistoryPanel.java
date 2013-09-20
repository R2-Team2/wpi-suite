/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.Transaction;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;

/**
 *
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class SingleHistoryPanel extends JPanel {

	
	/**
	 * Basic constructor for HistoryPanel
	 * @param userNtime The time stamp of the transaction
	 * @param transactionMsgs The transaction message to be displayed
	 */
	public SingleHistoryPanel(String userNtime, String transactionMsgs) {
		
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
	
	
	/**
	 * Displays the list of all transactions of the requirement
	 * @param list List of transactions of requirement
	
	 * @return The panel with all the transaction panels */
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
		
		// initialize appended message holder and panel creation flag
		String appendedMessages = "";
		boolean panelWasCreated = false;
		
		// set Date and Time format
		Format format = new SimpleDateFormat("MMMMM d, yyyy 'at' hh:mm:ss aaa");
		
		// iterate through the transaction history of the requirement
		while(historyIterator.hasNext()) {
			// check to see if this is the first transaction by checking to see if 
			// there is a previous transaction
			if (!historyIterator.hasPrevious()) {
				// if this is the first transaction, iterate and extract the transaction
				Transaction thisTransaction = historyIterator.next();
				
				// convert the time stamp to date and time
				Date date = new Date(thisTransaction.getTS());
			    
			    // combine user and date&time into single string
				String UserTime = ("by " + thisTransaction.getUser() + " on " + format.format(date).toString());		
				
				// Create a new history panel for the transaction and add it to the panel
				panel.add(new SingleHistoryPanel(UserTime, thisTransaction.getMessage()), c);
				// Next Row
				c.gridy++; 
				
				// confirm that a panel was just created
				panelWasCreated = true;
			}			
			// if this is not the first transaction
			else {
				// iterate and extract transaction
				Transaction thisTransaction = historyIterator.next();
				
				if (panelWasCreated) {
					// set appended message holder to the message of this transaction if
					// a panel was created more recently than a message was last appended
					appendedMessages = thisTransaction.getMessage();
					
					// confirm that a panel was not just created
					panelWasCreated = false;
				}				
				else {
					// append message of this transaction if a panel was not created more
					// recently than a message was last appended
					appendedMessages += ("\n" + thisTransaction.getMessage());
				}
				
				// if there is a subsequent transaction in the transaction history
				if (historyIterator.hasNext()) {
					// if this transaction's time stamp is the different from the subsequent
					// transaction's time stamp 					
					if (!(thisTransaction.getTS() == list.getItem(historyIterator.nextIndex()).getTS())){
						// convert the time stamp to date and time
						Date date = new Date(thisTransaction.getTS());
					    
					    // combine user and date&time into single string
						String UserTime = ("by " + thisTransaction.getUser() + " on " + format.format(date).toString());		
						
						// Create a new history panel for the transaction and add it to the panel
						panel.add(new SingleHistoryPanel(UserTime, appendedMessages), c);
						// Next Row
						c.gridy++; 
						
						// confirm that a panel was just created
						panelWasCreated = true;						
					}
				}
				// if there is no subsequent transaction in the history
				else {
					// convert the time stamp to date and time
					Date date = new Date(thisTransaction.getTS());
				    
				    // combine user and date&time into single string
					String UserTime = ("by " + thisTransaction.getUser() + " on " + format.format(date).toString());		
					
					// Create a new history panel for the transaction and add it to the panel
					panel.add(new SingleHistoryPanel(UserTime, appendedMessages), c);
					// Next Row
					c.gridy++; 
				}				
			}
		}

		c.weighty = 1;
		JPanel dummy = new JPanel();
		dummy.setBackground(Color.WHITE);
		panel.add(dummy,c);
		
		return panel;
	}

}
