/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;

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
		assertEquals(new Iteration(0,"").getName(), "Backlog");
		assertEquals(new Iteration(0,"").toString(), new Iteration(0,"").getName());
	}
	
	@Test
	public void testRenameIteration() {
		Iteration i = new Iteration(0,"Iteration 1");
		i.setName("Iteration 2");
		assertEquals("Iteration 2", i.getName());
	}

	@Test
	public void testEqualityWithEqualIterations() {
		assertTrue(new Iteration(0,"Iteration 1").equals(new Iteration(0,"Iteration 1")));
		assertTrue(new Iteration(0,"").equals(new Iteration(0,"")));
	}
	
	@Test
	public void testEqualityWithDifferentIterations() {
		assertFalse(new Iteration(0,"Iteration 1").equals(new Iteration(0,"Iteration 2")));
	}
	
}
