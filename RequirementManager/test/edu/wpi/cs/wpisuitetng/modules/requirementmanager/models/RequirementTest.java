/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.Transaction;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

/**
 * Tests the jSON conversion functions and all the getters and setters
 * 
 * @author Benjamin Senecal
 */
public class RequirementTest {

	GetRequirementsController controller;

	@Before
	public void setUp() throws Exception {
		Network.initNetwork(new MockNetwork());
		Network.getInstance().setDefaultNetworkConfiguration(
				new NetworkConfiguration("http://wpisuitetng"));
		IterationModel.getInstance().setBacklog(new Iteration(1, "Backlog"));
		RequirementModel.getInstance().emptyModel();
	}	

	@Test
	public void jSONConversionTests() {
		Requirement object = new Requirement(4, "Test", "A test");

		assertEquals(object.getStatus(), RequirementStatus.NEW);
		assertEquals(object.getPriority(), RequirementPriority.BLANK);

		object.setRelease("1.1.01");
		object.setStatus(RequirementStatus.INPROGRESS, true);
		object.setPriority(RequirementPriority.MEDIUM, true);
		object.setEffort(10);
		object.setEstimate(1);

		Requirement origObject = object; // change here
		String jsonMessage = object.toJSON();
		Requirement newObject = Requirement.fromJson(jsonMessage); // change
																	// here...
		assertEquals(origObject.getId(), 4);
		assertEquals(origObject.getName(), "Test");
		assertEquals(origObject.getRelease(), "1.1.01");
		assertEquals(origObject.getStatus(), RequirementStatus.INPROGRESS);
		assertEquals(origObject.getPriority(), RequirementPriority.MEDIUM);
		assertEquals(origObject.getDescription(), "A test");
		assertEquals(origObject.getEstimate(), 1);
		assertEquals(origObject.getEffort(), 10);

		TransactionHistory history = origObject.getHistory();
		// History has to be created in GUI side
		// assertEquals(history.getItem(0).getMessage(),"Changed status from NEW to INPROGRESS")

	}
	
	/**
	 * Test the setting methods in the Requirement class in the src package
	 * 
	 * @author: Benjamin Senecal
	 */
	@Test
	public void settingRequirementFieldsTest () {
		Requirement object = new Requirement(5, "Test", "This is a test");
		
		// setId
		object.setId(10);
		assertEquals(object.getId(), 10);
		
		// setName
		object.setName("Changed");
		assertEquals(object.getName(), "Changed");
		object.setName("01234567890123456789012345678901234567890123456789012345678901234567890123545678901234567890123456789this is extra and should not be included");
		assertEquals(object.getName(), "0123456789012345678901234567890123456789012345678901234567890123456789012354567890123456789012345678");
		
		// setStatus
		//if requirement was just created
		object.setStatus(RequirementStatus.OPEN, true);
		assertEquals(object.getStatus(), RequirementStatus.OPEN);
		ListIterator<Transaction> iter = object.getHistory().getIterator(0);
		assertFalse(iter.hasNext());
		//normal status setting
		object.setName("Name");	// makes checking the transaction history easier
		object.setStatus(RequirementStatus.INPROGRESS, false);
		assertEquals(object.getStatus(), RequirementStatus.INPROGRESS);
		iter = object.getHistory().getIterator(0);
		assertEquals(iter.next().getMessage(), "Status changed from Open to In Progress");
		//if the you change it to the current status
		object.setStatus(RequirementStatus.INPROGRESS, false);
		assertEquals(object.getStatus(), RequirementStatus.INPROGRESS);
		iter = object.getHistory().getIterator(1);
		assertFalse(iter.hasNext());
		//if you change it to the current status upon creation
		object.setStatus(RequirementStatus.NEW, true);
		assertEquals(object.getStatus(), RequirementStatus.NEW);
		iter = object.getHistory().getIterator(1);
		assertFalse(iter.hasNext());
		
		// setDescription
		object.setDescription("Changed the description too");
		assertEquals(object.getDescription(), "Changed the description too");

		// setPriority
		//if you change it to the current priority upon creation
		object.setPriority(RequirementPriority.BLANK, true);
		assertEquals(object.getPriority(), RequirementPriority.BLANK);
		iter = object.getHistory().getIterator(1);
		assertFalse(iter.hasNext());
		//if requirement was just created
		object.setPriority(RequirementPriority.HIGH, true);
		assertEquals(object.getPriority(), RequirementPriority.HIGH);
		iter = object.getHistory().getIterator(1);
		assertFalse(iter.hasNext());
		//normal priority setting
		object.setPriority(RequirementPriority.HIGH, false);
		assertEquals(object.getPriority(), RequirementPriority.HIGH);
		iter = object.getHistory().getIterator(1);
		assertFalse(iter.hasNext());
		//if the you change it to the current priority
		object.setPriority(RequirementPriority.LOW, false);
		assertEquals(object.getPriority(), RequirementPriority.LOW);
		iter = object.getHistory().getIterator(1);
		assertEquals(iter.next().getMessage(), "Priority changed from High to Low");
		
		// setType
		object.setType(RequirementType.USERSTORY);
		assertEquals(object.getType(), RequirementType.USERSTORY);
		
		// TODO: setIteration
		
	}
	
