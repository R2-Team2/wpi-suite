/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	Team R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.BorderLayout;



// TODO: Auto-generated Javadoc
/**
 * The Class EditTaskPanel.
 */
public class EditTaskPanel extends AbstractTaskPanel {
		
		/**
		 * Constructor for the NewTaskPanel.
		 */
		public EditTaskPanel() {
			this.buildLayout();
		}
		
		/**
		 * Creates the GUI for the NewTaskPanel.
		 */
		protected void buildLayout() {
	        buttonPanel = new EditTaskButtonPanel(this);
	        infoPanel = new EditTaskInformationPanel(this);
	        
	        this.setLayout(new BorderLayout());
	        this.add(infoPanel, BorderLayout.CENTER);
	        this.add(buttonPanel, BorderLayout.SOUTH);
		}

		/* (non-Javadoc)
		 * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel#createPressed()
		 */
		@Override
		public void createPressed() {
			// TODO Auto-generated method stub
			
		}
}
