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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.calendar.DateSelectionModel.SelectionMode;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.ViewMode;

/**
 */
public class IterationCalendar extends JXMonthView implements ActionListener {
	
	private ViewMode viewMode;
	private Date startDate = null;
	private Date endDate = null;
	private IterationPanel parent;
	private Iteration displayIteration;
	private boolean isOverview;
	/**
	 * Constructor for the iteration calendar.
	 * @param parent IterationPanel
	 * @param vm ViewMode
	 * @param displayIteration Iteration
	 */
	public IterationCalendar(IterationPanel parent, ViewMode vm, Iteration displayIteration)
	{
		this.isOverview = false;
		this.viewMode = vm;
		this.parent = parent;
		this.displayIteration = displayIteration;
		this.setFlaggedDayForeground(new Color(46, 79, 179));
		this.setSelectionMode(SelectionMode.SINGLE_INTERVAL_SELECTION);
		buildLayout();
	}
	
	/**
	 * Constructor for iteration calendar.
	 */
	public IterationCalendar() {
		this.isOverview = true;
		this.setFlaggedDayForeground(new Color(29, 245, 0));
		this.setSelectionMode(SelectionMode.SINGLE_SELECTION);
		buildLayout();
	}
	
	/**
	 * Builds the layout
	 */
	private void buildLayout()
	{
		this.setPreferredColumnCount(4);
		this.setPreferredRowCount(3);
		this.setSelectionBackground(new Color(95,242,90));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.addActionListener(this);
		
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(e.getClickCount() == 2)
				{
					int x = e.getX();
					int y = e.getY();
					Date forClick = IterationCalendar.this.getDayAtLocation(x, y);
					
					List<Iteration> forDate = IterationModel.getInstance().getIterationForDate(forClick);
									
					for(Iteration it : forDate)
					{
						ViewEventController.getInstance().editIteration(it);
					}
					
				}	
			}
		});
	}

	/**
	 * Method isFlaggedDate.
	 * @param date Date
	 * @return boolean
	 */
	@Override
	public boolean isFlaggedDate(Date date)
	{
		boolean isFlaggedDate = false;
		
		if(isOverview)
		{
			List<Iteration> forDate = IterationModel.getInstance().getIterationForDate(date);
						
			isFlaggedDate = forDate.size() > 0;
		}
		else
		{
		
			List<Iteration> forDate = IterationModel.getInstance().getIterationForDate(date);
			if(forDate.contains(displayIteration)) return false;
				
		
			for(Iteration iter : forDate)
			{
				isFlaggedDate |= date.equals(iter.getStart().getDate()) || date.equals(iter.getEnd().getDate());
			}
		}
		
		return isFlaggedDate || super.isFlaggedDate(date);
	}
	
	/**
	 * Method isUnselectableDate.
	 * @param date Date
	 * @return boolean
	 */
	@Override
	public boolean isUnselectableDate(Date date) {
		boolean unselectable = false;
		if(!isOverview)
		{
			List<Iteration> forDate = IterationModel.getInstance().getIterationForDate(date);
			
			if(forDate.contains(displayIteration)) return false;
			
			unselectable = forDate.size() == 1 && !isFlaggedDate(date);
		}

		return unselectable || super.isUnselectableDate(date);
	}


	/**
	 * Method actionPerformed.
	 * @param e ActionEvent
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isOverview)
		{
			this.getSelectionModel().clearSelection();
			return;
		}
		//check that the selected dates are valid dates.
		boolean validSelection = true;

		Date secondDate = this.getSelectionModel().getLastSelectionDate();
		Calendar firstDate = Calendar.getInstance();
		firstDate.setTime(this.getSelectionModel().getFirstSelectionDate());
		
		if(isUnselectableDate(firstDate.getTime()) || isUnselectableDate(secondDate)) validSelection = false;
		
		//if any date in the interval is unselectable, its invalid.
		while(firstDate.getTime().before(secondDate))
		{
			if(isUnselectableDate(firstDate.getTime()))
			{
				validSelection = false;
				break;
			}
			
			firstDate.add(Calendar.DAY_OF_YEAR, 1);
		}
	
		if(validSelection)
		{
			//if is valid date, update it as date in parent.
			startDate = this.getSelectionModel().getFirstSelectionDate();
			endDate = this.getSelectionModel().getLastSelectionDate();
			
			parent.setDates(startDate, endDate);
		}
		else
		{
			//otherwise, return back to previous selection.
			this.getSelectionModel().clearSelection();
			if(startDate != null && endDate != null)
			{
				this.getSelectionModel().setSelectionInterval(startDate, endDate);
			}
		}
	}

}