	@Test
	public void testCopyFromRequirement() {
		Requirement r = new Requirement(0, "name", "desc");
		r.setEffort(4);
		r.setEstimate(4);
		r.setIteration("",false);
		r.setPriority(RequirementPriority.HIGH, true);
		r.setRelease("release 1");
		r.setStatus(RequirementStatus.INPROGRESS, true);
		r.setType(RequirementType.USERSTORY);
		
		Requirement r2 = new Requirement(0, "", "");
		r2.copyFrom(r);
		
		assertEquals(r.getId(), 0);
		assertEquals(r.getName(), "name");
		assertEquals(r.getDescription(), "desc");
		assertEquals(r.getPriority(), RequirementPriority.HIGH);
		assertEquals(r.getRelease(), "release 1");
		assertEquals(r.getStatus(), RequirementStatus.INPROGRESS);
		assertEquals(r.getType(), RequirementType.USERSTORY);
	}
	
	@Test
	public void testToString() {
		Requirement r = new Requirement(0, "name", "desc");
		assertEquals("name", r.toString());
		assertEquals(r.toString(), r.getName());
	}
	
	@Test
	public void testAssigningRequirementsToPeople() {
		List<String> peopleAssignedTo = Arrays.asList("Gabe", "Ben");
		Requirement r = new Requirement(0, "name", "desc");
		r.setAssignedTo(peopleAssignedTo);
		assertEquals(r.getAssignedTo(), peopleAssignedTo);
	}
	
	@Test
	public void testSubRequirementEstimateSumming()
	{
		Requirement parentRequirement = new Requirement(0, "", "");
		parentRequirement.setEstimate(1);
		
		Requirement childRequirement = new Requirement(1, "", "");
		childRequirement.setEstimate(32);
		
		
		Requirement childRequirement2 = new Requirement(2, "", "");
		childRequirement2.setEstimate(7);
		
		Requirement grandChildRequirement = new Requirement(3, "","");
		grandChildRequirement.setEstimate(12);
		
		RequirementModel.getInstance().addRequirement(parentRequirement);
		RequirementModel.getInstance().addRequirement(childRequirement);
		RequirementModel.getInstance().addRequirement(childRequirement2);
		RequirementModel.getInstance().addRequirement(grandChildRequirement);
		
		try {
			childRequirement.setParent(parentRequirement);		
			childRequirement2.setParent(parentRequirement);
			grandChildRequirement.setParent(childRequirement);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(parentRequirement.getTotalEstimate(), 52);
		assertEquals(childRequirement.getTotalEstimate(), 44);
		assertEquals(childRequirement2.getEstimate(), 7);
		assertEquals(grandChildRequirement.getEstimate(), 12);
		
	}
	
	@Test
	public void testZeroEstimate()
	{
		Requirement parentRequirement = new Requirement(0,"","");
		
		assertEquals(parentRequirement.getEstimate(), 0);
	}
	
	@Test
	public void testNoChildEstimate()
	{
		Requirement parentRequirement = new Requirement(0,"","");
		parentRequirement.setEstimate(3);
		
		assertEquals(parentRequirement.getEstimate(),3);
	}
	
}
