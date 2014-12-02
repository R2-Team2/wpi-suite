/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitTabbedPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractTaskPanel.
 * 
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public abstract class AbstractTaskPanel extends JPanel {

	/** The parent panel. */
	protected String title;

	/** The parent panel. */
	protected WorkFlowSplitTabbedPanel parentPanel;

	/** The info panel. */
	protected AbstractInformationPanel infoPanel;

	/** The button panel. */
	protected AbstractButtonPanel buttonPanel;

	/** A task. */
	protected Task aTask;

	/** The view event controller. */
	private final ViewEventController viewEventController = ViewEventController.getInstance();

	/**
	 * Instantiates a new abstract task panel.
	 */
	protected AbstractTaskPanel() {

	}

	/**
	 * @throws Exception invalid selected requirement
	 */
	protected void openSelectedRequirement() throws Exception {
		this.infoPanel.openSelectedRequirement();
	}

	/**
	 * Instantiates a new abstract task panel.
	 *
	 * @param parentPanel the parent panel
	 */
	protected AbstractTaskPanel(WorkFlowSplitTabbedPanel parentPanel) {
		this.parentPanel = parentPanel;
	}

	/**
	 * Creates the GUI for the NewTaskPanel.
	 */
	protected void buildLayout() {
		this.title = this.aTask.getTitle();
		this.setLayout(new BorderLayout());
		this.add(this.buttonPanel, BorderLayout.SOUTH);
		this.add(this.infoPanel, BorderLayout.CENTER);
	}

	/**
	 * Called when the Create Button is pressed Creates a Task from the NewTask Info.
	 */
	public abstract void createPressed();

	/**
	 * Called when the Cancel Button is pressed Closes out the NewTask Tab.
	 */
	public void cancelPressed() {
		ViewEventController.getInstance().removeSplitTab();
		this.parentPanel.checkForHide();
	}

	/**
	 * Returns the title information from infoPanel.
	 *
	 * @return String
	 */
	public String getTitle() {
		return this.infoPanel.getTitle().getText();
	}

	/**
	 * Returns the description information from infoPanel.
	 *
	 * @return String
	 */
	public String getDescription() {
		return this.infoPanel.getDescription().getText();
	}

	/**
	 * Retrieves the Estimated Effort from infoPanel.
	 *
	 * @return int
	 */
	public int getEstimatedEffort() {
		return (int) this.infoPanel.getEstimatedEffort().getValue();
	}

	/**
	 * Retrieves the Actual Effort from infoPanel.
	 *
	 * @return int
	 */
	public int getActualEffort() {
		return (int) this.infoPanel.getActualEffort().getValue();
	}

	/**
	 * Retrieves the Status from infoPanel.
	 *
	 * @return String
	 */
	public String getStatus() {
		return this.infoPanel.getStatus().getSelectedItem().toString();
	}

	/**
	 * Retrieves the Requirement from infoPanel.
	 *
	 * @return String
	 */
	public String getRequirement() {
		return (String) this.infoPanel.getRequirement().getSelectedItem();
	}

	/**
	 * Retrieves the StartDate from infoPanel.
	 *
	 * @return Date
	 */
	public Date getStartDate() {
		return this.infoPanel.getStartDate();
	}

	/**
	 * Retrieves the DueDate from infoPanel.
	 *
	 * @return Date
	 */
	public Date getDueDate() {
		return this.infoPanel.getDueDate();
	}

	/**
	 * Retrieves the Chosen Members from infoPanel.
	 *
	 * @return String[]
	 */
	public List<User> getAssignedUsers() {
		return this.infoPanel.getAssignedUsers();
	}

	/**
	 * Sets the info panel.
	 *
	 * @param aPanel the new info panel
	 */
	public void setInfoPanel(NewTaskInformationPanel aPanel) {
		this.infoPanel = aPanel;
	}

}
