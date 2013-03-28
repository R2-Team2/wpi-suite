/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementStatus;
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
		Network.getInstance().setDefaultNetworkConfiguration(new NetworkConfiguration("http://wpisuitetng"));
		
	}

	@Test
	  public void jSONConversionTests() {
	    Requirement object = new Requirement(4, "Test", "A test");
	    
	    assertEquals(object.getStatus(),RequirementStatus.NEW);
	    assertEquals(object.getPriority(),RequirementPriority.BLANK);
	    
	    object.setRelease("1.1.01");
	    object.setStatus(RequirementStatus.INPROGRESS, true);
	    object.setPriority(RequirementPriority.MEDIUM, true);
	    object.setEffort(10);
	    object.setEstimate(1);
	    
	    Requirement origObject = object;  // change here
	    String jsonMessage = object.toJSON();
	    Requirement newObject = Requirement.fromJson(jsonMessage);  // change here...
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
//	    assertEquals(history.getItem(0).getMessage(),"Changed status from NEW to INPROGRESS");
	    
	    
	}
}
