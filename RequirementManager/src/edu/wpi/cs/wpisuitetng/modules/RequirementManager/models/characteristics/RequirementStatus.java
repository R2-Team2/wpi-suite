
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

/**
 * Various status' that a Requirement can have 
 * @author david
 */
public enum RequirementStatus {
	/**
	 * Indicates that the Requirement has only been created
	 */
	NEW,
	/**
	 * Indicates that the Requirement has been assigned to an iteration
	 */
	INPROGRESS,
	/**
	 * Indicates that the Requirement has been removed from an iteration and returned to the backlog
	 */
	OPEN,
	/**
	 * Indicates that the Requirement has been accepted by the customer
	 */
	COMPLETE,
	/**
	 * Indicates that the Requirement has been deleted
	 */
	DELETED
}
