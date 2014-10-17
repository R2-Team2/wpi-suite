/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Robert Smieja
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * Displays a pop-up dialog box that confirms that the user wants to discard unsaved changes
 * 
 * @author Robert Smieja
 */
public class RequirementConfirmationDialog {

    /**
     * Displays a pop-up dialog box that confirms that the user wants to discard unsaved changes
     * 
     * @param component Which component should display the dialog
     * @return True if Yes was selected, False if No was selected
     */
    public boolean confirmExit(Component component) {
        int result = JOptionPane.showConfirmDialog(component, "Discard unsaved changes and close tab?", "Discard Changes?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        return result == 0;
    }
}
