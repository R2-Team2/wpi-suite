/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.Transaction;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;

/**
 * @author paul
 *
 */
public class TransactionHistoryPanel extends JScrollPane {

	
	/**
	 * Constructor to build the Transaction History panel that is viewed when editing a requirement
	 * 
	 * THIS IS CURRENTLY NOT USED and will be used in the future (when re-factoring the code).
	 */
	public TransactionHistoryPanel() {
		
		
		JPanel historyPanel = new JPanel();
		BoxLayout layout = new BoxLayout(historyPanel, BoxLayout.PAGE_AXIS);
		historyPanel.setLayout(layout);
		
		
		this.setViewportView(historyPanel);
	}
}
