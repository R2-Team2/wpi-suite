/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.iterationcontroller.AddIterationController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.network.Network;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class EditRequirementPanelTest {
	ViewEventController mockViewEventController;
	AddIterationController mockAddIterationController;
	Network mockNetwork;
	
	Requirement mockRequirement;
	
	RequirementPanel requirementPanel;
	
	@Before
	public void setup() {
		mockViewEventController = mock(ViewEventController.class);
		mockAddIterationController = mock(AddIterationController.class);
		mockNetwork = mock(Network.class);
		
		mockRequirement = mock(Requirement.class);
		
		requirementPanel = new RequirementPanel();
		
		String testName = "test: Name";
		String testDescription = "test: Description";
		String testRelease = "1.0.2";
		int testEstimate = 0;
		//int testActualEffort = 0;
		
		// Set fields of the mock requirement
		//		testRequirement.setName(testName);
		//		testRequirement.setDescription(testDescription);
		//		testRequirement.setRelease(testRelease);
		//		testRequirement.setStatus(RequirementStatus.NEW);
		//		testRequirement.setPriority(RequirementPriority.LOW);
		//		testRequirement.setEstimate(testEstimate);
		//		testRequirement.setType(RequirementType.EPIC);
		//testRequirement.setEstimate(testActualEffort);
		
		when(mockRequirement.getName()).thenReturn(testName);
		when(mockRequirement.getDescription()).thenReturn(testDescription);
		when(mockRequirement.getRelease()).thenReturn(testRelease);
		when(mockRequirement.getStatus()).thenReturn(RequirementStatus.NEW);
		when(mockRequirement.getPriority()).thenReturn(RequirementPriority.LOW);
		when(mockRequirement.getEstimate()).thenReturn(testEstimate);
		when(mockRequirement.getType()).thenReturn(RequirementType.EPIC);
		
		requirementPanel.setDisplayRequirement(mockRequirement);
	}
	
	/**
	 * check whether the field is enabled or not as default
	 */
	@Test
	public void defaultEnabilityAndField() {
		// Create a mock requirement
		//		Requirement testRequirement = new Requirement();
		
		// Create an editRequirementPanel
		//		RequirementPanel testEdit = new RequirementPanel(mockRequirement);
		
		// Check enability of all fields
		//		assertEquals(true, requirementPanel.getInfoPanel().getBoxName().isEnabled());
		//		assertEquals(true, requirementPanel.getInfoPanel().getBoxDescription().isEnabled());
		//		assertEquals(false, requirementPanel.getInfoPanel().getBoxIteration().isEnabled());
		//		assertEquals(true, requirementPanel.getInfoPanel().getDropdownType().isEnabled());
		//		assertEquals(true, requirementPanel.getInfoPanel().getDropdownStatus().isEnabled());
		//		assertEquals(true, requirementPanel.getInfoPanel().getBoxEstimate().isEnabled());
		//		assertEquals(false, requirementPanel.getButtonPanel().getButtonOK().isEnabled());
		//		assertEquals(false, requirementPanel.getButtonPanel().getButtonClear().isEnabled());
		//		assertEquals(true, requirementPanel.getButtonPanel().getButtonCancel().isEnabled());
		//		assertEquals(true, requirementPanel.getButtonPanel().getButtonDelete().isEnabled());
		
		//		// Check for default values in each field
		//		assertEquals(testName, requirementPanel.getInfoPanel().getBoxName().getText());
		//		assertEquals(testDescription, requirementPanel.getInfoPanel().getBoxDescription().getText());
		//		assertEquals(testRelease, requirementPanel.getInfoPanel().getBoxReleaseNum().getText());
		//		assertEquals(RequirementStatus.NEW, requirementPanel.getInfoPanel().getDropdownStatus().getSelectedItem());
		//		assertEquals("0", requirementPanel.getInfoPanel().getBoxEstimate().getText());
		//		assertEquals(RequirementType.EPIC, requirementPanel.getInfoPanel().getDropdownType().getSelectedItem());
	}
	
	/**
	 * Check the error messages when invalid fields are filled
	 */
	@Test
	public void errorFieldTest() {
		//		// Create mock requirement
		//		Requirement testRequirement = new Requirement();
		//		
		//		//int testActualEffort = 0;
		//		
		//		//testRequirement.setEstimate(testActualEffort);
		//		
		//		RequirementPanel testEdit = new RequirementPanel(testRequirement);
		
		String errorMessageNoninterger = "Estimate must be non-negative integer";
		String errorMessageNoMore100 = "No more than 100 chars";
		String errorMessageRequiredDescription = "** Description is REQUIRED";
		
		String hundredCharText = "0";
		// Generate Hundred character string
		for (int i = 0; i < 100; i++) {
			hundredCharText += "0";
		}
		
		// adding invalid fields
		testEdit.getInfoPanel().getBoxName().setText(hundredCharText);
		testEdit.getInfoPanel().getBoxEstimate().setText("-134");
		testEdit.getInfoPanel().getBoxDescription().setText("Desc.");
		testEdit.getInfoPanel().keyReleased(null);
		testEdit.getButtonPanel().getButtonOK().doClick();
		testEdit.getInfoPanel().validateFields(true);
		
		// has to be nonnegative, has to have name, has to have description
		assertEquals(errorMessageNoninterger, testEdit.getInfoPanel().getErrorEstimate().getText());
		assertEquals(errorMessageNoMore100, testEdit.getInfoPanel().getErrorName().getText());
		
		// Iteration is unable, Dropdown status is enable
		assertEquals(false, testEdit.getInfoPanel().getBoxIteration().isEnabled());
		assertEquals(true, testEdit.getInfoPanel().getDropdownStatus().isEnabled());
		
		testEdit.getInfoPanel().getBoxName().setText(hundredCharText);
		testEdit.getInfoPanel().getBoxEstimate().setText("StringCharacter");
		testEdit.getInfoPanel().getBoxDescription().setText(null);
		testEdit.getButtonPanel().getButtonOK().doClick();
		testEdit.getInfoPanel().validateFields(true);
		
		assertEquals(errorMessageNoMore100, testEdit.getInfoPanel().getErrorName().getText());
		assertEquals(errorMessageNoninterger, testEdit.getInfoPanel().getErrorEstimate().getText());
		assertEquals(errorMessageRequiredDescription, testEdit.getInfoPanel().getErrorDescription().getText());
	}
	
	/**
	 * Check when required fields are not filled
	 */
	@Test
	public void errorRequiredFieldTest() {
		
		// Moc requirement
		Requirement testRequirement = new Requirement();
		String testName = "test: Name";
		String testDescription = "test: Description";
		String testRelease = "1.0.2";
		int testEstimate = 0;
		//int testActualEffort = 0;
		testRequirement.setName(testName);
		testRequirement.setDescription(testDescription);
		testRequirement.setRelease(testRelease);
		testRequirement.setStatus(RequirementStatus.NEW);
		testRequirement.setPriority(RequirementPriority.HIGH);
		testRequirement.setEstimate(testEstimate);
		testRequirement.setType(RequirementType.EPIC);
		//testRequirement.setEstimate(testActualEffort)/;
		
		RequirementPanel testEdit = new RequirementPanel(testRequirement);
		// a field is added correctly but both name and description are filled with blanks
		testEdit.getInfoPanel().getBoxEstimate().setText("-134");
		testEdit.getInfoPanel().getBoxName().setText("  ");
		testEdit.getInfoPanel().getBoxDescription().setText("Desc.");
		// release pressed key
		testEdit.getInfoPanel().keyReleased(null);
		
		assertEquals(false, testEdit.getButtonPanel().getButtonOK().isEnabled());
		assertEquals(true, testEdit.getButtonPanel().getButtonClear().isEnabled());
		assertEquals(true, testEdit.getButtonPanel().getButtonCancel().isEnabled());
		
		testEdit.getInfoPanel().getBoxName().setText("Name");
		testEdit.getInfoPanel().getBoxDescription().setText(" ");
		// release pressed key
		testEdit.getInfoPanel().keyReleased(null);
		
		// can't create because no name/description, but a field has been changed
		assertEquals(false, testEdit.getButtonPanel().getButtonOK().isEnabled());
		assertEquals(true, testEdit.getButtonPanel().getButtonClear().isEnabled());
		assertEquals(true, testEdit.getButtonPanel().getButtonCancel().isEnabled());
		
	}
	
	/**
	 * Check when undo the change
	 */
	@Test
	public void undoChangeButtonTest() {
		
		Requirement testRequirement = new Requirement();
		String testName = "test: Name";
		String testDescription = "test: Description";
		String testRelease = "1.0.2";
		int testEstimate = 0;
		//int testActualEffort = 0;
		
		testRequirement.setName(testName);
		testRequirement.setDescription(testDescription);
		testRequirement.setRelease(testRelease);
		testRequirement.setStatus(RequirementStatus.NEW);
		testRequirement.setPriority(RequirementPriority.MEDIUM);
		testRequirement.setEstimate(testEstimate);
		testRequirement.setType(RequirementType.EPIC);
		//testRequirement.setEstimate(testActualEffort);
		
		RequirementPanel testEdit = new RequirementPanel(testRequirement);
		
		// set to each field random stuffs to test clear functionality
		
		testEdit.getInfoPanel().getBoxName().setText(testName);
		testEdit.getInfoPanel().getBoxDescription().setText(testDescription);
		testEdit.getInfoPanel().getBoxIteration().addItem("Iteration test");
		testEdit.getInfoPanel().getBoxIteration().setSelectedItem("Iteration test");
		testEdit.getInfoPanel().getDropdownType().setSelectedItem(RequirementType.SCENARIO);
		testEdit.getInfoPanel().getDropdownStatus().setSelectedItem(RequirementStatus.INPROGRESS);
		testEdit.getInfoPanel().getBoxEstimate().setText("4");
		
		testEdit.getButtonPanel().getButtonClear().doClick();
		
		assertEquals(testName, testEdit.getInfoPanel().getBoxName().getText());
		assertEquals(testDescription, testEdit.getInfoPanel().getBoxDescription().getText());
		assertEquals(testRelease, testEdit.getInfoPanel().getBoxReleaseNum().getText());
		assertEquals(RequirementStatus.NEW, testEdit.getInfoPanel().getDropdownStatus().getSelectedItem());
		assertEquals("0", testEdit.getInfoPanel().getBoxEstimate().getText());
		assertEquals(RequirementType.EPIC, testEdit.getInfoPanel().getDropdownType().getSelectedItem());
		
	}
	
	/**
	 * test fifferent fields
	 */
	/*
	 * @Test
	 * public void updateButtonTest2()
	 * {
	 * Requirement testRequirement = new Requirement();
	 * String testName = "test: Name";
	 * String testDescription = "test: Description";
	 * String testRelease = "1.0.2";
	 * int testEstimate = 0;
	 * //int testActualEffort = 0;
	 * String updateTestName = "Update test: Name";
	 * String updateTestDescription = "Update test: Description";
	 * String updateTestRelease = "1.0.3";
	 * int updateTestEstimate = 1;
	 * testRequirement.setName(testName);
	 * testRequirement.setDescription(testDescription);
	 * testRequirement.setRelease(testRelease);
	 * testRequirement.setStatus(RequirementStatus.NEW);
	 * testRequirement.setPriority(RequirementPriority.LOW);
	 * testRequirement.setEstimate(testEstimate);
	 * testRequirement.setType(RequirementType.EPIC);
	 * testRequirement.setIteration("Backlog");
	 * //testRequirement.setEstimate(testActualEffort);
	 * RequirementPanel testEdit = new RequirementPanel(testRequirement);
	 * testEdit.getInfoPanel().getBoxName().setText(updateTestName);
	 * testEdit.getInfoPanel().getBoxDescription().setText(updateTestDescription)
	 * ;
	 * testEdit.getInfoPanel().getDropdownType().setSelectedItem(RequirementType.
	 * THEME);
	 * testEdit.getInfoPanel().getDropdownStatus().setSelectedItem(RequirementStatus
	 * .INPROGRESS);
	 * testEdit.getInfoPanel().getPriorityHigh().doClick();
	 * testEdit.getInfoPanel().getBoxEstimate().setText("4");
	 * testEdit.getInfoPanel().getBoxIteration().addItem("Iteration test");
	 * testEdit.getInfoPanel().getBoxIteration().setSelectedItem("Iteration test"
	 * );
	 * testEdit.getInfoPanel().update();
	 * // check the result
	 * assertEquals(updateTestName,testEdit.getDisplayRequirement().getName());
	 * assertEquals(updateTestDescription,testEdit.getDisplayRequirement().
	 * getDescription());
	 * assertEquals(RequirementType.THEME,testEdit.getDisplayRequirement().getType
	 * ());
	 * assertEquals("Iteration test",testEdit.getDisplayRequirement().getIteration
	 * ());
	 * assertEquals(RequirementStatus.INPROGRESS,testEdit.getDisplayRequirement()
	 * .getStatus());
	 * assertEquals(RequirementPriority.HIGH,testEdit.getDisplayRequirement().
	 * getPriority());
	 * assertEquals(4,testEdit.getDisplayRequirement().getEstimate());
	 * }
	 */
	
}
