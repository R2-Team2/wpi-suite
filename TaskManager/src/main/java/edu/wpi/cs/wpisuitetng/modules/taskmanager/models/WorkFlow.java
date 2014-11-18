package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import java.util.ArrayList;
import java.util.List;

public class WorkFlow {
	private int workFlowID;
	private List<TaskStatus> taskStatusList;
	
	public WorkFlow() {
		taskStatusList = new ArrayList<TaskStatus>();
	}

	public int getWorkFlowID() {
		return workFlowID;
	}

	public void setWorkFlowID(int workFlowID) {
		this.workFlowID = workFlowID;
	}

	public List<TaskStatus> getTaskStatusList() {
		return taskStatusList;
	}

	public void setTaskStatusList(List<TaskStatus> taskStatusList) {
		this.taskStatusList = taskStatusList;
	}
	
	
}
