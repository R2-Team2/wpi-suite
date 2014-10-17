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

public class TestBadRequestException {
    @Test
    public void testConstructor() {
        BadRequestException exception = new BadRequestException();

        assertNotNull(exception);
        assertNull(exception.getMessage());
    }

    @Test
    public void testConstructorWithMessage() {
        BadRequestException exception = new BadRequestException("TestException");

        assertNotNull(exception);
        assertEquals("TestException", exception.getMessage());
    }

    @Test
    public void testGetStatus() {
        BadRequestException exception = new BadRequestException();

        assertEquals(HttpServletResponse.SC_BAD_REQUEST, exception.getStatus());
    }
}
