package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.util.Date;
import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatus;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class TaskController {
	private final AbstractTaskPanel view;
	
	public TaskController(AbstractTaskPanel view) {
		this.view = view;
	}
	
	public void updateTask(Task toSave) {
		// Send a request to the core to save this message
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.POST); // POST == save
		request.setBody(toSave.toJson()); // put the new message in the body of the request
		request.addObserver(new TaskRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
		System.out.println("Sent task to database");
		//		}
	}
}
