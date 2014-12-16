/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import javax.swing.JButton;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractButtonPanel.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
@SuppressWarnings("serial")
public class AbstractButtonPanel extends JPanel {
    // Class Variables
    /** The button save. */
    protected JButton buttonSave;

    /** The button create. */
    protected JButton buttonCreate;

    /** The button cancel. */
    protected JButton buttonCancel;

    /** The button edit. */
    protected JButton buttonEdit;

    /**
     * Sets up the listeners for the buttons in the New Task Button Panel.
     *
     * @return boolean
     */
    /*
     * protected void setupListeners() { buttonCancel.addActionListener(new ActionListener() {
     * @Override public void actionPerformed(ActionEvent e) { parentPanel.cancelPressed(); } }); }
     */

    /**
     * Validate task title and description
     *
     * @return boolean
     */
    public boolean isTaskInfoValid() {
        throw new IllegalStateException(
                "AbstractButtonPanel.validateTaskInfo() should not be called");
    };

    /**
     * Validate task dates.
     */
    public void validateTaskDate() {};

    /**
     * Are dates valid.
     *
     * @return true, if successful
     */
    public boolean areDatesValid() {
        return true;
    };

}
