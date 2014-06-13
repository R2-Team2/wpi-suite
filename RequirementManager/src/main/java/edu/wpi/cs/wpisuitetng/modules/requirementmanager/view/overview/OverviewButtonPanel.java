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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.NewPieChartPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.Rotator;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class OverviewButtonPanel extends JPanel{

	public OverviewButtonPanel(){
		
		JButton buttonStatus = new JButton("Status");
		buttonStatus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				ViewEventController.getInstance().createPieChart("Status");
				
				
				
				
			}
		});
		JButton buttonIteration = new JButton("Iteration");
		buttonIteration.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				ViewEventController.getInstance().createPieChart("Iteration");
				
				
				
				
			}
		});
		JButton buttonRotate = new JButton("Rotate");
		
		
		this.add(buttonStatus);
		this.add(buttonIteration);
		//this.add(buttonRotate);
	}
	
}
