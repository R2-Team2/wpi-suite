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
package edu.wpi.cs.wpisuitetng.authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A factory to create and return MessageDigests
 * 
 * @author Robert Smieja
 */
public class MessageDigestFactory {

    public MessageDigest createMessageDigest(String algorithm) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algorithm);
    }

}
