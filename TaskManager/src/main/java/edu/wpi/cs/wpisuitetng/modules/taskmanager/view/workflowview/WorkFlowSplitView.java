/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel;

/**
 * The Class WorkFlowSplitView.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class WorkFlowSplitView extends JSplitPane {

    /** The split tabbed panel. */
    WorkFlowSplitTabbedPanel workflowSplitTabbedPanel;
    protected WorkFlowView workflowObj;
    private boolean expanded;

    /**
     * Instantiates a new work flow split view.
     */
    public WorkFlowSplitView() {
        workflowSplitTabbedPanel = new WorkFlowSplitTabbedPanel(this);

        ViewEventController.getInstance().setSplitTabbedPanel(workflowSplitTabbedPanel);

        workflowObj = new WorkFlowView();

        setLeftComponent(workflowObj);
        setRightComponent(null);
        setDividerSize(0);
        expanded = false;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                hideCreateNewTaskPanel();
            }
        });

        setupListeners();
    }

    /**
     * Returns the workflowSplitTabbedPanel used by this WorkFlowSplitView
     *
     * @return WorkFlowSplitTabbedPanel
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
        resetToPreferredSizes();
        setRightComponent(workflowSplitTabbedPanel);
        expanded = true;
        updateCollapseButtonText();
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
        resetToPreferredSizes();
        setRightComponent(workflowSplitTabbedPanel);
        expanded = true;
        updateCollapseButtonText();
    }

    /**
     * Hide create new task panel.
     */
    public void hideCreateNewTaskPanel() {
        setRightComponent(null);
        setOneTouchExpandable(false);
        expanded = false;
        updateCollapseButtonText();
    }

    /**
     * Decides whether to expand or collapse side panel
     */
    public void movePanel() {
        if (expanded) {
            collapsePanel();
        }
        else if (!expanded) {
            expandPanel();
        }
        else {
            System.out.println("Side Panel is in an Unknown State (collapseSidePanel Listener)");
        }
    }

    /**
     * Update collapse/expand button text
     */
    public void updateCollapseButtonText() {
        if (workflowSplitTabbedPanel.getTabCount() > 0) {
            workflowObj.collapseSideButton.setEnabled(true);
        }
        else {
            workflowObj.collapseSideButton.setEnabled(false);
        }
        if (expanded) {
            workflowObj.collapseSideButton.setText(">");
        }
        else {
            workflowObj.collapseSideButton.setText("< " + workflowSplitTabbedPanel.getTabCount());
        }
    }

    /**
     * Expand task panel.
     */
    public void expandPanel() {
        resetToPreferredSizes();
        expanded = true;
        updateCollapseButtonText();
    }

    /**
     * Collapse task panel.
     */
    public void collapsePanel() {
        setOneTouchExpandable(false);
        this.setDividerLocation(1.0);
        expanded = false;
        updateCollapseButtonText();
    }

    /**
     * Setup Listeners
     */
    private void setupListeners() {
        workflowObj.collapseSideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel();
            }
        });
    }

}
