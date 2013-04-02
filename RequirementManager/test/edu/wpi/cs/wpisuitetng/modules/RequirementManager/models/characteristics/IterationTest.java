package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Dylan
 *
 */
public class IterationTest {

	@Test
	public void createNewIterationWithNoName() {
		assertEquals(new Iteration("").getName(), "Backlog");
		assertEquals(new Iteration("").toString(), new Iteration("").getName());
	}
	
	@Test
	public void testRenameIteration() {
		Iteration i = new Iteration("Iteration 1");
		i.setName("Iteration 2");
		assertEquals("Iteration 2", i.getName());
	}

	@Test
	public void testEqualityWithEqualIterations() {
		assertTrue(new Iteration("Iteration 1").equals(new Iteration("Iteration 1")));
		assertTrue(new Iteration("").equals(new Iteration("")));
	}
	
	@Test
	public void testEqualityWithDifferentIterations() {
		assertFalse(new Iteration("Iteration 1").equals(new Iteration("Iteration 2")));
	}
	
}
