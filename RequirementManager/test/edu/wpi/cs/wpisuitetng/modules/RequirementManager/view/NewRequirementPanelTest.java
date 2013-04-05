package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements.NewRequirementPanel;

public class NewRequirementPanelTest {

	@Before
	public void setUp() throws Exception {
		
		 
	}

	@Test
	public void errorFieldTest() {
		NewRequirementPanel testNew = new NewRequirementPanel();
		String errorMessageNoninterger = "** Please enter a non-negative integer";
		String errorMessageNoMore100 = "No more than 100 chars";
		String errorMessageRequiredName = "** Name is REQUIRED";
		String errorMessageRequiredDescription = "** Description is REQUIRED";
		String testName = "testName";
		String testDescription = "testDescription";
		
		// initial set up
		assertEquals(RequirementStatus.NEW,testNew.getDropdownStatus().getSelectedItem());
		assertEquals(RequirementType.BLANK,testNew.getDropdownType().getSelectedItem());
		
		String hundredCharText = "0";
		
		for(int i = 0; i<100; i++)
		{
			hundredCharText = hundredCharText +"0";
		}
		
		testNew.getBoxEstimate().setText("-134");
		testNew.getBoxDescription().setText("   ");
		
		testNew.getButtonUpdate().doClick();
		
		// has to be nonnegative, has to have name, has to have description
		assertEquals(errorMessageNoninterger,testNew.getErrorEstimate().getText());
		assertEquals(errorMessageRequiredName,testNew.getErrorName().getText());
		assertEquals(errorMessageRequiredDescription,testNew.getErrorDescription().getText());
		// Iteration is unable, Dropdown status is unable
		assertEquals(false, testNew.getBoxIteration().isEnabled());
		assertEquals(false, testNew.getDropdownStatus().isEnabled());
		testNew.getBoxName().setText(hundredCharText);
		testNew.getBoxEstimate().setText("StringCharacter");
		testNew.getBoxDescription().setText(null);
		testNew.getButtonUpdate().doClick();
		
		assertEquals(errorMessageNoMore100,testNew.getErrorName().getText());
		assertEquals(errorMessageNoninterger,testNew.getErrorEstimate().getText());
		assertEquals(errorMessageRequiredDescription,testNew.getErrorDescription().getText());
		
	}
	
	@Test
	public void clearButtonTest() {
		NewRequirementPanel testNew = new NewRequirementPanel();
		String testName = "testName";
		String testDescription = "testDescription";
		
		// set to each field random stuffs to test clear functionality
		
		testNew.getBoxName().setText(testName);
		testNew.getBoxDescription().setText(testDescription);
		testNew.getBoxEstimate().setText("4");
		testNew.getButtonClear().doClick();
		
		assertEquals("",testNew.getBoxName().getText());
		assertEquals("",testNew.getErrorDescription().getText());
		assertEquals("",testNew.getErrorEstimate().getText());
		
		
	}
	
	@Test
	public void updateButtonTest()
	{
		NewRequirementPanel testNew = new NewRequirementPanel();
		String testName = "testName";
		String testDescription = "testDescription";

		
		testNew.getBoxName().setText(testName);
		testNew.getBoxDescription().setText(testDescription);
		testNew.getDropdownType().setSelectedItem(RequirementType.THEME);
		testNew.getPriorityHigh().doClick();
		testNew.getBoxEstimate().setText("4");
		testNew.update();
		
		assertEquals(testName,testNew.getNewRequirement().getName());
		assertEquals(testDescription,testNew.getNewRequirement().getDescription());
		assertEquals(RequirementType.THEME,testNew.getNewRequirement().getType());
		assertEquals("Backlog",testNew.getNewRequirement().getIteration().getName());
		assertEquals(RequirementStatus.NEW,testNew.getNewRequirement().getStatus());
		assertEquals(RequirementPriority.HIGH,testNew.getNewRequirement().getPriority());
		assertEquals(4,testNew.getNewRequirement().getEstimate());
	}
	
	@Test
	public void errorThenUpdate()
	{
		NewRequirementPanel testNew = new NewRequirementPanel();
		String errorMessageRequiredName = "** Name is REQUIRED";
		String errorMessageRequiredDescription = "** Description is REQUIRED";
		String testName = "testName";
		String testDescription = "testDescription";
		
		testNew.getBoxEstimate().setText(" ");
		testNew.getBoxDescription().setText("   ");
		testNew.getBoxName().setText("");
		testNew.getButtonUpdate().doClick();
		
		assertEquals("",testNew.getErrorEstimate().getText());
		assertEquals(errorMessageRequiredName,testNew.getErrorName().getText());
		assertEquals(errorMessageRequiredDescription,testNew.getErrorDescription().getText());
		
		testNew.getBoxName().setText(testName);
		testNew.getBoxDescription().setText(testDescription);
		testNew.getDropdownType().setSelectedItem(RequirementType.SCENARIO);
		testNew.getPriorityMedium().doClick();
		testNew.getBoxEstimate().setText("0");
		testNew.validateFields();
		
		testNew.update();
		
		assertEquals(testName,testNew.getNewRequirement().getName());
		assertEquals(testDescription,testNew.getNewRequirement().getDescription());
		assertEquals(RequirementType.SCENARIO,testNew.getNewRequirement().getType());
		assertEquals("Backlog",testNew.getNewRequirement().getIteration().getName());
		assertEquals(RequirementStatus.NEW,testNew.getNewRequirement().getStatus());
		assertEquals(RequirementPriority.MEDIUM,testNew.getNewRequirement().getPriority());
		assertEquals(0,testNew.getNewRequirement().getEstimate());
		
		testNew.getDropdownType().setSelectedItem(RequirementType.EPIC);
		testNew.getPriorityLow().doClick();
		testNew.validateFields();
		testNew.update();

		assertEquals(RequirementType.EPIC,testNew.getNewRequirement().getType());
		assertEquals(RequirementPriority.LOW,testNew.getNewRequirement().getPriority());
		
		testNew.getDropdownType().setSelectedItem(RequirementType.NONFUNCTIONAL);
		testNew.getPriorityBlank().doClick();
		testNew.validateFields();
		testNew.update();

		assertEquals(RequirementType.NONFUNCTIONAL,testNew.getNewRequirement().getType());
		assertEquals(RequirementPriority.BLANK,testNew.getNewRequirement().getPriority());

		
	}

}