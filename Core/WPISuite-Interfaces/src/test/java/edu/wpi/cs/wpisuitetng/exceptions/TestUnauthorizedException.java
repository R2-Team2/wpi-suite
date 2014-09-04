/*******************************************************************************
 * Copyright (c) 2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Robert Smieja
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class TestUnauthorizedException {
	
	@Test
	public void testConstructor() {
		UnauthorizedException exception = new UnauthorizedException();
		
		assertNotNull(exception);
		assertNull(exception.getMessage());
	}
	
	@Test
	public void testConstructorWithMessage() {
		UnauthorizedException exception = new UnauthorizedException("TestException");
		
		assertNotNull(exception);
		assertEquals("TestException", exception.getMessage());
	}
	
	@Test
	public void testGetStatus(){
		UnauthorizedException exception = new UnauthorizedException();

		assertEquals(HttpServletResponse.SC_UNAUTHORIZED, exception.getStatus());
	}
	
	@Test
	public void testSerialVersionUID() {
		assertEquals(9127615601542990581L, UnauthorizedException.serialVersionUID);
	}
}
