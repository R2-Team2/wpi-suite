/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.settings.WorkFlowEditView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowView;

// TODO: Auto-generated Javadoc
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
    
    /** The Settings view */
    private final JScrollPane workFlowEditView = new JScrollPane(new WorkFlowEditView());
    private int tabCounter = 0;


    /**
     * Construct the panel. There is some test text inside the panel.
     */
    public MainView() {
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

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
                    MainView.this.popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        this.addTab("Work Flow", null, workflow, null);
    }

    /**
     * Show create task view.
     */
    public void showCreateTaskView() {
        workflow.createNewTaskPanel();
        this.setSelectedComponent(workflow);
    }

    /**
     * Edits the work flow view.
     */
    public void editWorkFlowView() {
        if (!isTabAlreadyOpen(workFlowEditView)) {
            this.addTab("Edit Work Flow", null, workFlowEditView, null);

            final MainView thisPane = this;

            // create a "close" button
            final JButton tabCloseButton = new JButton("\u2716");
            tabCloseButton.setActionCommand("" + this.getTabCount());
            tabCloseButton.setFont(tabCloseButton.getFont().deriveFont((float) 8));
            tabCloseButton.setMargin(new Insets(0, 0, 0, 0));

            final ActionListener closeButtonListener;
            closeButtonListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent action) {

                    // get button which was clicked
                    JButton tmpButton = (JButton) action.getSource();

                    // get previously defined action command (sort of identifier)
                    final String clickedActionCommand = tmpButton.getActionCommand();

                    // for all tabs in tabpane
                    for (int i = 1; i < thisPane.getTabCount(); i++) {

                        // get a panel of current tab component
                        JPanel tabPanel = (JPanel) thisPane.getTabComponentAt(i);

                        // take a button from it
                        tmpButton = (JButton) tabPanel.getComponent(i);

                        // retrieve its action command
                        String actualActionCommand = tmpButton.getActionCommand();

                        // if this command is equal to that of clicked button, then we've found our
                        // tab
                        if (clickedActionCommand.equals(actualActionCommand)) {
                            thisPane.removeTabAt(i); // and we remove it
                            thisPane.checkForHide();
                            break;
                        }
                    }
                }
            };
            tabCloseButton.addActionListener(closeButtonListener);

            // this part of code manually creates a panel with title and button
            // and adds it to tab component
            if (this.getTabCount() != 0) {
                final JPanel panel = new JPanel();
                panel.setOpaque(false);

                final JLabel lblTitle = new JLabel("Edit Work Flow");
                lblTitle.setBorder(BorderFactory.createEmptyBorder(3, 0, 2, 7));

                final GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1;

                panel.add(lblTitle, gbc);

                gbc.gridx++;
                gbc.weightx = 0;
                panel.add(tabCloseButton, gbc);


                this.setTabComponentAt(this.getTabCount() - 1, panel);
                this.setSelectedIndex(this.getTabCount() - 1);
                this.setSelectedComponent(workFlowEditView);
            }
        }
    }

    /**
     * Tab already open.
     *
     * @param componentOpen the component open
     * @return true, if successful
     */
    private boolean isTabAlreadyOpen(Component componentOpen) {
        boolean ret = false;
        for (int i = 1; i < this.getTabCount(); i++) {
            if (componentOpen.equals(this.getTabComponentAt(i))) {
                ret = true;
            }
        }
        return ret;
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
        if (this.getTabCount() <= 0) {
            ViewEventController.getInstance().closeNewTaskPanel();
        }
    }


}
