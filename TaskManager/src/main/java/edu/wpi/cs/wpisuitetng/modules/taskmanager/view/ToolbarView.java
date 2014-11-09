/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Chris Casola
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.JToolBar;


/**
 * This is the toolbar for the Task Manager module
 */
@SuppressWarnings("serial")
public class ToolbarView extends JToolBar {
    
    
    /**
     * Construct this view and prevent it from being moved
     */
    public ToolbarView() {
        
        // Prevent this toolbar from being moved
        setFloatable(false);
        
    }
    
}
