/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics;

/**
 * Statuses an acceptance test can have
 * @author Dylan
 *
 */
public enum TestStatus {
	/**
	 * The user has not assigned a status to the acceptance test
	 */
	STATUS_BLANK(""),
	/**
	 * Indicates the acceptance test has passed
	 */
	STATUS_PASSED("Passed"),
	/**
	 * Indicates the acceptance test has failed
	 */
	STATUS_FAILED("Failed");
	
	private String desc;
	TestStatus(String desc) {
		this.desc = desc;
	}
	
	public String toString() {
		return desc;
	}
	
}

