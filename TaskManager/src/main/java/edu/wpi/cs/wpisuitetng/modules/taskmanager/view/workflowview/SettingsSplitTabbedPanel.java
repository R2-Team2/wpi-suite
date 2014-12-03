/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;
//package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

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
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.settings.WorkFlowEditView;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkFlowSplitTabbedPanel.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
public class SettingsSplitTabbedPanel extends JSplitPane {

    /** The popup. */
    private final JPopupMenu popup = new JPopupMenu();

    /** The close all. */
    private final JMenuItem closeAll = new JMenuItem("Close All Tabs");

    /** The close others. */
    private final JMenuItem closeOthers = new JMenuItem("Close Others");

    /** The parent panel. */
    private WorkFlowSplitView parentPanel;

    /** The workflow edit view. */
    private WorkFlowEditView workflowEditView;
    
    private SettingsSplitTabbedPanel settingsSplitTabbedPanel;

	public SettingsSplitTabbedPanel() {
		settingsSplitTabbedPanel = new SettingsSplitTabbedPanel();

        ViewEventController.getInstance().setSettingsSplitTabbedPanel(settingsSplitTabbedPanel);
		
		this.setLeftComponent(new JScrollPane(new WorkFlowView()));
		this.setRightComponent(null);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
        });
	}
    

    /**
     * Instantiates a new work flow split tabbed panel.
     *
     * @param parentPanel the parent panel
     */
    public SettingsSplitTabbedPanel(WorkFlowSplitView parentPanel) {
        this.parentPanel = parentPanel;
        workflowEditView = new WorkFlowEditView();
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
                    SettingsSplitTabbedPanel.this.popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

    }

    /**
	 * Description goes here.
	 *
	 * @param scrollTabLayout
	 */
	private void setTabLayoutPolicy(int scrollTabLayout) {
		// TODO Auto-generated method stub
		
	}


	/**
     * Adds the create task tab.
     */
    public void addSettingsTab() {
        this.addTab("Settings", null, new WorkFlowEditView(this), null);

        final SettingsSplitTabbedPanel thisPane = this;

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
	 * Description goes here.
	 *
	 * @param i
	 * @param panel
	 */
	private void setTabComponentAt(int i, JPanel panel) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Description goes here.
	 *
	 * @param i
	 */
	private void setSelectedIndex(int i) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Description goes here.
	 *
	 * @param i
	 * @return
	 */
	protected JPanel getTabComponentAt(int i) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Description goes here.
	 *
	 * @return
	 */
	private int getTabCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	/**
	 * Description goes here.
	 *
	 * @param string
	 * @param object
	 * @param workFlowEditView2
	 * @param object2
	 */
	private void addTab(String string, Object object,
			WorkFlowEditView workFlowEditView2, Object object2) {
		// TODO Auto-generated method stub
		
	}


	/**
     * Check for hide.
     */
    public void checkForHide() {
        if (this.getTabCount() <= 0) {
            ViewEventController.getInstance().closeNewTaskPanel();
        }
    }


	/**
	 * Description goes here.
	 *
	 * @return
	 */
	public Object getSelectedIndex() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Description goes here.
	 *
	 * @param selectedIndex
	 */
	public void removeTabAt(Object selectedIndex) {
		// TODO Auto-generated method stub
		
	}

}
