package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

/**
 * An iteration in a project. Requirements can be assigned to an iteration.
 * @author Gabriel McCormick
 */
public class Iteration {

	private String name;
	
	public Iteration(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
}
