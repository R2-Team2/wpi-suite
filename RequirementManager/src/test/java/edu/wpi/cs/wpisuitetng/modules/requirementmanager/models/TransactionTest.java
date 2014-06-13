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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.Transaction;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;

/**
 * @author Dylan
 *
 * @version $Revision: 1.0 $
 */
public class TransactionTest {

	/**
	 * Tests creating a transaction and retrieving the proper values back
	 */
	@Test
	public void testCreateTransaction() {
		String u = "joe";
		Transaction t = new Transaction(u, 1234567, "changed priority from LOW to HIGH");
		assertNotNull(t);
		assertSame(t.getUser(), u);
		assertEquals(t.getTS(), 1234567);
		assertEquals(t.getMessage(), "changed priority from LOW to HIGH");
	}
	
	/**
	 * Tests creating a transaction history and adding transactions to it
	 */
	@Test
	public void testTransactionHistory() {
		TransactionHistory th = new TransactionHistory();
		assertEquals(th.getHistory().size(), 0);
		
		th.add("changed priority from LOW to HIGH");
		assertEquals(th.getHistory().size(), 1);
		
		Transaction t = th.getItem(0);
		assertNotNull(t.getTS());
		assertEquals(t.getMessage(), "changed priority from LOW to HIGH");
		//assertNotNull(t.getUser());
		
		th.add("changed priority from HIGH to LOW");
		assertEquals(th.getHistory().size(), 2);
	}
	
	/**
	 * Tests adding transactions to a transaction log within the Requirement class
	 */
	@Test
	public void testTransactionsWithRequirements() {
		Requirement r = new Requirement(1, "Edit estimate", "Allow user to add estimates to requirements.");
		r.getHistory().add("changed priority from LOW to HIGH");
		r.getHistory().add("changed priority from HIGH to LOW");

		
		assertEquals(r.getHistory().getItem(0).getMessage(), "changed priority from LOW to HIGH");
		assertEquals(r.getHistory().getItem(1).getMessage(), "changed priority from HIGH to LOW");

	}

}
