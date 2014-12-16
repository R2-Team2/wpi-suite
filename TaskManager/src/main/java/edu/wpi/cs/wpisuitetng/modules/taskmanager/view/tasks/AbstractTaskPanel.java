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
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitTabbedPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractTaskPanel.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
@SuppressWarnings("serial")
public class AbstractTaskPanel extends JPanel {

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

    /**
     * Instantiates a new abstract task panel.
     */
    protected AbstractTaskPanel() {

    }

    /**
     * Open selected requirement.
     *
     * @throws Exception invalid selected requirement
     */
    protected void openSelectedRequirement() throws Exception {
        infoPanel.openSelectedRequirement();
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
        title = aTask.getTitle();
        setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(infoPanel, BorderLayout.CENTER);
    }

    /**
     * Called when the Create Button is pressed Creates a Task from the NewTask Info.
     */
    public void createPressed() {};

    /**
     * Called when the Cancel Button is pressed Closes out the NewTask Tab.
     */
    public void cancelPressed() {
        ViewEventController.getInstance().removeSplitTab();
        parentPanel.checkForHide();
    }

    /**
     * Returns the title information from infoPanel.
     *
     * @return String
     */
    public String getTitle() {
        return infoPanel.getTitle().getText();
    }

    /**
     * Returns the description information from infoPanel.
     *
     * @return String
     */
    public String getDescription() {
        return infoPanel.getDescription().getText();
    }

    /**
     * Retrieves the Estimated Effort from infoPanel.
     *
     * @return int
     */
    public int getEstimatedEffort() {
        return (int) infoPanel.getEstimatedEffort().getValue();
    }

    /**
     * Retrieves the Actual Effort from infoPanel.
     *
     * @return int
     */
    public int getActualEffort() {
        return (int) infoPanel.getActualEffort().getValue();
    }

    /**
     * Retrieves the Status from infoPanel.
     *
     * @return String
     */
    public String getStatus() {
        return infoPanel.getStatus().getSelectedItem().toString();
    }

    /**
     * Retrieves the Requirement from infoPanel.
     *
     * @return int
     */
    public int getRequirement() {
        return ((Requirement) infoPanel.getRequirement().getSelectedItem()).getId();
    }

    /**
     * Retrieves the StartDate from infoPanel.
     *
     * @return Date
     */
    public Date getStartDate() {
        return infoPanel.getStartDate();
    }

    /**
     * Retrieves the DueDate from infoPanel.
     *
     * @return Date
     */
    public Date getDueDate() {
        return infoPanel.getDueDate();
    }

    /**
     * Retrieves the Chosen Members from infoPanel.
     *
     * @return String[]
     */
    public List<User> getAssignedUsers() {
        return infoPanel.getAssignedUsers();
    }

    /**
     * Sets the info panel.
     *
     * @param aPanel the new info panel
     */
    public void setInfoPanel(NewTaskInformationPanel aPanel) {
        infoPanel = aPanel;
    }
}
