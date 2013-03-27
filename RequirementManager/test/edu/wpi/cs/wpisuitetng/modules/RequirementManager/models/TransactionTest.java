/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * @author Dylan
 *
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
		assertEquals(r.getHistory().getItem(0).getMessage(), "REQUIREMENT CREATED");
		assertEquals(r.getHistory().getItem(1).getMessage(), "changed priority from LOW to HIGH");
		assertEquals(r.getHistory().getItem(2).getMessage(), "changed priority from HIGH to LOW");
	}

}
