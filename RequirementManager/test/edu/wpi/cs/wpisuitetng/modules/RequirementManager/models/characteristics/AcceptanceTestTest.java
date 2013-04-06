/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Dylan
 *
 */
public class AcceptanceTestTest {

	@Test
	public void createTestAndRetrieveAttributes() {
		AcceptanceTest at = new AcceptanceTest("name", "description");
		assertEquals(at.getName(), "name");
		assertEquals(at.getDescription(), "description");
		assertEquals(at.getStatus(), TestStatus.STATUS_BLANK);
		
		AcceptanceTest at2 = new AcceptanceTest("a", "b");
		at2.setName("name");
		at2.setDescription("desc");
		at2.setStatus(TestStatus.STATUS_PASSED);
		assertEquals(at2.getName(), "name");
		assertEquals(at2.getDescription(), "desc");
		assertEquals(at2.getStatus(), TestStatus.STATUS_PASSED);
	}
	
	@Test
	public void testThatIdsIncrement() {
		AcceptanceTest at = new AcceptanceTest("test 1", "test 1");
		AcceptanceTest at2 = new AcceptanceTest("test2", "test 2");
		assertTrue(at2.getId() == at.getId() + 1);
	}

	@Test
	public void testNameLongerThan100Chars() {
		AcceptanceTest at = new AcceptanceTest("name", "desc");
		// 101 characters
		at.setName("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
		assertEquals(at.getName().length(), 100);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullName() {
		// Assert an error is thrown when a name is not given
		AcceptanceTest at = new AcceptanceTest("", "desc");
	}
	
}
