/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

/**
 * The Class ViewTaskInformationPanel.
 * 
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class ViewTaskInformationPanel extends AbstractInformationPanel {

    /**
     * Constructor for the ViewTaskButtonPanel.
     *
     * @param aParentPanel the parent panel
     */
    public ViewTaskInformationPanel(ViewTaskPanel aParentPanel) {
        parentPanel = aParentPanel;
        this.buildLayout();
        this.disableAll(true);
        setTask();
    }

    /**
     * Sets the fields from the class's task object.
     */
    public void setTask() {
        final Task viewTask = parentPanel.aTask;

        // viewTask.getTaskID();
        final String t = viewTask.getTitle();
        boxTitle.setText(t);
        boxDescription.setText(viewTask.getDescription());
        dropdownStatus.setSelectedItem(viewTask.getStatus().toString());
        // requirement
        listChosenAssignees = viewTask.getAssignedUsers();
        calStartDate.setDate(viewTask.getStartDate());
        calDueDate.setDate(viewTask.getDueDate());
        spinnerEstimatedEffort.setValue(viewTask.getEstimatedEffort());
        spinnerActualEffort.setValue(viewTask.getActualEffort());
    }

	@Override
	public Task getTask() {
		// TODO Auto-generated method stub
		return null;
	}

}
