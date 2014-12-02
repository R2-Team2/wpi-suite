package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

public class WorkFlow extends AbstractModel {
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

    @Override
    public void save() {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub

    }

    @Override
    public String toJson() {
        return new Gson().toJson(this, WorkFlow.class);
    }

    @Override
    public Boolean identify(Object o) {
        // TODO Auto-generated method stub
        return null;
    }

    public static WorkFlow fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, WorkFlow.class);
    }

    public void update(WorkFlow updatedWorkFlow) {
        taskStatusList = updatedWorkFlow.taskStatusList;

    }

}
