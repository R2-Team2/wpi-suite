/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

/**
 * The Class ViewTaskInformationPanel.
 * 
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class ViewTaskInformationPanel extends AbstractInformationPanel {

    /**
     * Constructor for the ViewTaskButtonPanel.
     *
     * @param aParentPanel the parent panel
     */
    public ViewTaskInformationPanel(ViewTaskPanel aParentPanel) {
        parentPanel = aParentPanel;
        this.buildLayout();
        //this.disableAll(true);
        //setTask();
    }
    
    @Override
    public void buildLayout() {
        this.setMinimumSize(new Dimension(540, 200));
        // Set the Panel
        final ScrollablePanel contentPanel = new ScrollablePanel();
        contentPanel.setLayout(new MigLayout("", "20[]20", "shrink"));
        
        //Instantiate GUI Elements
        // Labels
        final Task viewTask = parentPanel.aTask;
        String taskTitle = viewTask.getTitle();
        final JLabel labelTitle = new JLabel("<html><b>" + taskTitle + "</b></html>");
        final JLabel labelDescription =
                new JLabel("<html>Description</html>");
        final JLabel labelDescrBody = new JLabel("<html>" + viewTask.getDescription() + "</html>");
        final JLabel labelStatus = new JLabel("Status: ");
        final JLabel labelEstimatedEffort = new JLabel("Estimated Effort: ");
        final JLabel labelActualEffort = new JLabel("Actual Effort: ");
        final JLabel labelDueDate = new JLabel("Due Date: ");
        final JLabel labelStartDate = new JLabel("Start Date: ");
        final JLabel labelRequirement = new JLabel("Requirement: ");
        final JLabel labelPossibleAssignee = new JLabel("Open Assignees: ");
        final JLabel labelChosenAssignee = new JLabel("Chosen Assignees: ");
        
        JPanel descrPanel = new JPanel();
        descrPanel.setLayout(new MigLayout("", "20[]20", "shrink"));
        descrPanel.add(labelDescription, "split 2, span");
        descrPanel.add(new JSeparator(SwingConstants.HORIZONTAL), "growx, span, wrap");
        
        //Populate ContentPanel
        contentPanel.add(labelTitle, "wrap");
        contentPanel.add(descrPanel, "growx, shrinkx, span, wrap");
        contentPanel.add(labelDescrBody, "wrap");

        this.setViewportView(contentPanel);
        
    }

    /**
     * Sets the fields from the class's task object.
     */
    public void setTask() {
        final Task viewTask = parentPanel.aTask;

        // viewTask.getTaskID();
        final String t = viewTask.getTitle();
        boxTitle.setText(t);
        boxDescription.setText(viewTask.getDescription());
        dropdownStatus.setSelectedItem(viewTask.getStatus().toString());
        // requirement
        listChosenAssignees = viewTask.getAssignedUsers();
        calStartDate.setDate(viewTask.getStartDate());
        calDueDate.setDate(viewTask.getDueDate());
        spinnerEstimatedEffort.setValue(viewTask.getEstimatedEffort());
        spinnerActualEffort.setValue(viewTask.getActualEffort());
    }

}
