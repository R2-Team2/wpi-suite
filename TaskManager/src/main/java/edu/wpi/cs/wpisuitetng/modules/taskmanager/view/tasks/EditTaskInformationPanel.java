/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	Team R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

// TODO: Auto-generated Javadoc
/**
 * The Class EditTaskInformationPanel.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class EditTaskInformationPanel extends AbstractInformationPanel{

	/**
	 * Constructor for NewTaskInformationPanel.
	 *
	 * @param parentPanel the parent panel
	 */
	public EditTaskInformationPanel(AbstractTaskPanel parentPanel) {
		this.parentPanel = parentPanel;
		this.buildLayout();
		setTask();
		this.setupListeners();
	}

	/**
	 * Sets the fields with info from the class's task object.
	 *
	 */
	public void setTask() {
		parentPanel.aTask.getTaskID();
		boxTitle.setText(parentPanel.aTask.getTitle());
		boxDescription.setText(parentPanel.aTask.getDescription());
		dropdownStatus.setSelectedItem(parentPanel.aTask.getStatus().toString());
		//requirement
		listChosenAssignees= parentPanel.aTask.getAssignedUsers();
		calStartDate.setDate(parentPanel.aTask.getStartDate());
		calDueDate.setDate(parentPanel.aTask.getDueDate());
		spinnerEstimatedEffort.setValue(parentPanel.aTask.getEstimatedEffort());
		spinnerActualEffort.setValue(parentPanel.aTask.getActualEffort());
	}	

	/**
	 * Sets up listeners for the validation fields.
	 */
	protected void setupListeners() {
		// Text Field Listeners
		boxTitle.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				parentPanel.buttonPanel.validateTaskInfo();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				parentPanel.buttonPanel.validateTaskInfo();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				parentPanel.buttonPanel.validateTaskInfo();
			}
		});
		
		 buttonAdd.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // if(!listPossibleAssignees.isSelectionEmpty()) {
	                // String[] a = listOfPossibleAssignees;
	                // String[] b = listOfChosenAssignees;
	                // int[] c = listPossibleAssignees.getSelectedIndices();
	                // String[] tempA = new String[a.length - c.length];
	                // String[] tempB = new String[b.length + c.length];
	                // for(int i = 0; i < b.length; i++) {
	                // tempB[i] = b[i];
	                // }
	                // int counterA = 0;
	                // int counterB = b.length;
	                // for(int i = 0; i < a.length; i++) {
	                // boolean canAdd = true;
	                // for(int x : c) {
	                // if(x == i) {
	                // tempB[counterB] = a[i];
	                // counterB++;
	                // }
	                // else {
	                // tempA[counterA] = a[i];
	                // counterA++;
	                // }
	                // }
	                // }
	                // listOfPossibleAssignees = tempA;
	                // listOfChosenAssignees = tempB;
	                // //Repaint the GUI
	                // listChosenAssignees.repaint();
	                // listPossibleAssignees.repaint();
	                // }
	            }
	        });

	        buttonRemove.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // if(!listChosenAssignees.isSelectionEmpty()) {
	                // String[] a = listOfChosenAssignees;
	                // String[] b = listOfPossibleAssignees;
	                // int[] c = listChosenAssignees.getSelectedIndices();
	                // String[] tempA = new String[a.length - c.length];
	                // String[] tempB = new String[b.length + c.length];
	                // for(int i = 0; i < b.length; i++) {
	                // tempB[i] = b[i];
	                // }
	                // int counterA = 0;
	                // int counterB = b.length;
	                // for(int i = 0; i < a.length; i++) {
	                // boolean canAdd = true;
	                // for(int x : c) {
	                // if(x == i) {
	                // tempB[counterB] = a[i];
	                // counterB++;
	                // }
	                // else {
	                // tempA[counterA] = a[i];
	                // counterA++;
	                // }
	                // }
	                // }
	                // listOfPossibleAssignees = tempB;
	                // listOfChosenAssignees = tempA;
	                // //Repaint the GUI
	                // listChosenAssignees.repaint();
	                // listPossibleAssignees.repaint();
	                // }
	            }

	        });


	        /**
	         * Text Field (Title) Listeners
	         */
	        boxTitle.getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                parentPanel.buttonPanel.validateTaskInfo();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                parentPanel.buttonPanel.validateTaskInfo();
	            }

	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                parentPanel.buttonPanel.validateTaskInfo();
	            }
	        });


	        /**
	         * Text Field (Description) Listeners
	         */
	        boxDescription.getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                parentPanel.buttonPanel.validateTaskInfo();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                parentPanel.buttonPanel.validateTaskInfo();
	            }

	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                parentPanel.buttonPanel.validateTaskInfo();
	            }
	        });
	}
}


