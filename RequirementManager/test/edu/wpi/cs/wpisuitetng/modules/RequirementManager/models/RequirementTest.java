/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.Transaction;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.TransactionHistory;
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
		assertTrue(newObject instanceof Requirement);

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
		
		object.setId(10);
		assertEquals(object.getId(), 10);
		
		object.setName("Changed");
		assertEquals(object.getName(), "Changed");
		object.setName("01234567890123456789012345678901234567890123456789012345678901234567890123545678901234567890123456789this is extra and should not be included");
		assertEquals(object.getName(), "0123456789012345678901234567890123456789012345678901234567890123456789012354567890123456789012345678");
		
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
		assertEquals(iter.next().getMessage(), "Changed status of Name from OPEN to INPROGRESS");
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
		
		object.setDescription("Changed the description too");
		assertEquals(object.getDescription(), "Changed the description too");

		// TODO: setPriority
		//if you change it to the current priority upon creation
		object.setPriority(RequirementPriority.BLANK, true);
		assertEquals(object.getPriority(), RequirementPriority.BLANK);
		//if requirement was just created
		object.setPriority(RequirementPriority.HIGH, true);
		assertEquals(object.getPriority(), RequirementPriority.HIGH);
		//normal priority setting
		object.setPriority(RequirementPriority.HIGH, false);
		assertEquals(object.getPriority(), RequirementPriority.HIGH);
		//if the you change it to the current priority
		object.setPriority(RequirementPriority.LOW, false);
		assertEquals(object.getPriority(), RequirementPriority.LOW);
		
		object.setType(RequirementType.USERSTORY);
		assertEquals(object.getType(), RequirementType.USERSTORY);
		
		// TODO: setIteration
		
	}
}
