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
// import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.CreateNewTaskPanel;


// TODO: Auto-generated Javadoc
/**
 * The Class WorkFlowSplitView.
 * 
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class WorkFlowSplitView extends JSplitPane {

    /** The split tabbed panel. */
    WorkFlowSplitTabbedPanel splitTabbedPanel;

    /**
     * Instantiates a new work flow split view.
     */
    public WorkFlowSplitView() {
        splitTabbedPanel = new WorkFlowSplitTabbedPanel(this);

        ViewEventController.getInstance().setSplitTabbedPanel(splitTabbedPanel);

        setLeftComponent(new JScrollPane(new WorkFlowView()));
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
    public void createNewTaskPanel() {
        splitTabbedPanel.addCreateTaskTab();
        // Sets the Right Component to its minimum size always
        setResizeWeight(1.0);
        setEnabled(false);
        setOneTouchExpandable(false);
        // this.setDividerLocation(.6);
        resetToPreferredSizes();
        setRightComponent(splitTabbedPanel);
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
