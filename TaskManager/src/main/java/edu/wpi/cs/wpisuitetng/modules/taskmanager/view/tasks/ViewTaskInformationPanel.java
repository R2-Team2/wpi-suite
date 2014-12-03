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
	 * @param aParentPanel
	 *            the parent panel
	 */
	public ViewTaskInformationPanel(ViewTaskPanel aParentPanel) {
		parentPanel = aParentPanel;
		this.buildLayout();
		// this.disableAll(true);
		// setTask();
	}

	@Override
	public void buildLayout() {
		this.setMinimumSize(new Dimension(540, 200));
		// Set the Panel
		final ScrollablePanel contentPanel = new ScrollablePanel();
		contentPanel.setLayout(new MigLayout("", "[grow,fill]",
				"[][][]5[]30[][]5[grow]30[][]5[grow]30[][]5[]"));

		// Instantiate GUI Elements
		// Labels
		final Task viewTask = parentPanel.aTask;
		// String taskTitle = viewTask.getTitle();
		final JLabel labelTitle = new JLabel("<html><h1>" + viewTask.getTitle()
				+ "</h1></html>");
		final JLabel labelDescr = new JLabel(
				"<html><h3>Description</h3></html>");
		final JLabel labelDescrBody = new JLabel("<html>"
				+ viewTask.getDescription() + "</html>");
		final JLabel labelDetails = new JLabel("<html><h3>Details</h3></html>");
		final JLabel labelStatus = new JLabel("Status: ");
		final JLabel labelEstimatedEffort = new JLabel("Estimated Effort: ");
		final JLabel labelActualEffort = new JLabel("Actual Effort: ");
		final JLabel labelDates = new JLabel("<html><h3>Dates</h3></html>");
		final JLabel labelDueDate = new JLabel(" Due Date:");
		final JLabel labelStartDate = new JLabel("Start Date:");
		final JLabel labelRequirement = new JLabel("Requirement: ");
		final JLabel labelPeople = new JLabel("<html><h3>People</h3></html>");
		final JLabel labelPossibleAssignee = new JLabel("Open Assignees: ");
		final JLabel labelChosenAssignee = new JLabel("Chosen Assignees: ");

		// Populate ContentPanel
		contentPanel.add(labelTitle, "cell 0 0");
		contentPanel.add(labelDescr, "cell 0 1");

		final JSeparator separator = new JSeparator();
		contentPanel.add(separator, "cell 0 2,grow");
		contentPanel.add(labelDescrBody, "cell 0 3");

		this.setViewportView(contentPanel);

		contentPanel.add(labelDetails, "cell 0 4");

		final JSeparator separator_1 = new JSeparator();
		contentPanel.add(separator_1, "cell 0 5,grow");

		final JPanel detailsPanel = new JPanel();
		detailsPanel
				.setLayout(new MigLayout("", "[][grow,fill]", "[]5[]5[]5[]"));

		detailsPanel.add(labelStatus, "cell 0 0");
		detailsPanel.add(new JLabel("" + viewTask.getStatus()), "cell 1 0");
		detailsPanel.add(labelEstimatedEffort, "cell 0 1");
		detailsPanel.add(new JLabel("" + viewTask.getEstimatedEffort()),
				"cell 1 1");
		detailsPanel.add(labelActualEffort, "cell 0 2");
		detailsPanel.add(new JLabel("" + viewTask.getActualEffort()),
				"cell 1 2");
		detailsPanel.add(labelRequirement, "cell 0 3");
		detailsPanel
				.add(new JLabel("" + viewTask.getRequirement()), "cell 1 3");

		contentPanel.add(detailsPanel, "cell 0 6,grow");

		final JSeparator separator_2 = new JSeparator();
		contentPanel.add(labelDates, "cell 0 7");
		contentPanel.add(separator_2, "cell 0 8,grow");

		final JPanel datesPanel = new JPanel();
		datesPanel.setLayout(new MigLayout("", "[][grow,fill]", "[]5[]"));
		datesPanel.add(labelStartDate, "cell 0 0");
		datesPanel
				.add(new JLabel(viewTask.getDueDate().toString()), "cell 1 0");
		datesPanel.add(labelDueDate, "cell 0 1");
		datesPanel
				.add(new JLabel(viewTask.getDueDate().toString()), "cell 1 1");
		contentPanel.add(datesPanel, "cell 0 9,grow");

		final JSeparator separator_3 = new JSeparator();
		contentPanel.add(labelPeople, "cell 0 10");
		contentPanel.add(separator_3, "cell 0 11,grow");

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
