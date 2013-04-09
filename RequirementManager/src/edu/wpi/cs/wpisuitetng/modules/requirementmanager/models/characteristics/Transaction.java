package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
/**
 * Class for a single transaction
 * 
 * @author Kevin
 *
 */
public class Transaction {
	
	String user;
	long timestamp;
	String message;
	
	/**
	 * Constructor for a transaction
	 * @param user User who has executed the transaction
	 * @param timestamp Time at which transaction occurred
	 * @param message Message detailing transaction that occurred
	 */
	public Transaction (String user, long timestamp, String message){
		this.user = user;
		this.timestamp = timestamp;
		this.message = message;
	}
	
	/**
	 * Getter for the transaction user
	 * @return Transaction user
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * Getter for the transaction time stamp
	 * @return Transaction time stamp
	 */
	public long getTS() {
		return timestamp;
	}
	
	/**
	 * Getter for the transaction message
	 * @return Transaction message
	 */
	public String getMessage() {
		return message;
	}

}
