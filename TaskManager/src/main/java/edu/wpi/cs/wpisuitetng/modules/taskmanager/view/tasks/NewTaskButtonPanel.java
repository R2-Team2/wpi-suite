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

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

// TODO: Auto-generated Javadoc
/**
 * The Class NewTaskButtonPanel.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
@SuppressWarnings("serial")
public class NewTaskButtonPanel extends AbstractButtonPanel {

    /** The parent panel. */
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
                parentPanel.createPressed();
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
     *
     * @return true, if is task info valid
     */
    @Override
    public boolean isTaskInfoValid() {
        boolean result = false;
        if (parentPanel.infoPanel.boxTitle.getText().trim().length() <= 0
                || parentPanel.infoPanel.boxDescription.getText().trim().length() <= 0
                || !(areDatesValid())
                || (!((String) parentPanel.infoPanel.dropdownStatus.getSelectedItem())
                        .equals("New") && parentPanel.infoPanel.chosenAssigneeList.getModel()
                        .getSize() == 0) || parentPanel.infoPanel.calDueDate.getDate() == null) {
            buttonCreate.setEnabled(false);
            result = false;
        } else {
            buttonCreate.setEnabled(true);
            result = true;
        }

        parentPanel.infoPanel.validateAssigneeButtons();

        return result;
    }

    /**
     * Validate task dates.
     */
    @Override
    public void validateTaskDate() {
        if (parentPanel.infoPanel.getDueDate() != null
                && parentPanel.infoPanel.getStartDate() != null) {
            if (parentPanel.infoPanel.getDueDate().before(parentPanel.infoPanel.getStartDate())) {
                parentPanel.infoPanel.labelDueDate
                        .setText("<html>Due Date: <font color='CC0000'>"
                                + "Preceeds Start Date</font></html>");
            } else {
                parentPanel.infoPanel.labelDueDate.setText("Due Date: ");
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractButtonPanel#areDatesValid()
     */
    @Override
    public boolean areDatesValid() {
        if (parentPanel.infoPanel.getDueDate() != null
                && parentPanel.infoPanel.getStartDate() != null) {
            return (parentPanel.infoPanel.getDueDate().after(parentPanel.infoPanel.getStartDate()));
        }
        else {
            return true;
        }
    }

}
