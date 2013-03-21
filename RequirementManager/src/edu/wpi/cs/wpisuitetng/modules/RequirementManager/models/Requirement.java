package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;


import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

/**
 * Basic Requirement class
 * 
 * @author david
 *
 */
public class Requirement extends AbstractModel {
	/**  the ID of the requirement */
	private int id;
	/**  the name of the requirement */
	private String name;
	/**  the release number of the requirement */
	private String release;
	/**  the project status of the requirement */
	private RequirementStatus status;

	/**  the priority of the requirement */
	private RequirementPriority priority;
	/**  a short description of the requirement */

	private String description;
	/**  the estimated amount of time to complete the requirement */
	private int estimate;
	
	/**  the estimated effort of completing the requirement */
	private int effort;
	
	/** a flag indicating if the requirement is active or deleted */
	private boolean activeStatus;
	
	
	/**
	 * Constructs a Requirement with default characteristics
	 */
	public Requirement() {
		super();
		id = -1;
		name = description = "";
		release = "";
		status = RequirementStatus.NEW;
		priority = RequirementPriority.BLANK;
		estimate = effort = 0;
		activeStatus = true;
	}

	/**
	 * Construct a Requirement with required properties provided and others set to default
	 * 
	 * @param id The ID number of the requirement
	 * @param name The name of the requirement
	 * @param description A short description of the requirement
	 */
	public Requirement(int id, String name, String description) {
		this();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/**
	 * Constructs a requirement from the provided characteristics
	 * 
	 * @param id The ID number of the requirement
	 * @param name The name of the requirement
	 * @param release The release number of the requirement
	 * @param status The status of the requirement
	 * @param description A short description of the requirement
	 * @param estimate The estimated time required by the task. This is in a point system decided by the user
	 * @param effort The estimated amount of work required by the requirement.
	 * @deprecated
	 */
	@Deprecated
	public Requirement(int id, String name, String release, RequirementStatus status, RequirementPriority priority, String description, int estimate, int effort){
		this.id = id;
		this.name = name;
		this.release = release;
		this.status = status;
		this.priority = priority;
		this.description = description;
		this.estimate = estimate;
		this.effort = effort;
	}

	public static Requirement fromJson(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Requirement.class);
	}

	/**
	/**Getter for id
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**Setter for the id
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**getter for the id
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**setter for the name
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name.substring(0, 100);
	}

	/**getter for the name
	 * 
	 * @return the release
	 */
	public String getRelease() {
		return release;
	}

	/**Setter for the release number
	 * 
	 * @param release the release to set
	 */
	public void setRelease(String release) {
		this.release = release;
	}

	/**Getter for the release number
	 * 
	 * @return the status
	 */
	public RequirementStatus getStatus() {
		return status;
	}

	/**Setter for the status
	 * 
	 * @param status the status to set
	 */
	public void setStatus(RequirementStatus status) {
		this.status = status;
	}

	/**Getter for the description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**Setter for the description
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**Getter for the estimate
	 * 
	 * @return the estimate
	 */
	public int getEstimate() {
		return estimate;
	}

	/**Setter for the estimate
	 * 
	 * @param estimate the estimate to set
	 */
	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

	/**Getter for the estimate
	 * 
	 * @return the effort
	 */
	public int getEffort() {
		return effort;
	}

	/**Setter for the effort
	 * 
	 * @param effort the effort to set
	 */
	public void setEffort(int effort) {
		this.effort = effort;
	}

	/**Getter for the priority
	 * 
	 * @return the priority
	 */
	public RequirementPriority getPriority() {
		return priority;
	}

	/**Setter for the priority
	 * 
	 * @param priority the priority to set
	 */
	public void setPriority(RequirementPriority priority) {
		this.priority = priority;
	}
	
	/**Sets a flag in the requirement to indicate it's deleted
	 */
	public void remove() {
		this.activeStatus = false;
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

	/**This returns a Json encoded String representation of this requirement object.
	 * 
	 * @return a Json encoded String representation of this requirement
	 * 
	 */
	public String toJSON() {
		return new Gson().toJson(this, Requirement.class);
	}
	
	public static Requirement[] fromJsonArray(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Requirement[].class);
	}

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return this.getDescription();
	}

	public boolean isDeleted() {
		return !activeStatus;
	}

}
