/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

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
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel;
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
        this.addTab("New Task", null, new NewTaskPanel(this), null);

        final WorkFlowSplitTabbedPanel thisPane = this;

        // create a "cross" button
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
                for (int i = 0; i < thisPane.getTabCount(); i++) {

                    // get a panel of current tab component
                    JPanel tabPanel = (JPanel) thisPane.getTabComponentAt(i);

                    // take a button from it
                    tmpButton = (JButton) tabPanel.getComponent(1);

                    // retrieve its action command
                    String actualActionCommand = tmpButton.getActionCommand();

                    // if this command is equal to that of clicked button, then we've found our tab
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

            final JLabel lblTitle = new JLabel("New Task");
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
        }
    }

    
    public void addViewTaskTab(AbstractTaskPanel aPanel) {
        String title = aPanel.getTitle();
    	this.addTab(title, null, aPanel, null);

        final WorkFlowSplitTabbedPanel thisPane = this;

        // create a "cross" button
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
                for (int i = 0; i < thisPane.getTabCount(); i++) {

                    // get a panel of current tab component
                    JPanel tabPanel = (JPanel) thisPane.getTabComponentAt(i);

                    // take a button from it
                    tmpButton = (JButton) tabPanel.getComponent(1);

                    // retrieve its action command
                    String actualActionCommand = tmpButton.getActionCommand();

                    // if this command is equal to that of clicked button, then we've found our tab
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

            final JLabel lblTitle = new JLabel("New Task");
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
        }
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
