/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.DefaultToolbarView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.buttons.TaskButtonsPanel;


// TODO: Auto-generated Javadoc
/**
 * This is the toolbar for the Task Manager module.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class ToolbarView extends DefaultToolbarView {

	/** The new button. */
	TaskButtonsPanel newButton = new TaskButtonsPanel();

	/**
	 * Construct this view and prevent it from being moved.
	 */
	public ToolbarView() {

		// Prevent this toolbar from being moved
		setFloatable(false);
		addGroup(newButton);

	}

	/**
	 * Method getNewButton.
	 *
	 * @return TaskButtonsPanel
	 */
	public TaskButtonsPanel getReqButton() {
		return newButton;
	}

}
