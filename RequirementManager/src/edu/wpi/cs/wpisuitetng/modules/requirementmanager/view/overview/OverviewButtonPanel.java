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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OverviewButtonPanel extends JPanel{

	public OverviewButtonPanel(){
		JButton buttonStatus = new JButton("Status");
		JButton buttonIteration = new JButton("Iteration");
		JButton buttonRotate = new JButton("Rotate");
		this.add(buttonStatus);
		this.add(buttonIteration);
		this.add(buttonRotate);
	}
	
}
