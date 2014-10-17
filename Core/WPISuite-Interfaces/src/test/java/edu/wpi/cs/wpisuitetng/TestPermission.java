package edu.wpi.cs.wpisuitetng;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This is mostly for superficial code coverage
 * 
 * @author Robert Smieja
 */
public class TestPermission {
    @Test
    public void testValues()
    {
        assertEquals(2, Permission.values().length);
    }

    @Test
    public void testValueOf()
    {
        for (Permission value : Permission.values()) {
            assertEquals(value, Permission.valueOf(value.toString()));
        }
    }
}