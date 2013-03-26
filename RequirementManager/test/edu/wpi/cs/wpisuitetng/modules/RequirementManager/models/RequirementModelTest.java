package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class RequirementModelTest {

	@Test
	public void addRequirementTests(){
		RequirementModel model = RequirementModel.getInstance();
		assertEquals(model.getSize(), 0);
		Requirement object = new Requirement(4, "requirement a", "A requirement used for testing.");
		model.addRequirement(object);
		assertEquals(model.getSize(), 1);
		Requirement objectReturned = model.getElementAt(0);
		assertEquals(objectReturned.getName(), "requirement a");
		assertEquals(objectReturned.getId(), 4);
		assertEquals(objectReturned.getDescription(), "A requirement used for testing.");
	}
}
