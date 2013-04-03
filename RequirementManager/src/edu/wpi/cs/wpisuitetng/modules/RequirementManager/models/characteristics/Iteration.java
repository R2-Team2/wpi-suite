package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.IterationController.UpdateIterationController;

/**
 * An iteration in a project. Requirements can be assigned to an iteration.
 * 
 * @author Gabriel McCormick, David Iglesias, Nick Mollica
 */
public class Iteration extends AbstractModel {
	/** the ID of the iteration */
	private int id; // TODO: move ID stuff to server side? (copied from requirement impl.)
	
	/** the name of the iteration */
	private String name;
	
	/** the estimated amount of effort to complete the iteration */
	private int estimate;

	public Iteration(){}
	
	/**
	 * Construct an Iteration with required properties provided and others set
	 * to default
	 * 
	 * @param id
	 *            The ID number of the iteration
	 * @param name
	 *            The name of the iteration
	 * 
	 */
	public Iteration(int id, String name) {
		this.id = id;
		this.name = name;
		if (name.trim().length() == 0)
			this.name = "Backlog";
		this.estimate = 0;
	}

	/**
	 * Getter for the id
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for the id
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * getter for the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for the name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the estimate
	 * 
	 * @return the estimate
	 */
	public int getEstimate() {
		return estimate;
	}

	/**
	 * Setter for the estimate
	 * 
	 * @param estimate
	 *            the estimate to set
	 */
	public void setEstimate(int estimate) {
		System.out.println("Changed iteration " + name + " estimate from " + this.estimate + " to " + estimate);
		this.estimate = estimate;
		UpdateIterationController.getInstance().updateIteration(this);
	}
	
	/**
	 * represents iteration as a string which is currently just the name
	 * 
	 * @return the name
	 */
	// should this be extended for the other fields?
	public String toString() {
		return name;
	}

	/** 
	 * compares two iterations for equality based on name
	 * @param that
	 * 			iteration to compare to
	 * @return boolean for equality
	 */
	// should this also be extended for the other fields?
	public boolean equals(Iteration that) {
		if (this.name.equals(that.getName()))
			return true;
		else
			return false;
	}
	
	/**
	 * Copies all of the values from the given iteration to this iteration.
	 * 
	 * @param toCopyFrom
	 *            the iteration to copy from.
	 */
	public void copyFrom(Iteration toCopyFrom) {
		this.name = toCopyFrom.name;
		this.estimate = toCopyFrom.estimate;
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
	
	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}


}
