/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

/**
 * The Class ViewTaskInformationPanel.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class ViewTaskInformationPanel extends AbstractInformationPanel {
	
	private final List<Requirement> requirements = new ArrayList<Requirement>();
	
	/**
	 * Constructor for the ViewTaskButtonPanel.
	 *
	 * @param aParentPanel the parent panel
	 */
	public ViewTaskInformationPanel(ViewTaskPanel aParentPanel) {
		parentPanel = aParentPanel;
		buildLayout();
		setupListeners();
		// this.disableAll(true);
		// setTask();
		
		final List<Iteration> iterations = IterationModel.getInstance().getIterations();
		Collections.sort(iterations, new IterationComparator());
		for (int i = 0; i < iterations.size(); i++) {

			requirements.addAll(iterations.get(i).getRequirements());
			// gets the list of requirements that is associated with the iteration

		}
		Collections.sort(requirements, new RequirementComparator());
	}
	
	@Override
	public void buildLayout() {
		this.setMinimumSize(new Dimension(540, 200));
		// Set the Panel
		final ScrollablePanel contentPanel = new ScrollablePanel();
		contentPanel.setLayout(new MigLayout("", "20[grow,fill]20",
				"[][]3[]24[]3[]24[]3[]24[]24[]24[]24"));

		// Instantiate GUI Elements
		// Labels
		final Task viewTask = parentPanel.aTask;
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
		final JLabel labelAssignees = new JLabel("<html><h3>Assignees</h3></html>");
		final JLabel labelComments = new JLabel("<html><h3>Comments</h3></html>");
		final JLabel labelActivityLog = new JLabel("<html><h3>Activity Log</h3></html>");

		// TODO use a nice icon
		buttonOpenRequirement = new JButton("<");
		// TODO force the button to be this small
		buttonOpenRequirement.setPreferredSize(new Dimension(16, 16));

		// Populate ContentPanel
		//**Title**
		JPanel titlePanel = new JPanel(new MigLayout());
		titlePanel.add(labelTitle, "cell 0 0");
		contentPanel.add(titlePanel, "cell 0 0");
		
		//**Description**
		
		//Description Title and Separator
		contentPanel.add(labelDescr, "cell 0 1, split 2, span");
		contentPanel.add(new JSeparator(), "cell 0 1, growx, wrap");
		
		//Description Body
		contentPanel.add(labelDescrBody, "cell 0 2");
		
		//**Details**
		
		//Details Title and Separator
		contentPanel.add(labelDetails, "cell 0 3, split 2, span");
		contentPanel.add(new JSeparator(), "cell 0 3, growx, wrap");
		
		//Details Body
		JPanel detailsPanel = new JPanel(new MigLayout("", "[]100[]", "[][][][]"));
		
		detailsPanel.add(labelStatus, "cell 0 0");
		detailsPanel.add(new JLabel("" + viewTask.getStatus()), "cell 1 0");
		detailsPanel.add(labelEstimatedEffort, "cell 0 1");
		detailsPanel.add(new JLabel("" + viewTask.getEstimatedEffort()),"cell 1 1");
		detailsPanel.add(labelActualEffort, "cell 0 2");
		detailsPanel.add(new JLabel("" + viewTask.getActualEffort()),"cell 1 2");
		detailsPanel.add(labelRequirement, "cell 0 3");
		
		String requirementText = viewTask.getRequirement();
		if (requirementText == null || requirementText.equals("None")) {
			requirementText = "None";
			buttonOpenRequirement.setEnabled(false);
		}
		else {
			buttonOpenRequirement.setEnabled(true);
		}
		detailsPanel.add(new JLabel(requirementText), "cell 1 3");
		detailsPanel.add(buttonOpenRequirement, "cell 2 3, left");
		
		contentPanel.add(detailsPanel, "cell 0 4, left, growy, push, span, wrap");
		
		//**Dates**
		
		//Dates Title and Separator
		contentPanel.add(labelDates, "cell 0 5, split 2, span");
		contentPanel.add(new JSeparator(), "cell 0 5, growx, wrap");

		//Dates Panel
		JPanel datesPanel = new JPanel(new MigLayout("", "[]135[]", "[][]"));
		
		datesPanel.add(labelStartDate, "cell 0 0");
		datesPanel.add(new JLabel(formatDate(viewTask.getStartDate())), "cell 1 0");
		datesPanel.add(labelDueDate, "cell 0 1");
		datesPanel.add(new JLabel(formatDate(viewTask.getDueDate())), "cell 1 1");
		contentPanel.add(datesPanel, "cell 0 6, left, growy, push, span, wrap");
		
		//**People**
		
		//People Title and Separator
		contentPanel.add(labelAssignees, "cell 0 7, split 2, span");
		contentPanel.add(new JSeparator(), "cell 0 7, growx, wrap");
		
		//**Comments**
		
		//People Title and Separator
		contentPanel.add(labelComments, "cell 0 8, split 2, span");
		contentPanel.add(new JSeparator(), "cell 0 8, growx, wrap");
		
		//**Activity Log**
		
		//People Title and Separator
		contentPanel.add(labelActivityLog, "cell 0 9, split 2, span");
		contentPanel.add(new JSeparator(), "cell 0 9, growx, wrap");

		this.setViewportView(contentPanel);

	}

	/**
	 * Sets the fields from the class's task object.
	 */
	public void setupTask() {
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
	
	/**
	 * Sets up listeners for members of panel
	 * 
	 */
	protected void setupListeners() {
		buttonOpenRequirement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.infoPanel.openRequirement();
			}
		});
	}
	
	/**
     * Returns the formatted due date of a task.
     *
     * @param t A given Task Object
     * @return dateString Formatted Due Date of Task t in mm/dd/yy
     */
    private String formatDate(Date date) {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        final String dateString = dateFormatter.format(date);
        return dateString;
    }

	@Override
	public Task getTask() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @return selected requirement object
	 * @throws Exception
	 */
	private Requirement getCurrentRequirement() throws Exception {
		final String reqName = parentPanel.aTask.getRequirement();

		for (Requirement requirement : requirements) {
			if (requirement.getName().equals(reqName)) {
				return requirement;
			}
		}

		throw new Exception("Invalid requirement selected");
	}
	
	/**
	 * @throws Exception invalid requirement selected
	 */
	protected void openRequirement() {
		try {
			edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController
					.getInstance().editRequirement(getCurrentRequirement());
			edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController
					.getInstance().openRequirementsTab();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}
