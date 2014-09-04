package edu.wpi.cs.wpisuitetng.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class TestWPISuiteException {
	
	@Test
	public void testConstructor() {
		WPISuiteException exception = new WPISuiteException();
		
		assertNotNull(exception);
		assertNull(exception.getMessage());
	}
	
	@Test
	public void testConstructorWithMessage() {
		WPISuiteException exception = new WPISuiteException("TestException");
		
		assertNotNull(exception);
		assertEquals("TestException", exception.getMessage());
	}
	
	@Test
	public void testGetStatus(){
		WPISuiteException exception = new WPISuiteException();

		assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception.getStatus());
	}
	
	@Test
	public void testSerialVersionUID() {
		assertEquals(-5271354512939175980L, WPISuiteException.serialVersionUID);
	}
}
