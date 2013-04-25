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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.calendar.DateSelectionModel.SelectionMode;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;

public class IterationCalendar extends JXMonthView implements ActionListener {
	
	private Date startDate = null;
	private Date endDate = null;
	private IterationPanel parent;
	/**
	 * Constructor for the iteration calendar.
	 */
	public IterationCalendar(IterationPanel parent)
	{
		this.parent = parent;
		this.setPreferredColumnCount(4);
		this.setPreferredRowCount(3);
		this.setFlaggedDayForeground(Color.GREEN);
		this.setSelectionBackground(Color.GREEN);
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setSelectionMode(SelectionMode.SINGLE_INTERVAL_SELECTION);
		this.addActionListener(this);
	}
	
	@Override
	public boolean isUnselectableDate(Date date) {
		boolean unselectable = IterationModel.getInstance().getIterationForDate(date) != null;
		
		if(unselectable)
		{
			this.getSelectionModel().getUnselectableDates().add(date);
		}
		else
		{
			this.getSelectionModel().getUnselectableDates().remove(date);
		}
		
		return unselectable || super.isUnselectableDate(date);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		startDate = this.getSelectionModel().getFirstSelectionDate();
		endDate = this.getSelectionModel().getLastSelectionDate();
		
		parent.setDates(startDate, endDate);
	}

}
