package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

/**
 * An iteration in a project. Requirements can be assigned to an iteration.
 * 
 * @author Gabriel McCormick, David Iglesias
 */
public class Iteration {

	private String name;
	private int estimate;

	public Iteration(String name) {
		this.name = name;
		if (name.trim().length() == 0)
			this.name = "Backlog";
		this.estimate = 0;
	}

	public String getName() {
		return name;
	}

	public int getEstimate() {
		return estimate;
	}

	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public boolean equals(Iteration that) {
		if (this.name.equals(that.getName()))
			return true;
		else
			return false;
	}

	public static Iteration[] fromJsonArray(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	public String toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Iteration fromJson(
			String body) {
		// TODO Auto-generated method stub
		return null;
	}
}
