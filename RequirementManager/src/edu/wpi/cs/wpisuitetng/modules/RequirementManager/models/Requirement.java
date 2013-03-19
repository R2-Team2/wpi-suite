package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import com.google.gson.Gson;
/**
 * Basic Requirement class
 * 
 * @author david
 *
 */
public class Requirement extends AbstractModel {
	private int id;
	private String name;
	private String release;
	private RequirementStatus status;
	private String description;
	private int estimate;
	private int effort;
	
	public Requirement(int id, String name, String release, RequirementStatus status, String description, int estimate, int effort){
		this.id = id;
		this.name = name;
		this.release = release;
		this.status = status;
		this.description = description;
		this.estimate = estimate;
		this.effort = effort;
		
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the release
	 */
	public String getRelease() {
		return release;
	}

	/**
	 * @param release the release to set
	 */
	public void setRelease(String release) {
		this.release = release;
	}

	/**
	 * @return the status
	 */
	public RequirementStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(RequirementStatus status) {
		this.status = status;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the estimate
	 */
	public int getEstimate() {
		return estimate;
	}

	/**
	 * @param estimate the estimate to set
	 */
	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

	/**
	 * @return the effort
	 */
	public int getEffort() {
		return effort;
	}

	/**
	 * @param effort the effort to set
	 */
	public void setEffort(int effort) {
		this.effort = effort;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * This returns a Json encoded String representation of this message object.
	 */
	public String toJSON() {
		return new Gson().toJson(this, Requirement.class);
	}
	
	

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
