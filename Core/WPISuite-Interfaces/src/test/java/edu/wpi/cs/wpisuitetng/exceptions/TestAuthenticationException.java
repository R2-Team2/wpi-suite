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

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class TestAuthenticationException {
    AuthenticationException exception = new AuthenticationException("TestException");

    @Test
    public void testConstructor() {
        assertNotNull(exception);
        assertEquals("TestException", exception.getMessage());
    }

    @Test
    public void testGetStatus() {
        assertEquals(HttpServletResponse.SC_FORBIDDEN, exception.getStatus());
    }
}
