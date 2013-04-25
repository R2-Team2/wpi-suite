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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.ViewMode;

/**
 *
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class IterationPanel extends JPanel implements KeyListener{
	private final String START_AFTER_END_ERROR = "Start date cannot be after end date.";
	private final String OVERLAPPING_ERROR = "Iteration dates cannot overlap.";
	private final String INVALID_NAME_ERROR = "Iteration exists with given name.";
	private final String EMPTY_NAME_ERROR = "Name is required.";
	private final String DATES_REQ = "Start and end date required.";
	private final String PAST_ERROR = "Iteration cannot occur in the past.";
	
	private IterationRequirements requirements;
	private IterationCalendarPanel calPanel;
	
	private JTextField boxName;
	
	private JPanel buttonPanel;
	
	private JFormattedTextField startDateBox;
	private JFormattedTextField endDateBox;
	
	private JButton buttonAdd;
	private JButton buttonCancel;
	private List<String> errorList;
	private JLabel errorMsg;
	
	private ViewMode vm;
	private Iteration displayIteration;
	
	/**
	 * The constructor for the iteration panel when creating an iteration.
	 */
	public IterationPanel() {
		this.vm = ViewMode.CREATING;
		buildLayout();
	}
	
	/**
	 * Constructor for the iteration panel when editing an iteration
	 * @param iter the iteration to edit.
	 */
	public IterationPanel(Iteration iter) {
		this.vm = ViewMode.EDITING;
		displayIteration = iter;
		buildLayout();
	}
	
	/**
	 * Builds the GUI layout for the iteration panel
	 */
	private void buildLayout(){
		this.setLayout(new BorderLayout());
		MigLayout layout = new MigLayout();

		JPanel contentPanel = new JPanel(layout);

		JLabel labelName = new JLabel("Name: ");

		boxName = new JTextField();
		boxName.setPreferredSize(new Dimension(200, 20));
		boxName.addKeyListener(this);
		
		JLabel labelStart = new JLabel("Start Date: ");
		JLabel labelEnd = new JLabel("End Date: ");
		
		startDateBox = new JFormattedTextField();
		startDateBox.setEnabled(false);
		startDateBox.setPreferredSize(new Dimension(150, 20));
		endDateBox = new JFormattedTextField();
		endDateBox.setEnabled(false);
		endDateBox.setPreferredSize(new Dimension(150, 20));
		
		contentPanel.add(labelName, "left");
		contentPanel.add(boxName, "left ");
		contentPanel.add(labelStart, "left");
		contentPanel.add(startDateBox, "left");
		contentPanel.add(labelEnd, "left");
		contentPanel.add(endDateBox, "left");
		
		
		buttonAdd = new JButton("Add Iteration");
		buttonCancel = new JButton("Cancel");
		buttonAdd.setAlignmentX(LEFT_ALIGNMENT);
		buttonCancel.setAlignmentX(LEFT_ALIGNMENT);
		
		errorList = new LinkedList<String>();
		errorMsg = new JLabel();
		errorMsg.setForeground(Color.RED);
		errorMsg.setAlignmentX(LEFT_ALIGNMENT);
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
		buttonPanel.add(buttonAdd);
		buttonAdd.setEnabled(false);
		buttonPanel.add(buttonCancel);
		buttonPanel.add(errorMsg);
		
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = IterationModel.getInstance().getNextID();
				String name = boxName.getText();
				
				displayIteration = new Iteration(id, name, (Date)startDateBox.getValue(), (Date)endDateBox.getValue());
				
				IterationModel.getInstance().addIteration(displayIteration);
				
				ViewEventController.getInstance().removeTab(IterationPanel.this);
			}
		});
		
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEventController.getInstance().refreshTable();
				ViewEventController.getInstance().refreshTree();
				ViewEventController.getInstance().removeTab(IterationPanel.this);
			}
		});
		
		JTabbedPane tabs = new JTabbedPane();

		calPanel = new IterationCalendarPanel(this);
		tabs.addTab("Iteration Calendar", calPanel);
		
		requirements = new IterationRequirements();
		tabs.addTab("Requirements", requirements);

	
		this.add(contentPanel, BorderLayout.NORTH);
		this.add(tabs, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
				
		validateFields();
	}
	
	/**
	 * Adds an error to the error list.
	 * @param msg the error to add
	 */
	private void displayError(String msg)
	{
		this.errorList.add(msg);
		refreshErrors();
	}
	
	/**
	 * Removes all errors from the error list.
	 */
	private void removeAllErrors()
	{
		this.errorList.clear();
		refreshErrors();
	}
	
	/**
	 * Refreshes the error displayed at the bottom.
	 */
	private void refreshErrors()
	{
		errorMsg.setText("");
		for(String err : errorList)
		{
			errorMsg.setText(errorMsg.getText() + " " + err);
		}
	}
	
	public void setDates(Date startDate, Date endDate)
	{
		if(startDate != null) this.startDateBox.setValue(startDate);
		if(endDate != null) this.endDateBox.setValue(endDate);
		
		validateFields();
	}
	
	/**
	 * Validates the options of the fields inputted.
	 */
	private void validateFields()
	{
		this.removeAllErrors();
		Calendar cal = new GregorianCalendar();
		cal.setTime(Calendar.getInstance().getTime());
		cal.add(Calendar.DAY_OF_YEAR, -1);
		
		if(boxName.getText().trim().length() == 0)
		{
			displayError(EMPTY_NAME_ERROR);
		}
		else if(IterationModel.getInstance().getIteration(boxName.getText().trim()) != null)
		{
			displayError(INVALID_NAME_ERROR);
		}
		
		if(endDateBox.getText().trim().length() == 0 || endDateBox.getText().trim().length() == 0)
		{
			displayError(DATES_REQ);
		}
		else if(((Date)startDateBox.getValue()).after((Date)endDateBox.getValue()))
		{
			displayError(START_AFTER_END_ERROR);
		}
		else if(((Date)startDateBox.getValue()).before(cal.getTime()))
		{
			displayError(PAST_ERROR);
		}
		else
		{
			Iteration conflicting = IterationModel.getInstance().getConflictingIteration((Date)startDateBox.getValue(), (Date)endDateBox.getValue());
			if(conflicting != null)
			{
				displayError(OVERLAPPING_ERROR + " Overlaps with " + conflicting.getName() + ".");
			}
		}
		
		buttonAdd.setEnabled(errorList.size() == 0);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		validateFields();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		validateFields();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		validateFields();
	}
	
	
}
