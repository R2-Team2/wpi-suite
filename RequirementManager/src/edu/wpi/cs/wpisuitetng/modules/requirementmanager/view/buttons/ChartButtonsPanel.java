/**
 *  * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons;

/**
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class ChartButtonsPanel extends JPanel{
	
	public ChartButtonsPanel(){
		setBorder(BorderFactory.createTitledBorder("Charts")); // add a border so you can see the panel
		SpringLayout toolbarLayout = new SpringLayout();
		this.setLayout(toolbarLayout);
		
		JButton pieChart = new JButton("Pie Chart");
		
		pieChart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ViewEventController.getInstance().createPieChart("Status");
			}
		});
		
		JButton barChart = new JButton("Bar Chart");
		
		barChart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ViewEventController.getInstance().createBarChart("Status");
				
				
				
				
			}
		});
		
		toolbarLayout.putConstraint(SpringLayout.NORTH, pieChart, 5,SpringLayout.NORTH, this);
		toolbarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, pieChart, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		toolbarLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, barChart, 0, SpringLayout.HORIZONTAL_CENTER, this);
		toolbarLayout.putConstraint(SpringLayout.NORTH, barChart, 5, SpringLayout.SOUTH, pieChart);
		
		this.add(pieChart);
		this.add(barChart);
	}
	
}
