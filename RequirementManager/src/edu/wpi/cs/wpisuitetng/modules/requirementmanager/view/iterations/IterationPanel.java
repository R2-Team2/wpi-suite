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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXDatePicker;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 *
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class IterationPanel extends JPanel {
	private final String START_AFTER_END_ERROR = "Start date cannot be after end date.";
	private final String OVERLAPPING_ERROR = "Iteration dates cannot overlap.";
	private final String INVALID_NAME_ERROR = "Iteration exists with given name.";
	private final String EMPTY_NAME_ERROR = "Name is required.";
	private final String DATES_REQ = "Start and end date required.";
	
	private JTextField boxName;
	
	private JPanel buttonPanel;
	
	private JXDatePicker startDatePicker;
	private JXDatePicker endDatePicker;
	
	private JButton buttonAdd;
	private JButton buttonCancel;
	private List<String> errorList;
	private JLabel errorMsg;
	
	public IterationPanel(){
		this.setLayout(new BorderLayout());
		MigLayout layout = new MigLayout();

		JPanel contentPanel = new JPanel(layout);

		JLabel labelName = new JLabel("Name: ");

		boxName = new JTextField();
		boxName.setPreferredSize(new Dimension(200, 20));
		
		JLabel labelStart = new JLabel("Start Date: ");
		JLabel labelEnd = new JLabel("End Date: ");
		
		startDatePicker = new JXDatePicker();
		startDatePicker.setEnabled(false);
		endDatePicker = new JXDatePicker();
		endDatePicker.setEnabled(false);
		
		contentPanel.add(labelName, "left");
		contentPanel.add(boxName, "left, wrap");
		contentPanel.add(labelStart, "left");
		contentPanel.add(startDatePicker, "left,wrap");
		contentPanel.add(labelEnd, "left");
		contentPanel.add(endDatePicker, "left, wrap");
		
		
		buttonAdd = new JButton("Add Iteration");
		buttonCancel = new JButton("Cancel");
		buttonAdd.setAlignmentX(LEFT_ALIGNMENT);
		buttonCancel.setAlignmentX(LEFT_ALIGNMENT);
		
		errorList = new LinkedList<String>();
		errorMsg = new JLabel();
		errorMsg.setAlignmentX(LEFT_ALIGNMENT);
		
		buttonPanel = new JPanel();
		buttonPanel.add(buttonAdd);
		buttonPanel.add(buttonCancel);
		buttonPanel.add(errorMsg);
		/*
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error = 0;
				
				int id = IterationModel.getInstance().getIterations().size();
				String name = boxName.getText();
				if (name.length() == 0) {
					error = 1;
					System.out.println("Error");
				}
				
				IterationDate start = new IterationDate((Month) monthStart.getSelectedItem(),
						Integer.parseInt(dayStart.getText()),
						Integer.parseInt(yearStart.getText()));
				IterationDate end = new IterationDate((Month) monthEnd.getSelectedItem(),
						Integer.parseInt(dayEnd.getText()),
						Integer.parseInt(yearEnd.getText()));
				Iteration iter = new Iteration(id, name, start, end);
				
				if (error == 0) {
					IterationModel.getInstance().addIteration(iter);
				}
				ViewEventController.getInstance().removeTab(IterationPanel.this);
			}
		});*/
		
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEventController.getInstance().refreshTable();
				ViewEventController.getInstance().refreshTree();
				ViewEventController.getInstance().removeTab(IterationPanel.this);
			}
		});
	
		this.add(contentPanel, BorderLayout.CENTER);
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
		for(String err : errorList)
		{
			errorMsg.setText(errorMsg.getText() + " " + err);
		}
	}
	
	/**
	 * Validates the options of the fields inputted.
	 */
	private void validateFields()
	{
		this.removeAllErrors();
		
		if(boxName.getText().trim().length() == 0)
		{
			displayError(EMPTY_NAME_ERROR);
		}
		else if(IterationModel.getInstance().getIteration(boxName.getText().trim()) != null)
		{
			displayError(INVALID_NAME_ERROR);
		}
		
		if(startDatePicker.getEditor().getText().trim().length() == 0 || endDatePicker.getEditor().getText().trim().length() == 0)
		{
			displayError(DATES_REQ);
		}
		else if(startDatePicker.getDate().after(endDatePicker.getDate()))
		{
			displayError(START_AFTER_END_ERROR);
		}
		else if(IterationModel.getInstance().isValidIteration(startDatePicker.getDate(), endDatePicker.getDate()))
		{
			
		}
	}
	
	
}
