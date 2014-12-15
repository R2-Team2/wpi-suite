/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.settings.SettingsSplitTabbedPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel;

/**
 * The Class WorkFlowSplitView.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class WorkFlowSplitView extends JSplitPane {

    /** The split tabbed panel. */
    WorkFlowSplitTabbedPanel workflowSplitTabbedPanel;
    private SettingsSplitTabbedPanel settingsSplitTabbedPanel;

    /**
     * Instantiates a new work flow split view.
     */
    public WorkFlowSplitView() {
        workflowSplitTabbedPanel = new WorkFlowSplitTabbedPanel(this);

        ViewEventController.getInstance().setSplitTabbedPanel(workflowSplitTabbedPanel);

        setLeftComponent(new JScrollPane(WorkFlowView.getInstance()));
        setRightComponent(null);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                hideCreateNewTaskPanel();
            }
        });

    }

    /**
     * Creates the new task panel.
     */
    public WorkFlowSplitTabbedPanel getWF() {
        return workflowSplitTabbedPanel;
    }

    /**
     * Creates the new task panel.
     */
    public void createNewTaskPanel() {
        workflowSplitTabbedPanel.addCreateTaskTab();
        // Sets the Right Component to its minimum size always
        setResizeWeight(1.0);
        setEnabled(false);
        setOneTouchExpandable(false);
        // this.setDividerLocation(.6);
        resetToPreferredSizes();
        setRightComponent(workflowSplitTabbedPanel);
    }

    /**
     * Creates the view task panel.
     *
     * @param viewPanel is the panel to display in the created view panel
     */
    public void createViewTaskPanel(AbstractTaskPanel viewPanel) {
        workflowSplitTabbedPanel.addViewTaskTab(viewPanel);
        // Sets the Right Component to its minimum size always
        setResizeWeight(1.0);
        setEnabled(false);
        setOneTouchExpandable(false);
        // this.setDividerLocation(.6);
        resetToPreferredSizes();
        setRightComponent(workflowSplitTabbedPanel);
    }


    /**
     * Hide create new task panel.
     */
    public void hideCreateNewTaskPanel() {
        setRightComponent(null);
        setOneTouchExpandable(false);
        // this.setDividerLocation(0.0);
    }

    /**
     * Collapse.
     */
    public void collapse() {
        setOneTouchExpandable(true);
        this.setDividerLocation(1.0);
    }

}
