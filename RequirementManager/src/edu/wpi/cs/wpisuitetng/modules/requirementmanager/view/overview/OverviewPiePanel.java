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


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.NewPieChartPanel;

public class OverviewPiePanel extends JPanel{
	
	
	public OverviewPiePanel(String title){
		SpringLayout pieLayout = new SpringLayout();
		this.setLayout(pieLayout);
		
		OverviewFilterPanel buttonPanel = new OverviewFilterPanel();
		
		NewPieChartPanel newPie = new NewPieChartPanel(title); 
		
		JScrollPane piePanel = new JScrollPane(newPie);
		
		//set constraints for button panel
		pieLayout.putConstraint(SpringLayout.NORTH, buttonPanel, 0,SpringLayout.NORTH, this);
		pieLayout.putConstraint(SpringLayout.WEST, buttonPanel, 0, SpringLayout.WEST, this);
		pieLayout.putConstraint(SpringLayout.SOUTH, buttonPanel, 0, SpringLayout.SOUTH, this);
		pieLayout.putConstraint(SpringLayout.EAST, buttonPanel, 200,SpringLayout.WEST, buttonPanel);
		
		//set constraints for the pie chart
		pieLayout.putConstraint(SpringLayout.NORTH, buttonPanel, 0, SpringLayout.NORTH, this);
		pieLayout.putConstraint(SpringLayout.WEST, buttonPanel, 0, SpringLayout.EAST, buttonPanel);
		pieLayout.putConstraint(SpringLayout.EAST, buttonPanel, 0, SpringLayout.EAST, this);
		pieLayout.putConstraint(SpringLayout.SOUTH, buttonPanel, 0, SpringLayout.SOUTH, this);
		
		this.add(buttonPanel);
		this.add(piePanel);
	}
}
