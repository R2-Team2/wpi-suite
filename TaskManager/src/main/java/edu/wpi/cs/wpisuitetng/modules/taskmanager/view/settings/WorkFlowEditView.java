/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
// this class has depricated through task 24- create collapsing sidebar.
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.settings;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.AbsWorkFlowView;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkFlowView.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class WorkFlowEditView extends AbsWorkFlowView {

	private JButton addButton = new JButton("Add Status");
	private JButton removeButton = new JButton("Remove Status");
	private JButton saveButton = new JButton("Save");
    /**
     * Create the panel.
     */
    public WorkFlowEditView() {
    	super();
    	JPanel sidePanel = new JPanel(new MigLayout());
        sidePanel.setMinimumSize(new Dimension(100,1000));
        sidePanel.add(addButton, "wrap");
        sidePanel.add(removeButton,"wrap");
        sidePanel.add(saveButton,"wrap");
        
        taskStatusPanel.add(sidePanel, "dock east");
        setupListeners();
    }

    /**
     * Gets the work flow obj.
     *
     * @return the work flow obj
     */
    public WorkFlow getWorkFlowObj() {
        return super.getWorkFlowObj();
    }

    /**
     * Sets the work flow obj.
     *
     * @param workFlowObj the new work flow obj
     */
    public void setWorkFlowObj(WorkFlow workFlowObj) {
        super.setWorkFlowObj(workFlowObj);
    }

    /**
     * Refresh.
     */
    public void refresh() {
        super.refresh();
    }
    protected void setupListeners() {
    	addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	addTaskStatusView(new TaskStatusView("New Title", "New Name"));
            }
        });

    	removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });
    }
}
