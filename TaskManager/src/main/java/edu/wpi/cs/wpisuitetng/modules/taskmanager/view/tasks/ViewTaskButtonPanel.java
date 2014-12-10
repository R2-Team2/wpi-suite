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


/**
 * The Class ViewTaskButtonPanel.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class ViewTaskButtonPanel extends AbstractButtonPanel {
    protected ViewTaskPanel parentPanel;

    /**
     * Constructor for the ViewTaskButtonPanel.
     *
     * @param parentPanel the parent panel.
     */
    public ViewTaskButtonPanel(ViewTaskPanel parentPanel) {
        // Set Panel Layout
        setLayout(new FlowLayout(FlowLayout.LEFT));
        // Set Parent Panel
        this.parentPanel = parentPanel;

        // Set Button Messages
        final String editString = "Edit";
        final String cancelString = "Cancel";

        // Create Buttons
        buttonEdit = new JButton(editString);
        buttonCancel = new JButton(cancelString);
        this.add(buttonEdit);
        this.add(buttonCancel);
        // super.setupListeners();
        setupListeners();
    }

    /**
     * Sets the listeners for the View task buttons.
     */
    protected void setupListeners() {
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.editPressed();
                System.out.println("edit pressed");
                // TODO put it to the right place

            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.cancelPressed();
            }
        });
    }

    @Override
    public boolean isTaskInfoValid() {
        throw new IllegalStateException(
                "ViewTaskButtonPanel.validateTaskInfo() should not be called");
    }

    @Override
    public void validateTaskDate() {
        throw new IllegalStateException(
                "ViewTaskButtonPanel.validateTaskDate() should not be called");
    }

}
