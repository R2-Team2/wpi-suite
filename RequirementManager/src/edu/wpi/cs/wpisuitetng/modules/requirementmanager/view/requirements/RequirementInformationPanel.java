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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

public class RequirementInformationPanel extends JPanel implements KeyListener,
		ItemListener {
	private Requirement currentRequirement;
	private RequirementViewMode viewMode;
	private RequirementPanel parentPanel;

	private JPanel leftPanel;
	private JTextField boxName;
	private JTextField boxReleaseNum;
	private JTextArea boxDescription;
	private JTextField boxIteration;
	private JTextField boxTotalEstimate;
	private JLabel labelTotalEstimate;
	final private Border defaultBorder = (new JTextField()).getBorder();
	final private Border errorBorder = BorderFactory
			.createLineBorder(Color.RED);

	private JPanel rightPanel;
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

	public RequirementInformationPanel(RequirementPanel parentPanel,
			RequirementViewMode mode, Requirement curr) {
		this.currentRequirement = curr;
		this.parentPanel = parentPanel;
		this.viewMode = mode;

		this.add(this.buildLeftPanel());
		this.add(this.buildRightPanel());
		
		clearInfo();
	}

	/**
	 * Builds the left panel.
	 * 
	 * @return the newly created and formatted left panel.
	 */
	private JPanel buildLeftPanel() {
		leftPanel = new JPanel();
		JLabel labelName = new JLabel("Name *");
		JLabel labelReleaseNum = new JLabel("Release Number");
		JLabel labelDescription = new JLabel("Description *");
		JLabel labelIteration = new JLabel("Iteration");

		setBoxName(new JTextField());
		getBoxName().setPreferredSize(new Dimension(200, 20));
		getBoxName().addKeyListener(this);

		boxReleaseNum = (new JTextField());
		getBoxReleaseNum().setPreferredSize(new Dimension(200, 20));
		boxReleaseNum.addKeyListener(this);

		setBoxDescription(new JTextArea());
		getBoxDescription().setLineWrap(true);
		getBoxDescription().setBorder(defaultBorder);
		getBoxDescription().setPreferredSize(new Dimension(200, 200));
		getBoxDescription().addKeyListener(this);

		setBoxIteration(new JTextField());
		getBoxIteration().setPreferredSize(new Dimension(200, 20));
		getBoxIteration().addKeyListener(this);

		setErrorName(new JLabel());
		setErrorDescription(new JLabel());

		if (this.currentRequirement.getParentID() != -1) {
			System.out.println("Parent: "
					+ this.currentRequirement.getParentID());
		}

		SpringLayout leftLayout = new SpringLayout();

		leftPanel.setLayout(leftLayout);

		// Name Field
		leftLayout.putConstraint(SpringLayout.NORTH, labelName, 15,
				SpringLayout.NORTH, leftPanel);
		leftLayout.putConstraint(SpringLayout.WEST, labelName, 15,
				SpringLayout.WEST, leftPanel);

		leftLayout.putConstraint(SpringLayout.NORTH, getBoxName(), 15,
				SpringLayout.SOUTH, labelName);
		leftLayout.putConstraint(SpringLayout.WEST, getBoxName(), 15,
				SpringLayout.WEST, leftPanel);
		leftLayout.putConstraint(SpringLayout.NORTH, getErrorName(), 5,
				SpringLayout.SOUTH, getBoxName());
		leftLayout.putConstraint(SpringLayout.WEST, getErrorName(), 15,
				SpringLayout.WEST, leftPanel);

		// Release Field
		leftLayout.putConstraint(SpringLayout.NORTH, labelReleaseNum, 15,
				SpringLayout.SOUTH, getErrorName());
		leftLayout.putConstraint(SpringLayout.WEST, labelReleaseNum, 15,
				SpringLayout.WEST, leftPanel);
		leftLayout.putConstraint(SpringLayout.NORTH, getBoxReleaseNum(), 15,
				SpringLayout.SOUTH, labelReleaseNum);
		leftLayout.putConstraint(SpringLayout.WEST, getBoxReleaseNum(), 15,
				SpringLayout.WEST, leftPanel);

		// Description Field
		leftLayout.putConstraint(SpringLayout.NORTH, labelDescription, 15,
				SpringLayout.SOUTH, getBoxReleaseNum());
		leftLayout.putConstraint(SpringLayout.WEST, labelDescription, 15,
				SpringLayout.WEST, leftPanel);
		leftLayout.putConstraint(SpringLayout.NORTH, getBoxDescription(), 15,
				SpringLayout.SOUTH, labelDescription);
		leftLayout.putConstraint(SpringLayout.WEST, getBoxDescription(), 15,
				SpringLayout.WEST, leftPanel);
		leftLayout.putConstraint(SpringLayout.NORTH, getErrorDescription(), 5,
				SpringLayout.SOUTH, getBoxDescription());
		leftLayout.putConstraint(SpringLayout.WEST, getErrorDescription(), 15,
				SpringLayout.WEST, leftPanel);

		// Iteration Field
		leftLayout.putConstraint(SpringLayout.NORTH, labelIteration, 15,
				SpringLayout.SOUTH, getErrorDescription());
		leftLayout.putConstraint(SpringLayout.WEST, labelIteration, 15,
				SpringLayout.WEST, leftPanel);
		leftLayout.putConstraint(SpringLayout.NORTH, getBoxIteration(), 15,
				SpringLayout.SOUTH, labelIteration);
		leftLayout.putConstraint(SpringLayout.WEST, getBoxIteration(), 15,
				SpringLayout.WEST, leftPanel);

		leftPanel.add(labelName);
		leftPanel.add(getBoxName());
		leftPanel.add(getErrorName());

		leftPanel.add(labelReleaseNum);
		leftPanel.add(getBoxReleaseNum());

		leftPanel.add(labelDescription);
		leftPanel.add(getBoxDescription());
		leftPanel.add(getErrorDescription());

		leftPanel.add(labelIteration);
		leftPanel.add(getBoxIteration());

		leftPanel.setMinimumSize(new Dimension(250, 500));
		leftPanel.setPreferredSize(new Dimension(250, 500));

		return leftPanel;
	}

	/**
	 * Builds the right panel
	 * 
	 * @return the newly created and formatted right panel.
	 */
	protected JPanel buildRightPanel() {
		rightPanel = new JPanel();

		JLabel labelType = new JLabel("Type");
		JLabel labelStatus = new JLabel("Status");
		JLabel labelPriority = new JLabel("Priority");
		JLabel labelEstimate = new JLabel("Estimate");

		setDropdownType(new JComboBox<RequirementType>(RequirementType.values()));
		getDropdownType().setEditable(false);
		getDropdownType().setBackground(Color.WHITE);
		getDropdownType().addItemListener(this);

		setDropdownStatus(new JComboBox<RequirementStatus>(
				RequirementStatus.values()));
		getDropdownStatus().setEditable(false);
		getDropdownStatus().setBackground(Color.WHITE);
		getDropdownStatus().addItemListener(this);

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
		group.add(getPriorityBlank());
		group.add(getPriorityHigh());
		group.add(getPriorityMedium());
		group.add(getPriorityLow());

		JPanel priorityPanel = new JPanel();

		priorityPanel.add(getPriorityLow());
		priorityPanel.add(getPriorityMedium());
		priorityPanel.add(getPriorityHigh());
		priorityPanel.add(getPriorityBlank());

		setBoxEstimate(new JTextField());
		getBoxEstimate().setPreferredSize(new Dimension(50, 20));
		getBoxEstimate().addKeyListener(this);
		setBoxTotalEstimate(new JTextField());
		setErrorEstimate(new JLabel());

		SpringLayout rightLayout = new SpringLayout();

		rightPanel.setLayout(rightLayout);
		getPriorityBlank().setSelected(true);

		// setup the constraints for the layout.

		// Type Field
		rightLayout.putConstraint(SpringLayout.NORTH, labelType, 15,
				SpringLayout.NORTH, rightPanel);
		rightLayout.putConstraint(SpringLayout.WEST, labelType, 15,
				SpringLayout.WEST, rightPanel);
		rightLayout.putConstraint(SpringLayout.NORTH, getDropdownType(), 15,
				SpringLayout.SOUTH, labelType);
		rightLayout.putConstraint(SpringLayout.WEST, getDropdownType(), 15,
				SpringLayout.WEST, rightPanel);

		// Status Field
		rightLayout.putConstraint(SpringLayout.NORTH, labelStatus, 15,
				SpringLayout.SOUTH, getDropdownType());
		rightLayout.putConstraint(SpringLayout.WEST, labelStatus, 15,
				SpringLayout.WEST, rightPanel);
		rightLayout.putConstraint(SpringLayout.NORTH, getDropdownStatus(), 15,
				SpringLayout.SOUTH, labelStatus);
		rightLayout.putConstraint(SpringLayout.WEST, getDropdownStatus(), 15,
				SpringLayout.WEST, rightPanel);

		// Priority Field
		rightLayout.putConstraint(SpringLayout.NORTH, labelPriority, 15,
				SpringLayout.SOUTH, getDropdownStatus());
		rightLayout.putConstraint(SpringLayout.WEST, labelPriority, 15,
				SpringLayout.WEST, rightPanel);
		rightLayout.putConstraint(SpringLayout.NORTH, priorityPanel, 15,
				SpringLayout.SOUTH, labelPriority);
		rightLayout.putConstraint(SpringLayout.WEST, priorityPanel, 15,
				SpringLayout.WEST, rightPanel);

		// Estimate Field
		rightLayout.putConstraint(SpringLayout.NORTH, labelEstimate, 15,
				SpringLayout.SOUTH, priorityPanel);
		rightLayout.putConstraint(SpringLayout.WEST, labelEstimate, 15,
				SpringLayout.WEST, rightPanel);
		rightLayout.putConstraint(SpringLayout.NORTH, getBoxEstimate(), 15,
				SpringLayout.SOUTH, labelEstimate);
		rightLayout.putConstraint(SpringLayout.WEST, getBoxEstimate(), 15,
				SpringLayout.WEST, rightPanel);
		rightLayout.putConstraint(SpringLayout.NORTH, getErrorEstimate(), 5,
				SpringLayout.SOUTH, getBoxEstimate());
		rightLayout.putConstraint(SpringLayout.WEST, getErrorEstimate(), 15,
				SpringLayout.WEST, rightPanel);

		// Total Estimate Field

		labelTotalEstimate = new JLabel("Total Estimate");
		getBoxTotalEstimate().setPreferredSize(new Dimension(50, 20));
		getBoxTotalEstimate().setEnabled(false);
		getBoxTotalEstimate().setText(
				Integer.toString(currentRequirement.getTotalEstimate()));

		rightLayout.putConstraint(SpringLayout.NORTH, labelTotalEstimate, 15,
				SpringLayout.SOUTH, getBoxEstimate());
		rightLayout.putConstraint(SpringLayout.WEST, labelTotalEstimate, 15,
				SpringLayout.WEST, rightPanel);
		rightLayout.putConstraint(SpringLayout.NORTH, getBoxTotalEstimate(),
				15, SpringLayout.SOUTH, labelTotalEstimate);
		rightLayout.putConstraint(SpringLayout.WEST, getBoxTotalEstimate(), 15,
				SpringLayout.WEST, rightPanel);

		rightPanel.add(labelTotalEstimate);
		rightPanel.add(getBoxTotalEstimate());

		labelTotalEstimate.setVisible(!currentRequirement.getChildren()
				.isEmpty());
		getBoxTotalEstimate().setVisible(
				!currentRequirement.getChildren().isEmpty());

		parent = new JLabel();
		rightPanel.add(parent);

		if (this.currentRequirement.getParentID() != -1) {
			parent.setText("Child of \""
					+ currentRequirement.getParent().getName() + "\"");
		}

		rightPanel.add(labelType);
		rightPanel.add(getDropdownType());
		rightPanel.add(labelStatus);
		rightPanel.add(getDropdownStatus());
		rightPanel.add(labelPriority);
		rightPanel.add(priorityPanel);
		rightPanel.add(labelEstimate);
		rightPanel.add(getBoxEstimate());
		rightPanel.add(getErrorEstimate());

		// restrict size of these elements .
		rightPanel.setMinimumSize(new Dimension(315, 500));
		rightPanel.setPreferredSize(new Dimension(315, 500));

		return rightPanel;
	}

	public void refreshInfo() {
		boolean showTotalEstimate = !currentRequirement.getChildren().isEmpty();
		labelTotalEstimate.setVisible(showTotalEstimate);
		getBoxEstimate().setVisible(showTotalEstimate);
		getBoxTotalEstimate().setText(
				Integer.toString(currentRequirement.getTotalEstimate()));
		getBoxTotalEstimate().setVisible(showTotalEstimate);
	
		if (currentRequirement.getParentID() != -1) {
			parent.setText("Child of \""
					+ currentRequirement.getParent().getName() + "\"");
			parent.setVisible(true);
		}
	
		else {
			parent.setVisible(false);
		}
	}

	/**
	 * Fills the fields of the edit requirement panel based on the current
	 * settings of the edited requirement.
	 */
	@SuppressWarnings("unchecked")
	private void fillFieldsForRequirement() {
		getBoxName().setText(currentRequirement.getName());
		getBoxDescription().setText(currentRequirement.getDescription());
		getBoxEstimate().setText(
				String.valueOf(currentRequirement.getEstimate()));
		getBoxReleaseNum().setText(currentRequirement.getRelease());
	
		if (currentRequirement.getStatus().equals(RequirementStatus.NEW)) {
			getDropdownStatus().removeAllItems();
			getDropdownStatus().addItem(RequirementStatus.NEW);
			getDropdownStatus().addItem(RequirementStatus.DELETED);
		} else if (currentRequirement.getStatus().equals(
				RequirementStatus.INPROGRESS)) {
			getDropdownStatus().removeAllItems();
			getDropdownStatus().addItem(RequirementStatus.INPROGRESS);
			getDropdownStatus().addItem(RequirementStatus.COMPLETE);
			getDropdownStatus().addItem(RequirementStatus.DELETED);
		} else if (currentRequirement.getStatus()
				.equals(RequirementStatus.OPEN)) {
			getDropdownStatus().removeAllItems();
			getDropdownStatus().addItem(RequirementStatus.OPEN);
			getDropdownStatus().addItem(RequirementStatus.DELETED);
		} else if (currentRequirement.getStatus().equals(
				RequirementStatus.COMPLETE)
				|| currentRequirement.getStatus().equals(
						RequirementStatus.DELETED)) {
			if (currentRequirement.getIteration().equals("Backlog")) {
				getDropdownStatus().removeAllItems();
				getDropdownStatus().addItem(RequirementStatus.OPEN);
				getDropdownStatus().addItem(RequirementStatus.COMPLETE);
				getDropdownStatus().addItem(RequirementStatus.DELETED);
			} else {
				getDropdownStatus().removeAllItems();
				getDropdownStatus().addItem(RequirementStatus.INPROGRESS);
				getDropdownStatus().addItem(RequirementStatus.COMPLETE);
				getDropdownStatus().addItem(RequirementStatus.DELETED);
			}
		}
		getDropdownStatus().setSelectedItem(currentRequirement.getStatus());
	
		getDropdownType().setSelectedItem(currentRequirement.getType());
		getBoxIteration().setText(currentRequirement.getIteration().toString());
	
		this.setPriorityDropdown(currentRequirement.getPriority());
	
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
		this.getErrorEstimate().setText("");
		getBoxEstimate().setBorder(defaultBorder);
		this.getErrorDescription().setText("");
		getBoxDescription().setBorder(defaultBorder);
		this.getErrorName().setText("");
		getBoxName().setBorder(defaultBorder);
	
		repaint();
	}
	
	private void fillFieldsForParent()
	{
		getBoxIteration().setText(currentRequirement.getIteration());
		getBoxEstimate().setText(String.valueOf(currentRequirement.getEstimate()));
		getBoxReleaseNum().setText(currentRequirement.getRelease());
		getDropdownStatus().setSelectedItem(currentRequirement.getParent().getStatus());
		getDropdownType().setSelectedItem(currentRequirement.getType());
		
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
	 */
	public boolean validateFields() {
		boolean isNameValid;
		boolean isDescriptionValid;
		boolean isEstimateValid;
	
		if (getBoxName().getText().length() >= 100) {
			isNameValid = false;
			getErrorName().setText("No more than 100 chars");
			getBoxName().setBorder(errorBorder);
			getErrorName().setForeground(Color.RED);
		} else if (getBoxName().getText().trim().length() <= 0) {
			isNameValid = false;
			getErrorName().setText("** Name is REQUIRED");
			getBoxName().setBorder(errorBorder);
			getErrorName().setForeground(Color.RED);
		} else {
			getErrorName().setText("");
			getBoxName().setBorder(defaultBorder);
			isNameValid = true;
	
		}
		if (getBoxDescription().getText().trim().length() <= 0) {
			isDescriptionValid = false;
			getErrorDescription().setText("** Description is REQUIRED");
			getErrorDescription().setForeground(Color.RED);
			getBoxDescription().setBorder(errorBorder);
		} else {
			getErrorDescription().setText("");
			getBoxDescription().setBorder(defaultBorder);
			isDescriptionValid = true;
		}
	
		if (getBoxEstimate().getText().trim().length() <= 0) {
			getBoxEstimate().setText("");
			getErrorEstimate().setText("");
			getBoxEstimate().setBorder(defaultBorder);
			isEstimateValid = true;
		} else if (!(isInteger(getBoxEstimate().getText()))) {
			getErrorEstimate()
					.setText("** Please enter a non-negative integer");
			getBoxEstimate().setBorder(errorBorder);
			getBoxEstimate().setBorder((new JTextField()).getBorder());
	
			isEstimateValid = false;
			getErrorEstimate().setForeground(Color.RED);
		} else if (Integer.parseInt(getBoxEstimate().getText()) < 0) {
			getErrorEstimate()
					.setText("** Please enter a non-negative integer");
			getBoxEstimate().setBorder(errorBorder);
			isEstimateValid = false;
			getErrorEstimate().setForeground(Color.RED);
		} else if (Integer.parseInt(getBoxEstimate().getText()) == 0
				&& !(getBoxIteration().getText().trim().equals("Backlog") || getBoxIteration()
						.getText().trim().equals(""))) {
			getErrorEstimate()
					.setText(
							"<html>** Cannot have an estimate of 0<br>and be assigned to an iteration.</html>");
			getBoxEstimate().setBorder(errorBorder);
			isEstimateValid = false;
			getErrorEstimate().setForeground(Color.RED);
		} else {
			getErrorEstimate().setText("");
			getBoxEstimate().setBorder(defaultBorder);
			isEstimateValid = true;
		}
	
		return isNameValid && isDescriptionValid && isEstimateValid;
	}
	
	public void clearInfo()
	{
		if(viewMode == RequirementViewMode.CREATING)
		{
			this.clear();
		}
		else
		{
			this.fillFieldsForRequirement();
		}
		
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
		
		this.getErrorEstimate().setText("");
		getBoxEstimate().setBorder(defaultBorder);
		this.getErrorDescription().setText("");
		getBoxDescription().setBorder(defaultBorder);
		this.getErrorName().setText("");
		getBoxName().setBorder(defaultBorder);
		repaint(); //repaint the entire panel.
	}

	public void update() {
		if (viewMode == RequirementViewMode.CREATING) {
			createRequirement();
		} else {
			updateRequirement();
		}
	}

	private void updateRequirement() {
		// Extract the name, release number, and description from the GUI fields
		String stringName = this.getBoxName().getText();
		String stringReleaseNum = this.getBoxReleaseNum().getText();
		String stringDescription = this.getBoxDescription().getText();
		String stringEstimate = this.getBoxEstimate().getText();
		String stringIteration = this.getBoxIteration().getText();
	
		if (stringIteration.trim().equals(""))
			stringIteration = "Backlog";
	
		RequirementPriority priority;
		RequirementStatus status;
		RequirementType type = (RequirementType) getDropdownType()
				.getSelectedItem();
	
		int estimate = stringEstimate.trim().length() == 0 ? 0 : Integer
				.parseInt(stringEstimate);
		// Extract the status from the GUI
		status = (RequirementStatus) this.getDropdownStatus().getSelectedItem();
		// Extract which radio is selected for the priority
		// If requirement deleted {}
		// estimate = iteration.getEstimate()- estimate;
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
	
		// Set to false to indicate the requirement is being newly created
		boolean created = false;
	
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
	
		UpdateRequirementController.getInstance().updateRequirement(
				currentRequirement);
	
		ViewEventController.getInstance().refreshTable();
	}

	private void createRequirement() {
		currentRequirement.setId(RequirementModel.getInstance().getNextID());
		// Extract the name, release number, and description from the GUI fields
		String stringName = this.getBoxName().getText();
		String stringReleaseNum = this.getBoxReleaseNum().getText();
		String stringDescription = this.getBoxDescription().getText();
		String stringIteration = this.getBoxIteration().getText();
		String stringEstimate = this.getBoxEstimate().getText();
		
		RequirementPriority priority;
		RequirementStatus status;
		RequirementType type;
		int estimate = stringEstimate.trim().length() == 0 ? 0 : Integer.parseInt(stringEstimate);
		
		// Extract the status from the GUI
		status = (RequirementStatus)this.getDropdownStatus().getSelectedItem();
		type = (RequirementType)this.getDropdownType().getSelectedItem();
		// Extract which radio is selected for the priority
		boolean stateHigh = getPriorityHigh().isSelected();
		boolean stateMedium = getPriorityMedium().isSelected();
		boolean stateLow = getPriorityLow().isSelected();
		boolean stateBlank = getPriorityBlank().isSelected();

		// Convert the priority string to its corresponding enum
		if (stateHigh)
			priority = RequirementPriority.HIGH;
		else if (stateMedium)
			priority = RequirementPriority.MEDIUM;
		else if (stateLow)
			priority = RequirementPriority.LOW;
		else 
			priority = RequirementPriority.BLANK;


		// Set to true to indicate the requirement is being newly created
		boolean created = true;
				
		if(currentRequirement.getParentID() != -1)
		{
			currentRequirement.setName(stringName);
			currentRequirement.setDescription(stringDescription);
			currentRequirement.setStatus(status, created);
			currentRequirement.setEstimate(estimate);
		}
		else
		{
			// Create a new requirement object based on the extracted info
			currentRequirement.setName(stringName);
			currentRequirement.setDescription(stringDescription);		
			currentRequirement.setRelease(stringReleaseNum);
			currentRequirement.setStatus(status, created);
			currentRequirement.setPriority(priority, created);
			currentRequirement.setType(type);
			currentRequirement.setEstimate(estimate);
			currentRequirement.setIteration(stringIteration, created);
		}
		
		// Set the time stamp for the transaction for the creation of the requirement
		currentRequirement.getHistory().setTimestamp(System.currentTimeMillis());
        System.out.println("The Time Stamp is now :" + currentRequirement.getHistory().getTimestamp());
        currentRequirement.getHistory().add("Requirement created");

		RequirementModel.getInstance().addRequirement(currentRequirement);
		
		ViewEventController.getInstance().refreshEditRequirementPanel(RequirementModel.getInstance().getRequirement(currentRequirement.getParentID()));
	
	}

	public void deleteRequirement() {
		getDropdownStatus().setSelectedItem(RequirementStatus.DELETED);
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
	 * Returns whether any field in the panel has been changed
	 */
	public boolean anythingChanged()
	{
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
		if(viewMode == RequirementViewMode.CREATING) return true;
		if(anythingChanged())
		{
			int result = JOptionPane.showConfirmDialog(this, "Discard unsaved changes and close tab?", "Discard Changes?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			return result == 0;
		}
		else
		{
			return true;
		}
	}

	public void setPriorityDropdown(RequirementPriority priority) {
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
		if (viewMode == RequirementViewMode.CREATING) {
			this.parentPanel
					.fireValid(getBoxName().getText().trim().length() > 0
							&& getBoxDescription().getText().trim().length() > 0);
			this.parentPanel.fireChanges(anythingChanged());
			this.repaint();
		} else {
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (viewMode == RequirementViewMode.CREATING) {
			this.parentPanel
					.fireValid(getBoxName().getText().trim().length() > 0
							&& getBoxDescription().getText().trim().length() > 0);
			this.parentPanel.fireChanges(anythingChanged());
	
			// check that estimate is valid to enable iterations.
			boolean validEstimate = true;
	
			try {
				int estimate = Integer.parseInt(getBoxEstimate().getText()
						.trim());
				validEstimate = estimate > 0;
			} catch (Exception ex) {
				validEstimate = false;
			}
	
			this.getBoxIteration().setEnabled(validEstimate);
			if (currentRequirement.getParentID() != -1)
				this.disableNonChildFields();
	
			this.repaint();
		} else {
			this.parentPanel.fireChanges(anythingChanged());
			this.parentPanel.fireChanges(anythingChanged());
	
			// check that estimate is valid to enable iterations.
			boolean validEstimate = true;
	
			try {
				int estimate = Integer.parseInt(getBoxEstimate().getText()
						.trim());
				validEstimate = estimate > 0;
			} catch (Exception ex) {
				validEstimate = false;
			}
	
			this.getBoxIteration().setEnabled(validEstimate);
			if (currentRequirement.getParentID() != -1)
				disableNonChildFields();
	
			this.repaint();
		}
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

	public JLabel getErrorName() {
		return errorName;
	}

	public void setErrorName(JLabel errorName) {
		this.errorName = errorName;
	}

	public JLabel getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(JLabel errorDescription) {
		this.errorDescription = errorDescription;
	}

	public JLabel getErrorEstimate() {
		return errorEstimate;
	}

	public void setErrorEstimate(JLabel errorEstimate) {
		this.errorEstimate = errorEstimate;
	}

	public JTextField getBoxName() {
		return boxName;
	}

	public void setBoxName(JTextField boxName) {
		this.boxName = boxName;
	}

	public JTextField getBoxReleaseNum() {
		return boxReleaseNum;
	}

	public void setBoxReleaseNum(JTextField boxReleaseNum) {
		this.boxReleaseNum = boxReleaseNum;
	}

	public JTextArea getBoxDescription() {
		return boxDescription;
	}

	public void setBoxDescription(JTextArea boxDescription) {
		this.boxDescription = boxDescription;
	}

	public JTextField getBoxIteration() {
		return boxIteration;
	}

	public void setBoxIteration(JTextField boxIteration) {
		this.boxIteration = boxIteration;
	}

	public JTextField getBoxTotalEstimate() {
		return boxTotalEstimate;
	}

	public void setBoxTotalEstimate(JTextField boxTotalEstimate) {
		this.boxTotalEstimate = boxTotalEstimate;
	}

	public JComboBox<RequirementType> getDropdownType() {
		return dropdownType;
	}

	public void setDropdownType(JComboBox<RequirementType> dropdownType) {
		this.dropdownType = dropdownType;
	}

	public JComboBox<RequirementStatus> getDropdownStatus() {
		return dropdownStatus;
	}

	public void setDropdownStatus(JComboBox<RequirementStatus> dropdownStatus) {
		this.dropdownStatus = dropdownStatus;
	}

	public JTextField getBoxEstimate() {
		return boxEstimate;
	}

	public void setBoxEstimate(JTextField boxEstimate) {
		this.boxEstimate = boxEstimate;
	}
	
	public JRadioButton getPriorityHigh() {
		return priorityHigh;
	}

	public void setPriorityHigh(JRadioButton priorityHigh) {
		this.priorityHigh = priorityHigh;
	}

	public JRadioButton getPriorityMedium() {
		return priorityMedium;
	}

	public void setPriorityMedium(JRadioButton priorityMedium) {
		this.priorityMedium = priorityMedium;
	}

	public JRadioButton getPriorityLow() {
		return priorityLow;
	}

	public void setPriorityLow(JRadioButton priorityLow) {
		this.priorityLow = priorityLow;
	}

	public JRadioButton getPriorityBlank() {
		return priorityBlank;
	}

	public void setPriorityBlank(JRadioButton priorityBlank) {
		this.priorityBlank = priorityBlank;
	}
}
