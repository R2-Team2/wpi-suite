/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.settings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitTabbedPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class NewTaskPanel.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
@SuppressWarnings("serial")
public class WorkFlowEditView extends AbstractTaskPanel  {

    // private WorkFlowSplitTabbedPanel parentPanel;

    // private NewTaskInformationPanel infoPanel;
    // private NewTaskButtonPanel buttonPanel;

    // /** The view event controller. */
    // private final ViewEventController viewEventController = ViewEventController.getInstance();

    /** The default border. */
    protected final Border defaultBorder = BorderFactory.createEtchedBorder();

    /** Settings Panel Title. */
    protected JTextField title = new JTextField("Settings");

    /** The title font. */
    protected Font titleFont = new Font("SansSerif", Font.BOLD, 20);

    /** The task status button panel. */
    private final JPanel taskStatusButtonPanel = new JPanel(new MigLayout());

    /** The task status panel. */
    private final JPanel taskStatusPanel = new JPanel(new MigLayout());

    /** The add task status panel. */
    private final JPanel addTaskStatusPanel = new JPanel(new MigLayout());

    /** The label task status list. */
    final JLabel labelTaskStatusList = new JLabel("Available Task Statuses");



    // Task Status Settings Buttons
    /** The add task status button. */
    private final JButton addTaskStatusButton = new JButton("Add Task Status");

    /** The remove stage button. */
    private final JButton removeStageButton = new JButton("Remove Task Status");

    // Bottom Button Panel
    /** The bottom button panel. */
    private final JPanel bottomButtonPanel = new JPanel(new MigLayout());

    /** The save settings button. */
    private final JButton saveSettingsButton = new JButton("Save Settings");

    /** The cancel button. */
    private final JButton cancelButton = new JButton("Cancel");

    /** The new status title label. */
    private final JTextField newStatusTitleLabel = new JTextField("New Status Title");

    /** The new status title. */
    private JTextField newStatusTitle;

    /** The model. */
    private final DefaultListModel model = new DefaultListModel();

    /** The list. */
    private final JList list = new JList(model);

    /**
     * Constructor for the NewTaskPanel.
     * @param settingsSplitTabbedPanel 
     */
    public WorkFlowEditView(SettingsSplitTabbedPanel settingsSplitTabbedPanel) {
        this.buildLayout();


        // Query Database for Task Statuses

    }



    /**
	 * Description
	 *
	 */
    public WorkFlowEditView(WorkFlowSplitTabbedPanel parentPanel) {
        /*super(parentPanel);
        this.parentPanel = parentPanel;*/
        buildLayout();

    }
    
	public WorkFlowEditView() {
		// TODO Auto-generated constructor stub
	}
	


	/**
     * Creates the GUI for the NewTaskPanel.
     */
    protected void buildLayout() {

        // Add Settings for changing date format?

        // Build Bottom Button Panel
    	JScrollPane mainScrollPane=new JScrollPane();
        bottomButtonPanel.add(saveSettingsButton, "left");
        bottomButtonPanel.add(cancelButton, "left");

        this.buildEditWorkFlowView();
        this.add(title, BorderLayout.NORTH);
        this.add(taskStatusPanel, BorderLayout.CENTER);
        this.add(bottomButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * Builds the edit work flow view.
     */
    protected void buildEditWorkFlowView() {
        // Set Minimum Size of the Settings Panel
        this.setMinimumSize(new Dimension(540, 600));
        this.setLayout(new BorderLayout());

        newStatusTitleLabel.setBackground(getBackground());
        newStatusTitleLabel.setBorder(null);
        addTaskStatusPanel.add(newStatusTitleLabel, "center, wrap");
        newStatusTitle = new JTextField("");
        newStatusTitle.setMinimumSize(new Dimension(200, 15));
        addTaskStatusPanel.add(newStatusTitle, "center, wrap");
        addTaskStatusPanel.add(addTaskStatusButton, "center, wrap");

        list.setBorder(defaultBorder);
        list.setMinimumSize(new Dimension(200, 500));

        taskStatusPanel.add(addTaskStatusPanel, "center, wrap");
        taskStatusButtonPanel.add(removeStageButton, "center, wrap");


        taskStatusPanel.add(taskStatusButtonPanel, "center, wrap");
        taskStatusPanel.add(labelTaskStatusList, "center, wrap");
        taskStatusPanel.add(list, "left, width 200px, height 150px, wrap");
        taskStatusPanel.setBorder(defaultBorder);

        // Construct the Settings Title
        title.setBorder(null);
        title.setBackground(getBackground());
        title.setFont(titleFont);
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    }

    /**
     * Sets up the listeners for the buttons in the New Task Information Panel.
     */
    protected void setupListeners() {
        // Text Field Listeners
        newStatusTitle.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                // parentPanel.buttonPanel.validateTaskInfo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // parentPanel.buttonPanel.validateTaskInfo();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                // parentPanel.buttonPanel.validateTaskInfo();
            }
        });

        addTaskStatusButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent arg0) {


            }
        });
    }

	/*
	 * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel#createPressed()
	 */
	@Override
	public void createPressed() {
		// TODO Auto-generated method stub
		
	}
}
