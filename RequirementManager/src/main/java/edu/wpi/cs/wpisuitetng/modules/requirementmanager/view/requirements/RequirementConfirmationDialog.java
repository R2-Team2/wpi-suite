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

public interface RequirementConfirmationDialog {

    /**
     * Creates a confirmation prompt
     * 
     * @return true if Yes was pressed, false if No was pressed
     */
    boolean confirmExit(Component component);
}
