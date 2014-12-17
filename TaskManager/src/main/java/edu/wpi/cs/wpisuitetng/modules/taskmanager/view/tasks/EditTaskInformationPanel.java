/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RetrieveUsersController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;

/**
 * The Class EditTaskInformationPanel.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
@SuppressWarnings("serial")
public class EditTaskInformationPanel extends AbstractInformationPanel {

    /**
     * Constructor for NewTaskInformationPanel.
     *
     * @param parentPanel the parent panel
     */
    public EditTaskInformationPanel(AbstractTaskPanel parentPanel) {
        this.parentPanel = parentPanel;
        buildLayout();
        setupTask();
        new RetrieveUsersController(possibleAssigneeModel, parentPanel.aTask.getAssignedUsers())
        .requestAllUsers();
        setupListeners();
    }

    /**
     * Sets the fields with info from the class's task object.
     */
    public void setupTask() {
        parentPanel.aTask.getTaskID();
        boxTitle.setText(parentPanel.aTask.getTitle());
        boxDescription.setText(parentPanel.aTask.getDescription());
        dropdownStatus.setSelectedItem(parentPanel.aTask.getStatus().toString());
        dropdownRequirement.setSelectedItem(parentPanel.aTask.getRequirement().toString());
        for (String username : parentPanel.aTask.getAssignedUsers()) {
            new RetrieveUsersController(chosenAssigneeModel).requestUser(username);
        }
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
                    buttonAdd.setEnabled(false);
                }
            }

        });

        buttonOpenRequirement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.infoPanel.openRequirement();
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
         * Status combo-box Listener
         */
        dropdownStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }
        });

        /**
         * Requirement drop-down Listener
         */
        dropdownRequirement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }
        });

        /**
         * Actual effort spinner Listener
         */
        spinnerActualEffort.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }
        });

        /**
         * Estimated effort spinner Listener
         */
        spinnerEstimatedEffort.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
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

        /**
         * Add assignee button Listener
         */
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }
        });

        /**
         * Remove assignee button Listener
         */
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.buttonPanel.isTaskInfoValid();
            }
        });
    }

    @Override
    public Task getTask() {
        final long id = parentPanel.aTask.getTaskID();
        final String title = getTitle().getText();
        final String description = getDescription().getText();
        final int estimatedEffort = (int) getEstimatedEffort().getValue();
        final int actualEffort = (int) getActualEffort().getValue();
        final TaskStatus status = (new TaskStatus(getStatus().getSelectedItem().toString()));
        final String requirement = getRequirement().getSelectedItem().toString();
        final Date startDate = getStartDate();
        final Date dueDate = getDueDate();
        final List<String> assignedUsers = new ArrayList<String>();
        final String priority = getPriority().toString();
        for (User u : getAssignedUsers()) {
            assignedUsers.add(u.getUsername());
        }
        final List<String> activityList = parentPanel.aTask.getActivityList();
        // Code inspired by mkyong
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy");
        final Date date = new Date();
        final String user = ConfigManager.getConfig().getUserName();
        String createActivity = "Updated Task at " + dateFormat.format(date) + " (by " + user + ")";
        if (status.equals(new TaskStatus("archived"))) {
            createActivity = "Archived Task at " + dateFormat.format(date) + " (by " + user + ")";
        }
        final Task updatedTask;
        updatedTask =
                new Task(id, title, description, estimatedEffort, actualEffort, status,
                        requirement, startDate, dueDate, assignedUsers, activityList, priority);
        updatedTask.addActivity(createActivity); // add activity entry to activity list
        return updatedTask;
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
