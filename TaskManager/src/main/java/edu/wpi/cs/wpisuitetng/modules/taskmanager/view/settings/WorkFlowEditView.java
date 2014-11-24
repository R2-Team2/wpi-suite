/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.settings;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitTabbedPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class NewTaskPanel.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class WorkFlowEditView extends JPanel{

    // private WorkFlowSplitTabbedPanel parentPanel;

    // private NewTaskInformationPanel infoPanel;
    // private NewTaskButtonPanel buttonPanel;

    ///** The view event controller. */
	// private final ViewEventController viewEventController = ViewEventController.getInstance();

    /** The default border. */
    protected final Border defaultBorder = BorderFactory.createEtchedBorder();
    
    /** Settings Panel Title. */
    protected JTextField title = new JTextField("Edit Work Flow");
    protected Font titleFont = new Font("SansSerif", Font.BOLD, 30);
    
    private final JPanel taskStatusButtonPanel = new JPanel(new MigLayout());
    private final JPanel taskStatusPanel = new JPanel(new MigLayout());
	private final JPanel addTaskStatusPanel = new JPanel(new MigLayout());
	final JLabel labelTaskStatusList = new JLabel("Available Task Statuses");
    

    
    // Task Status Settings Buttons
    private final JButton addTaskStatusButton = new JButton("Add Task Status");
    private final JButton removeStageButton = new JButton("Remove Task Status");
    
    // Bottom Button Panel
    private final JPanel bottomButtonPanel = new JPanel(new MigLayout());
    private final JButton saveSettingsButton = new JButton("Save Settings");
    private final JButton cancelButton = new JButton("Cancel");

    private final JTextField newStatusTitleLabel = new JTextField("New Status Title");
	private JTextField newStatusTitle;
	
	private DefaultListModel model = new DefaultListModel();
	private JList list = new JList(model);
    
    /**
     * Constructor for the NewTaskPanel.
     */
    public WorkFlowEditView() {
        this.buildLayout();
        
        
        //Query Database for Task Statuses
        
    }
    
    public WorkFlowEditView(SettingsSplitTabbedPanel parentPanel) {
        
    	
    	this.buildLayout();

    }

    /**
     * Creates the GUI for the NewTaskPanel.
     */
    protected void buildLayout() {

    	// Add Settings for changing date format?
        
    	// Build Bottom Button Panel
    	bottomButtonPanel.add(saveSettingsButton, "left");
    	bottomButtonPanel.add(cancelButton,"left");
    	
    	this.buildEditWorkFlowView();
        this.add(title, BorderLayout.NORTH);
        this.add(taskStatusPanel, BorderLayout.CENTER);
        this.add(bottomButtonPanel, BorderLayout.SOUTH);
    }
    
    protected void buildEditWorkFlowView(){
    	// Set Minimum Size of the Settings Panel
    	this.setMinimumSize(new Dimension(1000, 1000));
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
        title.setHorizontalAlignment(JLabel.CENTER);
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
                //parentPanel.buttonPanel.validateTaskInfo();
            }
        });
    	
    	addTaskStatusButton.addActionListener(new ActionListener() {
    		
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
    }
}
