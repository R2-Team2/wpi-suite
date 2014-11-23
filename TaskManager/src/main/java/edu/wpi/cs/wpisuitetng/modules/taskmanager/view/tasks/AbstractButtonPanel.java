/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractButtonPanel.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */

public class AbstractButtonPanel extends JPanel {
    // Class Variables
    /** The parent panel. */
    protected AbstractTaskPanel parentPanel;

    /** The button left. */
    protected JButton buttonLeft;

    /** The button cancel. */
    protected JButton buttonCancel;

    /*
	protected JButton buttonSave;
	protected JButton buttonCreate;
	*/

  

    /**
     * Validate task info.
     */
    public void validateTaskInfo() {
        if (parentPanel.infoPanel.boxTitle.getText().length() <= 0) {
            buttonLeft.setEnabled(false);
        }
        else {
            buttonLeft.setEnabled(true);
        }
    }

}
