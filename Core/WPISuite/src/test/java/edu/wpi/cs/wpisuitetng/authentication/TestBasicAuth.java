/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    twack
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.exceptions.AuthenticationException;

public class TestBasicAuth {
    String header = "Authorization: Basic "; // the static BasicAuth header
    BasicAuth basic = new BasicAuth();

    /**
     * When given an improperly formatted POST-string (not in 3 parts)
     * then expect an exception
     * 
     * @throws AuthenticationException
     */
    @Test(expected = AuthenticationException.class)
    public void testInvalidTokenFormat() throws AuthenticationException
    {
        this.basic.parsePost("bahston");
    }

    /**
     * Generates a valid BasicAuth token with invalidly formatted
     * credentials. Decoding should work properly. An exception should
     * raise because the credential string is not in the "String:String" format.
     */
    @Test(expected = AuthenticationException.class)
    public void testInvalidCredentialFormat() throws AuthenticationException
    {
        String authToken = "Basic ";
        String credentials = "abcdefg123456";
        authToken += Base64.encodeBase64String(credentials.getBytes());

        this.basic.parsePost(authToken);
    }

    /**
     * Tests a situation when a valid BasicAuth token is given
     * to the POST string parser.
     * 
     * @throws AuthenticationException
     */
    @Test
    public void testValidFormat() throws AuthenticationException
    {
        String username = "twack";
        String pass = "12345";

        String basicAuthToken = BasicAuth.generateBasicAuth(username, pass);
        String[] credentials = this.basic.parsePost(basicAuthToken);

        assertEquals(credentials.length, 2);
        assertTrue(credentials[0].equals(username));
        assertTrue(credentials[1].equals(pass));
    }

    @Test
    public void TestIsValidBasicAuth()
    {
        String[] basicAuth = { "Basic", "Base64" };

        assertTrue(basic.isValidBasicAuth(basicAuth));
    }

    @Test
    public void TestIsValidBasicAuth_IncorrectFormat_Length()
    {
        String[] basicAuth = { "NotBasic" };

        assertFalse(basic.isValidBasicAuth(basicAuth));
    }

    @Test
    public void TestIsValidBasicAuth_IncorrectFormat_NotBasic()
    {
        String[] basicAuth = { "NotBasic", "##NotBase64##" };

        assertFalse(basic.isValidBasicAuth(basicAuth));
    }

    @Test
    public void TestIsValidBasicAuth_NotBase64()
    {
        String[] basicAuth = { "Basic", "##NotBase64##" };

        assertFalse(basic.isValidBasicAuth(basicAuth));
    }
}
