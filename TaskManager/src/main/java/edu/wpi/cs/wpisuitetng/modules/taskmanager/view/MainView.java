/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel;
// import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.settings.SettingsSplitTabbedPanel;
// import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.settings.SettingsSplitView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitView;

/**
 * This panel fills the main content area of the tab for this module. It is currently blank.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
@SuppressWarnings("serial")
public class MainView extends JTabbedPane {

    /** The popup. */
    private final JPopupMenu popup = new JPopupMenu();

    /** The close all. */
    private final JMenuItem closeAll = new JMenuItem("Close All Tabs");

    /** The close others. */
    private final JMenuItem closeOthers = new JMenuItem("Close Others");

    /** The workflow. */
    private final WorkFlowSplitView workflow = new WorkFlowSplitView();

    /**
     * Construct the panel. There is some test text inside the panel.
     */
    public MainView() {
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        popup.add(closeAll);
        popup.add(closeOthers);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        this.addTab("Work Flow", null, workflow, null);
    }

    public WorkFlowSplitView getWF() {
        return workflow;
    }


    /**
     * Show create task view.
     */
    public void showCreateTaskView() {
        workflow.createNewTaskPanel();
        setSelectedComponent(workflow);
    }

    /**
     * Show view task view.
     *
     * @param aPanel is the panel to be created.
     */
    public void showViewTaskView(AbstractTaskPanel aPanel) {
        workflow.createViewTaskPanel(aPanel);
    }

    /**
     * Hide create task view.
     */
    public void hideCreateTaskView() {
        workflow.hideCreateNewTaskPanel();
    }

    /**
     * Check for hide.
     */
    public void checkForHide() {
        if (getTabCount() <= 0) {
            ViewEventController.getInstance().closeNewTaskPanel();
        }
    }

    /**
     * resets workflow size refreshing its contents
     */
    public void resetPreferedSize() {
        workflow.resetToPreferredSizes();
    }

}
