package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class Transaction {
	
	User user;
	long timestamp;
	String message;
	
	Transaction (User user, long timestamp, String message){
		this.user = user;
		this.timestamp = timestamp;
		this.message = message;
	}

}
