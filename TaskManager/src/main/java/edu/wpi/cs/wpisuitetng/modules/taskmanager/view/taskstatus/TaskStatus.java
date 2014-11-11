/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pluxsuwong
 * This class contains the fields and methods for the Taskstatus
 */
public class TaskStatus {
	
	private String name;
	private List<String> taskList;
	
	/**
	 * Constructor for the Taskstatus class
	 */
	public TaskStatus(String name) {
		this.name = name;
		this.taskList = new ArrayList<String>();
	}

    /**
     * Gets the name of the object
     * 
     * @return name String
     */
    public String getName() {
       return this.name;
    }
    
    /**
     * Sets the name of the object
     * 
     * @param name String
     */
    public void setName(String name) {
       this.name = name;
    }

    /**
     * Gets the tasklist of the object
     * 
     * @return taskList ArrayList
     */
    public List<String> getTaskList() {
    	return this.taskList;
    }
    
    /**
     * Sets the tasklist of the object
     * 
     */
    public void setTaskList(List<String> taskList) {
    	this.taskList = taskList;
    }
}
