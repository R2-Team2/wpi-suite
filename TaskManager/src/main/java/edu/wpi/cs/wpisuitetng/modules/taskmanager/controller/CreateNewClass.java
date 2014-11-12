/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.characteristics.TaskStatus;

import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;


public class CreateNewClass implements ICreateNewTask {

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.ICreateNewTask#createNewTask(java.lang.String, java.lang.String, int, java.util.Date, java.util.ArrayList, int)
	 */
	@Override
	public int createNewTask(String title, String description,
			int estimatedEffort, Date dueDate, ArrayList<Integer> assigned,
			int associatedRequirement) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
//	//First attempt at creating a new task from task module
//	//and adding it to the database
//	public void getTask(String title, int id){
//		//Integer NewTask = id;
//		List<User> userList = new ArrayList<User>();
//		Date date = new Date();
//		
//		Task newTask = new Task(id, title, "Description", userList, 0, 0, date, new ArrayList<String>(), 0, TaskStatus.NEW);
//		//Task t1 = new Task(12, "Title", "Description", userList, 3, 2, date, new ArrayList<String>(), 0, TaskStatus.NEW);
//		//db.store(newTask);
//		System.out.println("Stored " + newTask);
//	}
//
//	final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.PUT);
//	//request.
//	
}
