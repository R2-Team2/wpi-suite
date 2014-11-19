/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.NewTaskPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkFlowSplitTabbedPanel.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class WorkFlowSplitTabbedPanel extends JTabbedPane {

    /** The popup. */
    private final JPopupMenu popup = new JPopupMenu();

    /** The close all. */
    private final JMenuItem closeAll = new JMenuItem("Close All Tabs");

    /** The close others. */
    private final JMenuItem closeOthers = new JMenuItem("Close Others");

    /** The parent panel. */
    private final WorkFlowSplitView parentPanel;


    /**
     * Instantiates a new work flow split tabbed panel.
     *
     * @param parentPanel the parent panel
     */
    public WorkFlowSplitTabbedPanel(WorkFlowSplitView parentPanel) {
        this.parentPanel = parentPanel;

        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        // this.setMaximumSize(new Dimension(100,100));
        closeAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ViewEventController.getInstance().closeAllTabs();
            }
        });

        closeOthers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ViewEventController.getInstance().closeOthers();
            }
        });

        popup.add(closeAll);
        popup.add(closeOthers);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    WorkFlowSplitTabbedPanel.this.popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

    }

    /**
     * Adds the create task tab.
     */
    public void addCreateTaskTab() {
        this.addTab("New Task", null, new NewTaskPanel(this), "New Task");
    }

    /**
     * Check for hide.
     */
    public void checkForHide() {
        if (this.getTabCount() <= 0) {
            ViewEventController.getInstance().closeNewTaskPanel();
        }
    }
}
