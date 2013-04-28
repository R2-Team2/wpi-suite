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
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs.SingleAcceptanceTestPanel;

/**
 * Tests the AcceptanceTest.java and SingleAcceptanceTestPanel.java souece files
 * @author Rolling Thunder
 *
 * @version $Revision: 1.0 $
 */
public class AcceptanceTestTest {

	//===== AcceptanceTest.java tests =====
	
	/**
	 * test the creation and retrieval of tests.
	 */
	@Test
	public void createTestAndRetrieveAttributes() {
		AcceptanceTest at = new AcceptanceTest(0, "name", "description");
		assertEquals(at.getName(), "name");
		assertEquals(at.getDescription(), "description");
		assertEquals(at.getStatus(), "");
		
		AcceptanceTest at2 = new AcceptanceTest(1, "a", "b");
		at2.setName("name");
		at2.setDescription("desc");
		at2.setStatus(TestStatus.STATUS_PASSED);
		assertEquals(at2.getName(), "name");
		assertEquals(at2.getDescription(), "desc");
		assertEquals(at2.getStatus(), "Passed");
	}

	/**
	 * Test that name cannot be longer than 100 chars
	 */
	@Test
	public void testNameLongerThan100Chars() {
		AcceptanceTest at = new AcceptanceTest(2, "name", "desc");
		// 101 characters
		at.setName("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
		assertEquals(at.getName().length(), 100);
	}
	
	/**
	 * Test that null cannot be name
	 */
	@Test(expected=NullPointerException.class)
	public void testNullName() {
		// Assert an error is thrown when a name is not given
		new AcceptanceTest(3, "", "desc");
	}
	
	@Test
	public void makeANotNullAcceptanceTest() {
		assertNotNull(new AcceptanceTest(1, "First Test", "a test for a test"));
	}
	
	@Test
	public void getAcceptanceTestIdTest() {
		assertEquals(1, new AcceptanceTest(1, "test", "test description").getId());
	}

	//===== SingleAcceptanceTestPanel.java tests =====
	
	@Test
	public void testToSeeIfANotNullAcceptanceTestPanelCanBeMade() {
		Requirement testReq = new  Requirement(1, "reqName", "Description");
		AcceptanceTest testAT = new AcceptanceTest(2, "Name", "An acceptance test for testing");
		assertNotNull(new SingleAcceptanceTestPanel(testReq, testAT));
	}
	
	@Test
	public void testSettingThePassFailDropDownStatus() {
		Requirement testReq = new  Requirement(1, "reqName", "Description");
		AcceptanceTest testAT = new AcceptanceTest(2, "Name", "An acceptance test for testing");
		testAT.setStatus(TestStatus.STATUS_PASSED);
		SingleAcceptanceTestPanel ATpan = new SingleAcceptanceTestPanel(testReq, testAT);
		assertEquals(TestStatus.STATUS_PASSED, ATpan.getDropdownStatus().getSelectedItem());
		testAT.setStatus(TestStatus.STATUS_FAILED);
		ATpan = new SingleAcceptanceTestPanel(testReq, testAT);
		assertEquals(TestStatus.STATUS_FAILED, ATpan.getDropdownStatus().getSelectedItem());
	}
	
	@Test
	public void testAddingAnAcceptanceTestThroughARequirement() {
		Requirement testReq = new  Requirement(1, "reqName", "Description");
		testReq.addTest(new AcceptanceTest(2, "Name", "Description"));
		assertEquals(1, testReq.getTests().size());
	}
	
	@Test
	public void testUpdateTestStatus() {
		Requirement testReq = new  Requirement(1, "reqName", "Description");
		testReq.addTest(new AcceptanceTest(1, "First Name", "Description"));
		AcceptanceTest testAT = new AcceptanceTest(2, "Name", "Description");
		testAT.setStatus(TestStatus.STATUS_FAILED);
		testReq.addTest(testAT);
		assertEquals("Failed", testReq.getTests().get(1).getStatus());
		testReq.updateTestStatus(2, TestStatus.STATUS_PASSED);
		assertEquals("Passed", testReq.getTests().get(1).getStatus());
	}
	
	@Test
	public void testRemovingAnAcceptanceTest() {
		Requirement testReq = new  Requirement(1, "reqName", "Description");
		testReq.addTest(new AcceptanceTest(1, "First Name", "Description"));
		testReq.addTest(new AcceptanceTest(2, "Second Name", "Description"));
		assertEquals(2, testReq.getTests().size());
		testReq.removeTest(2);
		assertEquals(1, testReq.getTests().size());
		testReq.removeTest(4);
		assertEquals(1, testReq.getTests().size());
		testReq.removeTest(1);
		assertEquals(0, testReq.getTests().size());
	}
}
