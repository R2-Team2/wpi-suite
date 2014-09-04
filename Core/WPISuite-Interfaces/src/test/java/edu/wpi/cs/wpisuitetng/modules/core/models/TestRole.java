package edu.wpi.cs.wpisuitetng.modules.core.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This is mostly for superficial code coverage
 * 
 * @author Robert Smieja
 */
public class TestRole {

	@Test
	public void testValues()
	{
		assertEquals(2, Role.values().length);
	}
	
	@Test
	public void testValueOf()
	{
		for(Role value : Role.values()){
			assertEquals(value, Role.valueOf(value.toString()));
		}
	}
}
