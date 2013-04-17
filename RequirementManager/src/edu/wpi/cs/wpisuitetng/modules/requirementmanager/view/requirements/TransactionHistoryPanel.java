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

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
