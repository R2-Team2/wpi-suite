/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator; // wpi-38
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXDatePicker;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
// requirement module integration
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractInformationPanel.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public abstract class AbstractInformationPanel extends JScrollPane {

	/** The parent panel. */
	protected AbstractTaskPanel parentPanel;

	/** The list of chosen assignees. */
	protected User[] listOfChosenAssignees = new User[] {};

	/** The list of possible assignees. */
	protected User[] listOfPossibleAssignees = new User[] {};

	/** The list of statuses. */
	protected String[] listOfStatuses = new String[] {new TaskStatus("new").toString(),
			new TaskStatus("scheduled").toString(), new TaskStatus("in progress").toString(),
			new TaskStatus("complete").toString()}; // needs to be list of TaskStatus

	/** The string list of requirements. */
	protected List<String> strListOfRequirements = new ArrayList<String>();

	/** The default border. */
	protected final Border defaultBorder = BorderFactory.createEtchedBorder();

	/** The box title. */
	protected JTextField boxTitle;

	/** The box description. */
	protected JTextArea boxDescription;

	/** The dropdown status. */
	protected JComboBox<String> dropdownStatus;

	/** The dropdown requirement. */
	protected JComboBox<String> dropdownRequirement;

	/** The list chosen assignees. */
	protected JList<User> listChosenAssignees;

	/** The list possible assignees. */
	protected JList<User> listPossibleAssignees;

	/** The spinner estimated effort. */
	protected JSpinner spinnerEstimatedEffort;

	/** The spinner actual effort. */
	protected JSpinner spinnerActualEffort;

	/** The button add. */
	protected JButton buttonAdd;

	/** The button remove. */
	protected JButton buttonRemove;

	/** The cal start date. */
	protected JXDatePicker calStartDate;

	/** The cal due date. */
	protected JXDatePicker calDueDate;

	/** Calendar Button Dropdown Icon. */
	protected ImageIcon icon;

	final private List<Requirement> requirements = new ArrayList<Requirement>();

	/**
	 * Builds the layout.
	 */
	protected void buildLayout() {
		setMinimumSize(new Dimension(540, 200));
		// Set the Panel
		final ScrollablePanel contentPanel = new ScrollablePanel();
		contentPanel.setLayout(new MigLayout("", "20[]20", "shrink"));
		// contentPanel.setLayout(new MigLayout("", "[500px:n:500px,left]", "shrink"));

		// get latest list of requirement objects and sort them
		// (code partially from requirements module overviewtreepanel.java)
		final List<Iteration> iterations = IterationModel.getInstance().getIterations();
		Collections.sort(iterations, new IterationComparator());
		for (int i = 0; i < iterations.size(); i++) {

			requirements.addAll(iterations.get(i).getRequirements());
			// gets the list of requirements that is associated with the iteration

		}
		Collections.sort(requirements, new RequirementComparator());
		final String[] arrListOfRequirements = new String[requirements.size()];
		for (int i = 0; i < requirements.size(); i++) {
			// build a List<String> of the names of the requirements
			// defaultComboBoxModel, below, requires an array of string
			String tempName = requirements.get(i).getName();
			strListOfRequirements.add(tempName);
			arrListOfRequirements[i] = tempName;
		}


		// Instantiate GUI Elements
		// Labels
		final JLabel labelTitle = new JLabel("<html>Title: <font color='red'>*</font></html>");
		final JLabel labelDescription =
				new JLabel("<html>Description: <font color='red'>*</font></html>");
		final JLabel labelStatus = new JLabel("Status: ");
		final JLabel labelEstimatedEffort = new JLabel("Estimated Effort: ");
		final JLabel labelActualEffort = new JLabel("Actual Effort: ");
		final JLabel labelDueDate = new JLabel("Due Date: ");
		final JLabel labelStartDate = new JLabel("Start Date: ");
		final JLabel labelRequirement = new JLabel("Requirement: ");
		final JLabel labelPossibleAssignee = new JLabel("Open Assignees: ");
		final JLabel labelChosenAssignee = new JLabel("Chosen Assignees: ");
		// Text Areas
		boxTitle = new JTextField("");
		boxTitle.setBorder(defaultBorder);

		final JScrollPane descrScroll = new JScrollPane();
		boxDescription = new JTextArea();
		boxDescription.setLineWrap(true);
		boxDescription.setBorder(null);
		descrScroll.setBorder(defaultBorder);
		descrScroll.setViewportView(boxDescription);
		// Drop Down Menus
		dropdownRequirement = new JComboBox<String>();

		dropdownRequirement.setModel(new DefaultComboBoxModel<String>(arrListOfRequirements));
		dropdownRequirement.setEnabled(true);
		dropdownRequirement.setBackground(Color.WHITE);
		dropdownStatus = new JComboBox<String>();
		dropdownStatus.setModel(new DefaultComboBoxModel<String>(listOfStatuses));
		dropdownStatus.setEnabled(true);
		dropdownStatus.setBackground(Color.WHITE);
		// Lists
		listChosenAssignees = new JList<User>();
		listPossibleAssignees = new JList<User>();
		// Spinners
		spinnerEstimatedEffort = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
		spinnerActualEffort = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
		// Buttons
		buttonAdd = new JButton(">>");
		buttonAdd.setEnabled(false);
		buttonRemove = new JButton("<<");
		buttonRemove.setEnabled(false);
		// Calendars
		calStartDate = new JXDatePicker();
		calStartDate.setName("start date");
		calStartDate.setDate(Calendar.getInstance().getTime());
		calDueDate = new JXDatePicker();
		calDueDate.setName("due date");
		calDueDate.setDate(Calendar.getInstance().getTime());
		icon = new ImageIcon(this.getClass().getResource("calendar.png"));
		final ImageIcon scaledIcon =
				new ImageIcon(icon.getImage()
						.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		// Code taken from:
		// http://stackoverflow.com/questions/8406200/swingx-personalize-jxdatepicker
		((JButton) calStartDate.getComponent(1)).setIcon(scaledIcon);

		((JButton) calDueDate.getComponent(1)).setIcon(scaledIcon);

		// Setup GUI


		// Setup Columns
		final JPanel leftColumn = new JPanel(new MigLayout());
		final JPanel rightColumn = new JPanel(new MigLayout());

		final JPanel bottom = new JPanel(new MigLayout());

		final JPanel bottomLeft = new JPanel(new MigLayout());
		final JPanel bottomCenter = new JPanel(new MigLayout());
		final JPanel bottomRight = new JPanel(new MigLayout());

		// Assignee view created and populated to the bottom Panel
		listPossibleAssignees.setBorder(defaultBorder);
		bottomLeft.add(labelPossibleAssignee, "left, wrap");
		bottomLeft.add(listPossibleAssignees, "left, width 200px, height 150px, wrap");

		bottomCenter.add(buttonAdd, "center, wrap");
		bottomCenter.add(buttonRemove, "center, wrap");

		listChosenAssignees.setBorder(defaultBorder);
		bottomRight.add(labelChosenAssignee, "left, wrap");
		bottomRight.add(listChosenAssignees, "left, width 200px, height 150px, wrap");

		bottom.add(bottomLeft);
		bottom.add(bottomCenter);
		bottom.add(bottomRight);
		bottom.setBorder(defaultBorder);


		// left and right columns
		leftColumn.add(labelStatus, "left, wrap");
		leftColumn.add(dropdownStatus, "left, width 200px, wrap");
		leftColumn.add(labelRequirement, "left, wrap");
		leftColumn.add(dropdownRequirement, "left, width 200px, wrap");
		leftColumn.add(labelStartDate, "left, wrap");
		leftColumn.add(calStartDate, "left, wrap");
		rightColumn.add(labelEstimatedEffort, "left, wrap");
		rightColumn.add(spinnerEstimatedEffort, "left, width 200px, height 25px, wrap");
		rightColumn.add(labelActualEffort, "left, wrap");
		rightColumn.add(spinnerActualEffort, "left, width 200px, height 25px, wrap");
		rightColumn.add(labelDueDate, "left, wrap");
		rightColumn.add(calDueDate, "left, wrap");

		// Populate contentPanel
		contentPanel.add(labelTitle, "wrap");
		contentPanel.add(boxTitle, "growx, pushx, shrinkx, span, wrap");

		contentPanel.add(labelDescription, "wrap");
		contentPanel.add(descrScroll, "growx, pushx, shrinkx, span, height 200px, wmin 10, wrap");

		contentPanel.add(leftColumn, "left, spany, growy, push");
		contentPanel.add(rightColumn, "right, spany, growy, push");

		contentPanel.add(bottom, "left 5, dock south, spany, growy, push");

		setViewportView(contentPanel);
	}


	/**
	 * Returns the JTextField holding the title.
	 *
	 * @return JTextField
	 */
	public JTextField getTitle() {
		return boxTitle;
	}

	/**
	 * Returns the JTextArea holding the description.
	 *
	 * @return JTextArea
	 */
	public JTextArea getDescription() {
		return boxDescription;
	}

	/**
	 * Returns the JSpinner holding the estimated effort.
	 *
	 * @return JSpinner
	 */
	public JSpinner getEstimatedEffort() {
		return spinnerEstimatedEffort;
	}

	/**
	 * Returns the JSpinner holding the actual effort.
	 *
	 * @return JSpinner
	 */
	public JSpinner getActualEffort() {
		return spinnerEstimatedEffort;
	}

	/**
	 * Returns the JComboBox holding the status.
	 *
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getStatus() {
		return dropdownStatus;
	}

	/**
	 * Returns the JComboBox holding the Requirement.
	 *
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getRequirement() {
		return dropdownRequirement;
	}

	/**
	 * Returns the Start Date.
	 *
	 * @return Date
	 */
	public Date getStartDate() {
		return calStartDate.getDate();
	}

	/**
	 * Returns the Due Date.
	 *
	 * @return Date
	 */
	public Date getDueDate() {
		return calDueDate.getDate();
	}

	/**
	 * Returns the JList holding the Chosen Members.
	 *
	 * @return JList<String>
	 */
	public List<User> getAssignedUsers() {
		return new ArrayList<User>(Arrays.asList(listOfChosenAssignees));
	}

	/**
	 * @return selected requirement object
	 * @throws Exception
	 */
	private Requirement getSelectedRequirement() throws Exception {
		final String reqName = (String) dropdownRequirement.getSelectedItem();

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
	public void openSelectedRequirement() throws Exception {
		edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController.getInstance()
		.editRequirement(getSelectedRequirement());
		edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController.getInstance()
		.openRequirementsTab();
	}

	/**
	 * Disables all of the text fields based on boolean io
	 *
	 * @param io is a flag that if true disables fields, if false enables all fields.
	 */
	public void disableAll(Boolean io) {
		io = !io;
		// aTask.getTaskID();
		boxTitle.setEnabled(io);
		boxDescription.setEnabled(io);
		dropdownStatus.setEnabled(io);
		// requirement
		listChosenAssignees.setEnabled(io);
		calStartDate.setEnabled(io);
		calDueDate.setEnabled(io);
		spinnerEstimatedEffort.setEnabled(io);
		spinnerActualEffort.setEnabled(io);
	}

	/**
	 * opens selected requirement. May be overriden
	 */
	protected void openRequirement() {
		try {
			parentPanel.openSelectedRequirement();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}



/**
 * @version legacy
 * @author Kevin from the requirements manager sorts the Iterations by date
 */
class IterationComparator implements Comparator<Iteration> {
	@Override
	public int compare(Iteration I1, Iteration I2) {
		if (I1.getStart() == null) {
			return -1;
		}
		if (I2.getStart() == null) {
			return 1;
		}
		return I1.getStart().getDate().compareTo(I2.getStart().getDate());
	}
}


/**
 * @version legacy
 * @author Kevin from the requirements manager sorts Requirements by name
 */
class RequirementComparator implements Comparator<Requirement> {
	@Override
	public int compare(Requirement R1, Requirement R2) {
		return R1.getName().compareTo(R2.getName());
	}
}
