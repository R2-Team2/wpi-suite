/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;


import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RetrieveUsersController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 * The Class NewTaskInformationPanel.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
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
        // this.setMinimumSize(new Dimension(540, 200));

        buildLayout();
        new RetrieveUsersController(this).requestUsers();
        setupListeners();
    }

    /**
     * Sets up listeners for text validation and button presses.
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
                if (!possibleAssigneeList.isSelectionEmpty()) {
                    int[] toAdd = possibleAssigneeList.getSelectedIndices();
                    for (int i = toAdd.length - 1; i >= 0; i--) {
                        User transfer = possibleAssigneeModel.remove(toAdd[i]);
                        chosenAssigneeModel.add(chosenAssigneeModel.size(), transfer);
                    }
                    if (possibleAssigneeModel.size() == 0) {
                        buttonAdd.setEnabled(false);
                    }
                    if (chosenAssigneeModel.size() > 0) {
                        buttonRemove.setEnabled(true);
                    }
                }
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!chosenAssigneeList.isSelectionEmpty()) {
                    int[] toRemove = chosenAssigneeList.getSelectedIndices();
                    for (int i = toRemove.length - 1; i >= 0; i--) {
                        User transfer = chosenAssigneeModel.remove(toRemove[i]);
                        possibleAssigneeModel.add(possibleAssigneeModel.size(), transfer);
                    }
                    if (chosenAssigneeModel.size() == 0) {
                        buttonRemove.setEnabled(false);
                    }
                    if (possibleAssigneeModel.size() > 0) {
                        buttonAdd.setEnabled(true);
                    }
                }
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
