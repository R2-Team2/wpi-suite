/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

public class RequirementInformationPanel extends JPanel implements KeyListener,
		ItemListener, RequirementPanelListener {
	private Requirement currentRequirement;
	private RequirementViewMode viewMode;
	private RequirementPanel parentPanel;

	private JTextField boxName;
	private JTextField boxReleaseNum;
	private JTextArea boxDescription;
	private JTextField boxIteration;
	private JTextField boxChildEstimate;
	private JLabel labelChildEstimate;
	private JTextField boxTotalEstimate;
	private JLabel labelTotalEstimate;
	private final Border defaultBorder = (new JTextField()).getBorder();
	private final Border errorBorder = BorderFactory
			.createLineBorder(Color.RED);

	private JLabel parent;
	private JComboBox<RequirementType> dropdownType;
	private JComboBox<RequirementStatus> dropdownStatus;
	private JRadioButton priorityHigh;
	private JRadioButton priorityMedium;
	private JRadioButton priorityLow;
	private JRadioButton priorityBlank;
	private JTextField boxEstimate;
	private ButtonGroup group;

	private JLabel errorName;
	private JLabel errorDescription;
	private JLabel errorEstimate;

	/**
	 * Constructs the requirement information panel
	 * @param parentPanel the panel this info panel reports to
	 * @param mode the current view mode.
	 * @param curr the requirement being edited/created.
	 */
	public RequirementInformationPanel(RequirementPanel parentPanel,
			RequirementViewMode mode, Requirement curr) {
		this.currentRequirement = curr;
		this.parentPanel = parentPanel;
		this.viewMode = mode;

		this.buildLayout();
		
		clearInfo();
	}
	
	/**
	 * Builds the layout panel.
	 * 
	 * @return the newly created and formatted layout panel.
	 */
	private void buildLayout() {
		this.setLayout(new MigLayout("", "", "shrink"));
		//instantialize everything.
		JLabel labelName = new JLabel("Name *");
		JLabel labelReleaseNum = new JLabel("Release Number");
		JLabel labelDescription = new JLabel("Description *");
		JLabel labelIteration = new JLabel("Iteration");
		labelChildEstimate = new JLabel("Child Estimate");
		labelTotalEstimate = new JLabel("Total Estimate");
		JLabel labelType = new JLabel("Type");
		JLabel labelStatus = new JLabel("Status");
		JLabel labelPriority = new JLabel("Priority");
		JLabel labelEstimate = new JLabel("Estimate");
		
		boxName = new JTextField();
		boxName.addKeyListener(this);

		boxReleaseNum = (new JTextField());
		boxReleaseNum.addKeyListener(this);

		boxDescription = new JTextArea();
		boxDescription.setLineWrap(true);
		boxDescription.setBorder(defaultBorder);
		boxDescription.addKeyListener(this);

		boxIteration = (new JTextField());
		boxIteration.addKeyListener(this);

		errorName = (new JLabel());
		errorDescription = (new JLabel());
		


		dropdownType = (new JComboBox<RequirementType>(RequirementType.values()));
		dropdownType.setEditable(false);
		dropdownType.setBackground(Color.WHITE);
		dropdownType.addItemListener(this);

		dropdownStatus = (new JComboBox<RequirementStatus>(
				RequirementStatus.values()));
		dropdownStatus.setEditable(false);
		dropdownStatus.setBackground(Color.WHITE);
		dropdownStatus.addItemListener(this);

		// Radio buttons

		priorityHigh = (new JRadioButton("High"));
		priorityHigh.addItemListener(this);
		priorityMedium = (new JRadioButton("Medium"));
		priorityMedium.addItemListener(this);
		priorityLow = (new JRadioButton("Low"));
		priorityLow.addItemListener(this);
		priorityBlank = (new JRadioButton("None"));
		priorityBlank.addItemListener(this);

		group = new ButtonGroup();
		group.add(priorityBlank);
		group.add(priorityHigh);
		group.add(priorityMedium);
		group.add(priorityLow);

		JPanel priorityPanel = new JPanel();

		priorityPanel.add(priorityLow);
		priorityPanel.add(priorityMedium);
		priorityPanel.add(priorityHigh);
		priorityPanel.add(priorityBlank);

		boxEstimate = (new JTextField());
		boxEstimate.setPreferredSize(new Dimension(50, 20));
		boxEstimate.addKeyListener(this);
		boxChildEstimate = (new JTextField());
		boxChildEstimate.setEnabled(false);
		boxTotalEstimate = new JTextField();
		boxTotalEstimate.setEnabled(false);
		errorEstimate = (new JLabel());
		
		boolean hasChildren = !currentRequirement.getChildren().isEmpty();
		labelChildEstimate.setVisible(hasChildren);
		boxChildEstimate.setVisible(hasChildren);
		
		labelTotalEstimate.setVisible(hasChildren);
		boxTotalEstimate.setVisible(hasChildren);

		parent = new JLabel();
		
		if (currentRequirement.getParentID() != -1) {
			parent.setText("Child of \""
					+ currentRequirement.getParent().getName() + "\"");
		}
		
		//setup the top.
		
		this.add(labelName, "wrap");
		this.add(boxName, "width 100%, span");
		this.add(errorName, "wrap");
		
		this.add(labelDescription, "wrap");
		this.add(boxDescription, "width 100%, span, height 600px, wmin 10, wrap");
		this.add(errorDescription, "wrap");
				
		//setup columns.
		this.add(labelReleaseNum, "left");
		this.add(labelType, "right, wrap");
		this.add(boxReleaseNum, "width 100px, left");
		this.add(dropdownType, "right, wrap");
		this.add(labelIteration, "left");
		this.add(labelStatus, "right, wrap");
		this.add(boxIteration, "width 100px, left");
		this.add(dropdownStatus, "right, wrap");
		this.add(labelEstimate, "left");
		this.add(labelPriority, "right, wrap");
		this.add(boxEstimate, "width 50px, left");
		this.add(priorityPanel, "right, wrap");
		this.add(errorEstimate, "left, wrap");
		this.add(labelChildEstimate, "left, wrap");
		this.add(boxChildEstimate, "width 50px, left");	
		this.add(parent, "right, wrap");
		this.add(labelTotalEstimate, "left, wrap");
		this.add(boxTotalEstimate, "width 50px, left");
	}

	/**
	 * Refreshes the information of the requirement.
	 */
	public void fireRefresh() {
		boolean showTotalEstimate = !currentRequirement.getChildren().isEmpty();
		labelChildEstimate.setVisible(showTotalEstimate);
		boxChildEstimate.setText(
				Integer.toString(currentRequirement.getChildEstimate()));
		boxChildEstimate.setVisible(showTotalEstimate);
		boxChildEstimate.setText(Integer.toString(currentRequirement.getTotalEstimate()));
		boxChildEstimate.setVisible(showTotalEstimate);
		labelTotalEstimate.setVisible(showTotalEstimate);
	
		if (currentRequirement.getParentID() != -1) {
			parent.setText("Child of \""
					+ currentRequirement.getParent().getName() + "\"");
			parent.setVisible(true);
		}
	
		else {
			parent.setVisible(false);
		}
	}

	@Override
	public void fireDeleted(boolean b) {
		getDropdownStatus().setSelectedItem(RequirementStatus.DELETED);		
	}

	@Override
	public void fireValid(boolean b) {}

	@Override
	public void fireChanges(boolean b) {}
	
	/**
	 * Fills the fields of the edit requirement panel based on the current
	 * settings of the edited requirement.
	 */
	@SuppressWarnings("unchecked")
	private void fillFieldsForRequirement() {
		boxName.setText(currentRequirement.getName());
		boxDescription.setText(currentRequirement.getDescription());
		boxEstimate.setText(
				String.valueOf(currentRequirement.getEstimate()));
		boxReleaseNum.setText(currentRequirement.getRelease());
	
		if (currentRequirement.getStatus().equals(RequirementStatus.NEW)) {
			dropdownStatus.removeAllItems();
			dropdownStatus.addItem(RequirementStatus.NEW);
			dropdownStatus.addItem(RequirementStatus.DELETED);
		} else if (currentRequirement.getStatus().equals(
				RequirementStatus.INPROGRESS)) {
			dropdownStatus.removeAllItems();
			dropdownStatus.addItem(RequirementStatus.INPROGRESS);
			dropdownStatus.addItem(RequirementStatus.COMPLETE);
			dropdownStatus.addItem(RequirementStatus.DELETED);
		} else if (currentRequirement.getStatus()
				.equals(RequirementStatus.OPEN)) {
			dropdownStatus.removeAllItems();
			dropdownStatus.addItem(RequirementStatus.OPEN);
			dropdownStatus.addItem(RequirementStatus.DELETED);
		} else if (currentRequirement.getStatus().equals(
				RequirementStatus.COMPLETE)
				|| currentRequirement.getStatus().equals(
						RequirementStatus.DELETED)) {
			if (currentRequirement.getIteration().equals("Backlog")) {
				dropdownStatus.removeAllItems();
				dropdownStatus.addItem(RequirementStatus.OPEN);
				dropdownStatus.addItem(RequirementStatus.COMPLETE);
				dropdownStatus.addItem(RequirementStatus.DELETED);
			} else {
				dropdownStatus.removeAllItems();
				dropdownStatus.addItem(RequirementStatus.INPROGRESS);
				dropdownStatus.addItem(RequirementStatus.COMPLETE);
				dropdownStatus.addItem(RequirementStatus.DELETED);
			}
		}
		dropdownStatus.setSelectedItem(currentRequirement.getStatus());
	
		dropdownType.setSelectedItem(currentRequirement.getType());
		boxIteration.setText(currentRequirement.getIteration().toString());
	
		this.setPriorityButton(currentRequirement.getPriority());
	
		if (currentRequirement.getStatus() == RequirementStatus.INPROGRESS)
			parentPanel.fireDeleted(false);
		if (currentRequirement.getStatus() == RequirementStatus.DELETED)
			disableComponents();
		if (currentRequirement.getEstimate() <= 0)
			getBoxIteration().setEnabled(false);
		if (currentRequirement.getEstimate() <= 0)
			getBoxIteration().setEnabled(false);
	
		if ((currentRequirement.getStatus() == RequirementStatus.INPROGRESS || currentRequirement
				.getStatus() == RequirementStatus.COMPLETE))
			getBoxEstimate().setEnabled(false);
	
		if (currentRequirement.getStatus() == RequirementStatus.COMPLETE)
			this.getBoxIteration().setEnabled(false);
	
		// reset the error messages.
		errorEstimate.setText("");
		boxEstimate.setBorder(defaultBorder);
		errorDescription.setText("");
		boxDescription.setBorder(defaultBorder);
		errorName.setText("");
		boxName.setBorder(defaultBorder);
		boxChildEstimate.setText(Integer.toString(currentRequirement.getChildEstimate()));
		boxTotalEstimate.setText(Integer.toString(currentRequirement.getTotalEstimate()));
	
		repaint();
	}
	
	/**
	 * In the case that the requirement is a child requirement, fills in the data fields
	 * that should be inherited from the parent.
	 */
	private void fillFieldsForParent()
	{
		boxIteration.setText(currentRequirement.getIteration());
		boxEstimate.setText(String.valueOf(currentRequirement.getEstimate()));
		boxReleaseNum.setText(currentRequirement.getRelease());
		dropdownStatus.setSelectedItem(currentRequirement.getParent().getStatus());
		dropdownType.setSelectedItem(currentRequirement.getType());
		
		switch(currentRequirement.getPriority())
		{
			case BLANK:
				getPriorityBlank().setSelected(true);
				break;
			case LOW:
				getPriorityLow().setSelected(true);
				break;
			case MEDIUM:
				getPriorityMedium().setSelected(true);
				break;
			case HIGH:
				getPriorityHigh().setSelected(true);
				break;
		}
		
		this.disableNonChildFields();
		this.getDropdownStatus().setEnabled(false);

	}

	/**
	 * Validates the values of the fields in the requirement panel to ensure
	 * they are valid
	 * @param warn whether to warn the user or not
	 * @return whether fields are valid.
	 */
	public boolean validateFields(boolean warn) {
		boolean isNameValid;
		boolean isDescriptionValid;
		boolean isEstimateValid;
	
		if (getBoxName().getText().length() >= 100) {
			isNameValid = false;
			if(warn)
			{
				getErrorName().setText("No more than 100 chars");
				getBoxName().setBorder(errorBorder);
				getErrorName().setForeground(Color.RED);
			}
		} else if (getBoxName().getText().trim().length() <= 0) {
			isNameValid = false;
			if(warn)
			{
				getErrorName().setText("** Name is REQUIRED");
				getBoxName().setBorder(errorBorder);
				getErrorName().setForeground(Color.RED);
			}
		} else {
			if(warn)
			{
				getErrorName().setText("");
				getBoxName().setBorder(defaultBorder);
			}
			isNameValid = true;
	
		}
		if (getBoxDescription().getText().trim().length() <= 0) {
			isDescriptionValid = false;
			if(warn)
			{
				getErrorDescription().setText("** Description is REQUIRED");
				getErrorDescription().setForeground(Color.RED);
				getBoxDescription().setBorder(errorBorder);
			}
		} else {
			if(warn)
			{
				getErrorDescription().setText("");
				getBoxDescription().setBorder(defaultBorder);
			}
			isDescriptionValid = true;
		}
	
		if (getBoxEstimate().getText().trim().length() <= 0) {
			getBoxEstimate().setText("");
			getErrorEstimate().setText("");
			getBoxEstimate().setBorder(defaultBorder);
			isEstimateValid = true;
		} else if (!(isInteger(getBoxEstimate().getText()))) {
			if(warn)
			{
				getErrorEstimate()
					.setText("** Please enter a non-negative integer");
				getBoxEstimate().setBorder(errorBorder);
				getBoxEstimate().setBorder((new JTextField()).getBorder());
				getErrorEstimate().setForeground(Color.RED);
			}

			isEstimateValid = false;
		} else if (Integer.parseInt(getBoxEstimate().getText()) < 0) {
			if(warn)
			{
				getErrorEstimate()
					.setText("** Please enter a non-negative integer");
				getBoxEstimate().setBorder(errorBorder);
				getErrorEstimate().setForeground(Color.RED);
			}
			isEstimateValid = false;
		} else if (Integer.parseInt(getBoxEstimate().getText()) == 0
				&& !(getBoxIteration().getText().trim().equals("Backlog") || getBoxIteration()
						.getText().trim().equals(""))) {
			if(warn)
			{
				getErrorEstimate()
					.setText(
							"<html>** Cannot have an estimate of 0<br>and be assigned to an iteration.</html>");
				getBoxEstimate().setBorder(errorBorder);
				getErrorEstimate().setForeground(Color.RED);
			}
			isEstimateValid = false;
		} else {
			getErrorEstimate().setText("");
			getBoxEstimate().setBorder(defaultBorder);
			isEstimateValid = true;
		}
	
		return isNameValid && isDescriptionValid && isEstimateValid;
	}
	
	/**
	 * Resets the information back to default
	 */
	public void clearInfo()
	{
		if(viewMode == RequirementViewMode.CREATING)
		{
			this.clear(); //if creating, then clear all
		}
		else
		{
			this.fillFieldsForRequirement(); //if editing, revert back to old info
		}
		
		//no changes have been made, so let the parent know.
		this.parentPanel.fireChanges(false); 
	}
	
	/**
	 * Clears the editing of the requirement.
	 */
	private void clear() 
	{
		if(currentRequirement.getParentID() != -1)
		{
			fillFieldsForParent();
			getBoxName().setText("");
			getBoxDescription().setText("");
			getBoxEstimate().setText("");
			repaint();
			return;
		}
		getBoxName().setText("");
		getBoxDescription().setText("");
		this.getPriorityBlank().setSelected(true);
		getDropdownType().setSelectedItem(RequirementType.BLANK);
		getBoxReleaseNum().setText("");
		getBoxEstimate().setText("");
		getDropdownStatus().setSelectedItem(RequirementStatus.NEW);
		
		this.getErrorEstimate().setText("");
		getBoxEstimate().setBorder(defaultBorder);
		this.getErrorDescription().setText("");
		getBoxDescription().setBorder(defaultBorder);
		this.getErrorName().setText("");
		getBoxName().setBorder(defaultBorder);
		this.getBoxIteration().setEnabled(false);
		this.getDropdownStatus().setEnabled(false);
		repaint(); //repaint the entire panel.
	}

	/**
	 * Updates the requirement/creates the requirement based on the view mode.
	 */
	public void update() {
		updateRequirement(viewMode == RequirementViewMode.CREATING);
	}

	/**
	 * Updates the requirement based on whether it is being created or not
	 * @param created whether the requirement is being created or edited.
	 */
	private void updateRequirement(boolean created) {
		if(created) currentRequirement.setId(RequirementModel.getInstance().getNextID());

		// Extract the name, release number, and description from the GUI fields
		String stringName = this.getBoxName().getText();
		String stringReleaseNum = this.getBoxReleaseNum().getText();
		String stringDescription = this.getBoxDescription().getText();
		String stringEstimate = this.getBoxEstimate().getText();
		String stringIteration = this.getBoxIteration().getText();
	
		if (stringIteration.trim().equals(""))
			stringIteration = "Backlog";
	
		RequirementPriority priority;
		RequirementStatus status = (RequirementStatus) this.getDropdownStatus().getSelectedItem();
		RequirementType type = (RequirementType) getDropdownType()
				.getSelectedItem();
	
		int estimate = stringEstimate.trim().length() == 0 ? 0 : Integer
				.parseInt(stringEstimate);

		// Extract which radio is selected for the priority
		boolean stateHigh = getPriorityHigh().isSelected();
		boolean stateMedium = getPriorityMedium().isSelected();
		boolean stateLow = getPriorityLow().isSelected();
	
		// Convert the priority string to its corresponding enum
		if (stateHigh)
			priority = RequirementPriority.HIGH;
		else if (stateMedium)
			priority = RequirementPriority.MEDIUM;
		else if (stateLow)
			priority = RequirementPriority.LOW;
		else
			priority = RequirementPriority.BLANK;
	
		// Set the time stamp so that all transaction messages from this update
		// will have the same time stamp
		TransactionHistory requirementHistory = currentRequirement.getHistory();
		requirementHistory.setTimestamp(System.currentTimeMillis());
	
		// Create a new requirement object based on the extracted info
		if (currentRequirement.getParentID() != -1) {
			currentRequirement.setName(stringName);
			currentRequirement.setDescription(stringDescription);
			currentRequirement.setStatus(status, created);
			currentRequirement.setEstimate(estimate);
		} else {
			currentRequirement.setName(stringName);
			currentRequirement.setRelease(stringReleaseNum);
			currentRequirement.setDescription(stringDescription);
			currentRequirement.setStatus(status, created);
			currentRequirement.setPriority(priority, created);
			currentRequirement.setEstimate(estimate);
			currentRequirement.setIteration(stringIteration, created);
			currentRequirement.setType(type);
		}
		
		if(created)
		{
			// Set the time stamp for the transaction for the creation of the requirement
			currentRequirement.getHistory().setTimestamp(System.currentTimeMillis());
	        System.out.println("The Time Stamp is now :" + currentRequirement.getHistory().getTimestamp());
	        currentRequirement.getHistory().add("Requirement created");

			RequirementModel.getInstance().addRequirement(currentRequirement);
		}
	
		UpdateRequirementController.getInstance().updateRequirement(
				currentRequirement);
	
		ViewEventController.getInstance().refreshTable();
		
		if(currentRequirement.getParentID() != -1)
		{
			ViewEventController.getInstance().refreshEditRequirementPanel(currentRequirement.getParent());
		}
	}

	/**
	 * Enables all of the components of the editing panel.
	 */
	private void enableComponents() {
		this.getBoxName().setEnabled(true);
		this.getBoxDescription().setEnabled(true);
		this.getBoxEstimate().setEnabled(true);
		this.getBoxReleaseNum().setEnabled(true);
		this.getDropdownType().setEnabled(true);
		if (currentRequirement.getEstimate() > 0)
			this.getBoxIteration().setEnabled(true);
		this.getPriorityHigh().setEnabled(true);
		this.getPriorityMedium().setEnabled(true);
		this.getPriorityLow().setEnabled(true);
		this.getPriorityBlank().setEnabled(true);
		this.parentPanel.fireDeleted(true);
	}

	/**
	 * Disables all the components of the editing panel besides the status
	 * dropdown.
	 */
	private void disableComponents() {
		this.getBoxName().setEnabled(false);
		this.getBoxDescription().setEnabled(false);
		this.getBoxEstimate().setEnabled(false);
		this.getBoxReleaseNum().setEnabled(false);
		this.getDropdownType().setEnabled(false);
		this.getBoxIteration().setEnabled(false);
		this.getPriorityHigh().setEnabled(false);
		this.getPriorityMedium().setEnabled(false);
		this.getPriorityLow().setEnabled(false);
		this.getPriorityBlank().setEnabled(false);
		this.parentPanel.fireDeleted(false);
	}

	/**
	 * Disables all fields that are not editable in a child requirement.
	 */
	protected void disableNonChildFields() {
	
		this.getPriorityBlank().setEnabled(false);
		this.getPriorityHigh().setEnabled(false);
		this.getPriorityLow().setEnabled(false);
		this.getPriorityMedium().setEnabled(false);
		getDropdownType().setEnabled(false);
		getBoxReleaseNum().setEnabled(false);
		getBoxIteration().setEnabled(false);
	}
	
	/**
	 * @return Returns whether any field in the panel has been changed
	 */
	public boolean anythingChanged()
	{
		if(viewMode == RequirementViewMode.CREATING)
		{
			return anythingChangedCreating();
		}
		else
		{
			return anythingChangedEditing();
		}
	}
	
	/**
	 * @return Returns whether any fields in the panel have been changed 
	 */
	private boolean anythingChangedCreating() {
		// Check if the user has changed the name
		if (!(getBoxName().getText().equals(""))){
			return true;}
		// Check if the user has changed the description
		if (!(getBoxDescription().getText().equals(""))){
			return true;}
		// Check if the user has changed the release number
		if (!(getBoxReleaseNum().getText().equals(""))){
			return true;}
		// Check if the user has changed the iteration number
		if (!(getBoxIteration().getText().equals(""))){
			return true;}
		// Check if the user has changed the type
		if (!(((RequirementType)getDropdownType().getSelectedItem()) == RequirementType.BLANK)){
			return true;}
		// Check if the user has changed the estimate
		if (!(getBoxEstimate().getText().trim().equals(""))){
			return true;}

		if (!getPriorityBlank().isSelected())
		{
			return true;
		}
		
		return false;
	}

	/**
	 * @return whether any fields have been changed.
	 */
	private boolean anythingChangedEditing() {
		// Check if the user has changed the name
		if (!(getBoxName().getText().equals(currentRequirement.getName()))){
			return true;}
		// Check if the user has changed the description
		if (!(getBoxDescription().getText().equals(currentRequirement.getDescription()))){
			return true;}
		// Check if the user has changed the release number
		if (!(getBoxReleaseNum().getText().equals(currentRequirement.getRelease()))){
			return true;}
		// Check if the user has changed the iteration number
		if (!(getBoxIteration().getText().equals(currentRequirement.getIteration()))){
			return true;}
		// Check if the user has changed the type
		if (!(((RequirementType)getDropdownType().getSelectedItem()) == currentRequirement.getType())){
			return true;}
		// Check if the user has changed the status
		if (!(((RequirementStatus)getDropdownStatus().getSelectedItem()) == currentRequirement.getStatus())){
			return true;}
		// Check if the user has changed the estimate
		if (!(getBoxEstimate().getText().trim().equals(String.valueOf(currentRequirement.getEstimate())))){
			return true;}

		RequirementPriority reqPriority = currentRequirement.getPriority();
		boolean priorityChanged = false;
		switch(reqPriority)
		{
			case BLANK:
				priorityChanged = !getPriorityBlank().isSelected();
				break;
			case LOW:
				priorityChanged = !getPriorityLow().isSelected();
				break;
			case MEDIUM:
				priorityChanged = !getPriorityMedium().isSelected();
				break;
			case HIGH:
				priorityChanged = !getPriorityHigh().isSelected();
				break;
		}
		if (priorityChanged)
		{
			return true;
		}
		
		return false;
	}

	/**
	 * Returns whether the panel is ready to be removed or not based on if there are changes that haven't been
	 * saved.
	 * 
	 * @return whether the panel can be removed.
	 */
	public boolean readyToRemove()
	{	
		return !anythingChanged();
	}

	/**
	 * Sets the priority button based on the priority inputted
	 * @param priority the priority to be set.
	 */
	public void setPriorityButton(RequirementPriority priority) {
		switch (priority) {
		case BLANK:
			getPriorityBlank().setSelected(true);
			break;
		case LOW:
			getPriorityLow().setSelected(true);
			break;
		case MEDIUM:
			getPriorityMedium().setSelected(true);
			break;
		case HIGH:
			getPriorityHigh().setSelected(true);
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		this.parentPanel.fireValid(validateFields(false));
		this.parentPanel.fireChanges(anythingChanged());
	
		if (getDropdownStatus().getSelectedItem() != RequirementStatus.DELETED) {
			enableComponents();
		} else {
			disableComponents();
		}

		if (getDropdownStatus().getSelectedItem() == RequirementStatus.COMPLETE
				|| getDropdownStatus().getSelectedItem() == RequirementStatus.DELETED) {
			this.parentPanel.fireDeleted(true);
		} else {
			this.parentPanel.fireDeleted(false);
		}

		if (currentRequirement.getParentID() != -1)
			disableNonChildFields();

		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.parentPanel.fireValid(validateFields(false));
		this.parentPanel.fireChanges(anythingChanged());
		
		// check that estimate is valid to enable iterations.
		boolean validEstimate = true;

		int totalEstimate = currentRequirement.getChildEstimate();
		try {
			int estimate = Integer.parseInt(getBoxEstimate().getText()
					.trim());
			validEstimate = estimate > 0;
			totalEstimate += estimate;
		} catch (Exception ex) {
			validEstimate = false;
		}
		
		getBoxTotalEstimate().setText(Integer.toString(totalEstimate));
		this.getBoxIteration().setEnabled(validEstimate);
		if (currentRequirement.getParentID() != -1)
			this.disableNonChildFields();


		this.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	/**
	 * Checks if the input string is an integer
	 * 
	 * @param input
	 *            the string to test
	 * @return true if input is an integer, false otherwise
	 */
	public boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Returns the error name label
	 * @return error name label
	 */
	public JLabel getErrorName() {
		return errorName;
	}

	/**
	 * @return the error description label
	 */
	public JLabel getErrorDescription() {
		return errorDescription;
	}

	/**
	 * 
	 * @return the error estimate label
	 */
	public JLabel getErrorEstimate() {
		return errorEstimate;
	}

	/**
	 * 
	 * @return box name
	 */
	public JTextField getBoxName() {
		return boxName;
	}

	/**
	 * 
	 * @return box release num
	 */
	public JTextField getBoxReleaseNum() {
		return boxReleaseNum;
	}

	/**
	 * 
	 * @return box description
	 */
	public JTextArea getBoxDescription() {
		return boxDescription;
	}

	/**
	 * 
	 * @return box iteration
	 */
	public JTextField getBoxIteration() {
		return boxIteration;
	}

	/**
	 * 
	 * @return box child estimate
	 */
	public JTextField getBoxChildEstimate() {
		return boxChildEstimate;
	}
	
	/**
	 * 
	 * @return box total estimate
	 */
	public JTextField getBoxTotalEstimate() {
		return boxTotalEstimate;
	}

	/**
	 * 
	 * @return type dropdown
	 */
	public JComboBox<RequirementType> getDropdownType() {
		return dropdownType;
	}

	/**
	 * 
	 * @return status dropdown
	 */
	public JComboBox<RequirementStatus> getDropdownStatus() {
		return dropdownStatus;
	}

	/**
	 * 
	 * @return estimate box
	 */
	public JTextField getBoxEstimate() {
		return boxEstimate;
	}
	
	/**
	 * @return high priority button
	 */
	public JRadioButton getPriorityHigh() {
		return priorityHigh;
	}
	/**
	 * @return medium priority button
	 */
	public JRadioButton getPriorityMedium() {
		return priorityMedium;
	}
	/**
	 * @return low priority button
	 */
	public JRadioButton getPriorityLow() {
		return priorityLow;
	}
	/**
	 * @return blank priority button
	 */
	public JRadioButton getPriorityBlank() {
		return priorityBlank;
	}
}
