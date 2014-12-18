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


// TODO: Auto-generated Javadoc
/**
 * The Class ViewTaskButtonPanel.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
@SuppressWarnings("serial")
public class ViewTaskButtonPanel extends AbstractButtonPanel {

    /** The parent panel. */
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
        final String archiveString = "Archive";

        // Create Buttons
        buttonEdit = new JButton(editString);
        buttonCancel = new JButton(cancelString);
        buttonArchive = new JButton(archiveString);
        this.add(buttonEdit);
        this.add(buttonCancel);

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
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.cancelPressed();
            }
        });

        buttonArchive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.archivePressed();
            }
        });
    }

    /*
     * (non-Javadoc)
     * @see
     * edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractButtonPanel#isTaskInfoValid()
     */
    @Override
    public boolean isTaskInfoValid() {
        throw new IllegalStateException(
                "ViewTaskButtonPanel.validateTaskInfo() should not be called");
    }

    /*
     * (non-Javadoc)
     * @see
     * edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractButtonPanel#validateTaskDate()
     */
    @Override
    public void validateTaskDate() {
        throw new IllegalStateException(
                "ViewTaskButtonPanel.validateTaskDate() should not be called");
    }

}
