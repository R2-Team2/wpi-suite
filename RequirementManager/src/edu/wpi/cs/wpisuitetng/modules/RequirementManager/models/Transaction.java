package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

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
	Transaction (String user, long timestamp, String message){
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
	 * Setter for the transaction user
	 * @param user Transaction user
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	/**
	 * Getter for the transaction time stamp
	 * @return Transaction time stamp
	 */
	public long getTS() {
		return timestamp;
	}
	
	/**
	 * Setter for the transaction time stamp
	 * @param timestamp Transaction time stamp
	 */
	public void setTS(long timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * Getter for the transaction message
	 * @return Transaction message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Setter for the transaction message
	 * @param message Transaction message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
