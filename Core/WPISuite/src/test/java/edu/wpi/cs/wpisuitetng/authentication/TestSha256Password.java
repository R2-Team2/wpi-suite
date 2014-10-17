package edu.wpi.cs.wpisuitetng.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class TestSha256Password {
    private Logger mockLogger = mock(Logger.class);
    private MessageDigestFactory factorySpy = spy(new MessageDigestFactory());
    private Sha256Password password;

    @Before
    public void setup() throws Exception {
        Sha256Password.setLogger(mockLogger);
        Sha256Password.setDigestFactory(factorySpy);

        doCallRealMethod().when(factorySpy).createMessageDigest(anyString());

        password = new Sha256Password();
    }

    @Test
    public void testConstructor() {
        assertNotNull(password);
        assertNotNull(password.getHashDigest());
    }

    @Test
    public void testConstructor_noSuchAlgorithmThrown() throws Exception {
        doThrow(new NoSuchAlgorithmException()).when(factorySpy).createMessageDigest("SHA-256");

        password = new Sha256Password();

        verify(mockLogger, times(1)).log(Level.SEVERE, "Password Hashing System has failed to instantiate. NoSuchAlgorithmException thrown.");
        assertNull(password.getHashDigest());
    }

    @Test
    public void testGenerateHash() throws UnsupportedEncodingException {
        MessageDigest mockDigest = mock(MessageDigest.class);
        String passwordString = "TestPassword";
        String hashedPasswordString = "HashedPassword";

        when(mockDigest.digest()).thenReturn(hashedPasswordString.getBytes());
        password.setHashDigest(mockDigest);

        String result = password.generateHash(passwordString);

        verify(mockLogger, times(1)).log(Level.FINE, "Attempting password hashing...");
        verify(mockDigest, times(1)).reset();
        verify(mockDigest, times(1)).update(passwordString.getBytes("UTF-8"));
        verify(mockLogger, times(1)).log(Level.FINE, "Password hashing success!");

        assertEquals(hashedPasswordString, result);
    }

    @Test
    public void testValidate() {
        password = spy(password);
        doReturn("Hash").when(password).generateHash("Password");

        assertTrue(password.validate("Password", "Hash"));
    }
}
