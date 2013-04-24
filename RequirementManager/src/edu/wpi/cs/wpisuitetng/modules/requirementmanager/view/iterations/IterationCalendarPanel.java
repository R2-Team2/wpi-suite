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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class IterationCalendarPanel extends JScrollPane {
	
	private IterationCalendar calendarView;
	
	public IterationCalendarPanel()
	{
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new MigLayout());
		
		JPanel buttonPanel = new JPanel();
		JButton next = new JButton(">>");
		next.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				nextYear();
			}	
		});
		JLabel year = new JLabel ("Year");
		JButton prev = new JButton("<<");
		prev.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				previousYear();
			}
		});
		buttonPanel.add(prev);
		buttonPanel.add(year);
		buttonPanel.add(next);
		contentPanel.add(buttonPanel, "center, grow, span, wrap");
		
		JPanel calendarPanel = new JPanel(new BorderLayout());
		calendarView = new IterationCalendar();
		calendarPanel.add(calendarView, BorderLayout.CENTER);
		contentPanel.add(calendarPanel, "center, grow, span");
		
		this.setViewportView(contentPanel);
		
	}
	
	/**
	 * Switches the calendar to the previous year.
	 */
	private void previousYear()
	{
		Calendar cal = calendarView.getCalendar();
		cal.add(Calendar.YEAR, -1);
		calendarView.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the next year.
	 */
	private void nextYear()
	{
		Calendar cal = calendarView.getCalendar();
		cal.add(Calendar.YEAR, +1);
		calendarView.setFirstDisplayedDay(cal.getTime());
	}
}
