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

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This panel fills the main content area of the tab for this module.
 * It is currently blank.
 */
@SuppressWarnings("serial")
public class MainView extends JPanel {
    
    
    /**
     * Construct the panel. There is some test text inside the panel.
     */
    public MainView() {
    	JLabel testLabel = new JLabel();
    	testLabel.setText("We're 80% done, Your Excellence...");
    	this.add(testLabel);
    	testLabel.setVisible(true);
    }
    
    
}
