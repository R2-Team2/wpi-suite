/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

/**
 * The Class NewTaskButtonPanel.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class NewTaskButtonPanel extends AbstractButtonPanel {
    // Class Variables

    // /** The button create. */
    // protected JButton buttonCreate;
    protected NewTaskPanel parentPanel;

    /**
     * Constructor for the NewTaskButtonPanel.
     *
     * @param parentPanel the parent panel
     */
    public NewTaskButtonPanel(NewTaskPanel parentPanel) {
        // Set Panel Layout
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Set Parent Panel
        this.parentPanel = parentPanel;
        // Set Button Messages
        final String createString = "Create";
        final String cancelString = "Cancel";
        // Create Buttons
        buttonCreate = new JButton(createString);
        buttonCancel = new JButton(cancelString);
        buttonCreate.setEnabled(false);
        this.add(buttonCreate);
        this.add(buttonCancel);
        setupListeners();
    }


    /**
     * Sets up listeners for the buttons in the new task panel.
     */
    protected void setupListeners() {
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    parentPanel.createPressed();
                } catch (WPISuiteException e1) {
                    e1.printStackTrace();
                }
                ViewEventController.getInstance().refreshWorkFlowView();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.cancelPressed();
            }

        });
    }

    /**
     * Validate task info.
     */
    @Override
    public boolean validateTaskInfo() {
        if (parentPanel.infoPanel.boxTitle.getText().trim().length() <= 0
                || parentPanel.infoPanel.boxDescription.getText().trim().length() <= 0
                || (!((String) parentPanel.infoPanel.dropdownStatus.getSelectedItem())
                        .equals("New") && parentPanel.infoPanel.listChosenAssignees.getModel()
                        .getSize() == 0)) {
            buttonCreate.setEnabled(false);
            return false;
        } else {
            buttonCreate.setEnabled(true);
            return true;
        }
    }

    /**
     * Validate task dates
     */
    @Override
    public void validateTaskDate() {
        if (parentPanel.infoPanel.getDueDate().before(parentPanel.infoPanel.getStartDate())) {
            parentPanel.infoPanel.labelDueDate
            .setText("<html>Due Date: <font color='CC0000'>Preceeds Start Date</font></html>");
        } else {
            parentPanel.infoPanel.labelDueDate.setText("Due Date: ");

        }
    }

}
