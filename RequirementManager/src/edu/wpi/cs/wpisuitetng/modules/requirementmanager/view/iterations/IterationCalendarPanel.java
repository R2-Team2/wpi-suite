/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.iterations;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXMonthView;

public class IterationCalendarPanel extends JScrollPane {
	
	private JXMonthView calendarView;
	
	public IterationCalendarPanel()
	{
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new MigLayout());
		
		calendarView = new JXMonthView();
		calendarView.setPreferredColumnCount(4);
		calendarView.setPreferredRowCount(3);
		
		contentPanel.add(calendarView, "center, grow, span");
		
		this.setViewportView(contentPanel);
		
	}
}
