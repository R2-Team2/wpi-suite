/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Dylan
 *
 */
public class RequirementManagerTest {

	@Test
	public void testCreateRequirement() {
		assertNotNull(new Requirement("Bob", "1.0", RequirementStatus.DUMMY, "Desc", 1, 1));
	}

}
