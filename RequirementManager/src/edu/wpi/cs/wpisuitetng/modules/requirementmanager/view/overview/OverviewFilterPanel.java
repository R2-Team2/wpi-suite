/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class OverviewFilterPanel extends JPanel {
	
	/**
	 * Sets up the left hand panel of the overview
	 */
	public OverviewFilterPanel()
	{	
		JLabel filterPanel = new JLabel ("Filtering Options Go Here");
		
		this.add(filterPanel);
	}
}
