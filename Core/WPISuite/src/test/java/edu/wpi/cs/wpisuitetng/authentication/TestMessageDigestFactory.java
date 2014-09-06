package edu.wpi.cs.wpisuitetng.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class TestMessageDigestFactory {

    MessageDigestFactory factory = new MessageDigestFactory();

    @Test
    public void testConstructor() {
        assertNotNull(factory);
    }

    @Test
    public void testCreateMessageDigest() throws NoSuchAlgorithmException {
        MessageDigest digest = factory.createMessageDigest("SHA-256");

        assertNotNull(digest);
        assertEquals("SHA-256", digest.getAlgorithm());
    }
}
