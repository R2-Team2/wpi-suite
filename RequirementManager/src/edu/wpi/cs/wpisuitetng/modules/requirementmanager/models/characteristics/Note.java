/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics;

/**
 * A Note for a requirement;
 * need an id, a username, timestamp, and a string for the note
 * @author Gabriel McCormick
 * @author Brian Froehlich
 */
public class Note {
	
	private static int noteCount;
	private final int id;
	private String user;
	private long timestamp;
	private String message;
	
	/**
	 * Basic constructor for a note
	 * @param user Name of user who created the note
	 * @param timestamp Time at which note was created
	 * @param message Message to be stored within the note
	 */
	public Note (String user, long timestamp, String message){
		this.id = noteCount++;
		this.user = user;
		this.timestamp = timestamp;
		this.message = message;
		
	}

	/**
	 * Getter for the ID
	 * @return The ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter for the user
	 * @return The user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Setter for the user
	 * @param user The user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Getter for the timestamp
	 * @return The timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Setter for the timestamp
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Getter for the message
	 * @return The message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter for the message
	 * @param message The message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
