/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RetrieveUsersController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;


/**
 * The Class NewTaskInformationPanel.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
@SuppressWarnings("serial")
public class NewTaskInformationPanel extends AbstractInformationPanel {

    /**
     * Constructor for NewTaskInformationPanel.
     *
     * @param parentPanel the parent panel
     */
    public NewTaskInformationPanel(AbstractTaskPanel parentPanel) {
        this.parentPanel = parentPanel;

        buildLayout();
        new RetrieveUsersController(possibleAssigneeModel).requestAllUsers();
        setupListeners();
    }

    /**
     * Sets up listeners for text validation.
     */
    protected void setupListeners() {

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!possibleAssigneeList.isSelectionEmpty()) {
                    final int[] toAdd = possibleAssigneeList.getSelectedIndices();
                    for (int i = toAdd.length - 1; i >= 0; i--) {
                        User transfer = possibleAssigneeModel.remove(toAdd[i]);
                        chosenAssigneeModel.add(chosenAssigneeModel.size(), transfer);
                    }
                    buttonAdd.setEnabled(false);
                }
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!chosenAssigneeList.isSelectionEmpty()) {
                    final int[] toRemove = chosenAssigneeList.getSelectedIndices();
                    for (int i = toRemove.length - 1; i >= 0; i--) {
                        User transfer = chosenAssigneeModel.remove(toRemove[i]);
                        possibleAssigneeModel.add(possibleAssigneeModel.size(), transfer);
                    }
                    buttonRemove.setEnabled(false);
                }
            }
        });

        /**
         * Text Field (Title) Listeners
         */
        boxTitle.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }
        });


        /**
         * Text Field (Description) Listeners
         */
        boxDescription.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }
        });

        /**
         * Start Calendar Listener
         */
        calStartDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.buttonPanel.validateTaskDate();
                parentPanel.buttonPanel.isTaskInfoValid();
            }
        });

        /**
         * Due Calendar Listener
         */
        calDueDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.buttonPanel.validateTaskDate();
                parentPanel.buttonPanel.isTaskInfoValid();
            }
        });

        buttonOpenRequirement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.infoPanel.openRequirement();
            }
        });

        /**
         * Chosen assignee list Listener
         */
        chosenAssigneeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }
        });

        /**
         * Possible assignee list Listener
         */
        possibleAssigneeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }
        });
    }

    @Override
    public Task getTask() {
        return parentPanel.aTask;
    }

    /**
     * Validate assignee buttons
     */
    @Override
    public void validateAssigneeButtons() {
        buttonAdd.setEnabled(!possibleAssigneeList.isSelectionEmpty());
        buttonRemove.setEnabled(!chosenAssigneeList.isSelectionEmpty());
    };
}
