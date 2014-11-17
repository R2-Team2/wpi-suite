/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitView;

/**
 * This panel fills the main content area of the tab for this module. It is currently blank.
 */
@SuppressWarnings("serial")
public class MainView extends JTabbedPane {
    private final JPopupMenu popup = new JPopupMenu();
    private JMenuItem closeAll = new JMenuItem("Close All Tabs");
    private JMenuItem closeOthers = new JMenuItem("Close Others");
    private WorkFlowSplitView workflow = new WorkFlowSplitView();
    /**
     * Construct the panel. There is some test text inside the panel.
     */
    public MainView() {
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        this.closeAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ViewEventController.getInstance().closeAllTabs();
            }
        });

        this.closeOthers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ViewEventController.getInstance().closeOthers();
            }
        });

        this.popup.add(this.closeAll);
        this.popup.add(this.closeOthers);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger())
                    MainView.this.popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        this.addTab("Work Flow", null, workflow, "Work Flow");
    }
    
    public void showCreateTaskView(){
    	workflow.createNewTaskPanel();
    }

}
