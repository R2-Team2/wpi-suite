package edu.wpi.cs.wpisuitetng.modules.taskmanager.actions;

public class WorkFlowAction extends AbstractAction {

    private Enum<WorkFlowAction.Actions> action;

    public static enum Actions {
        VIEW_TASK, EDIT_TASK;
    }

    public WorkFlowAction(Enum<WorkFlowAction.Actions> action) {
        this.action = action;
    }

    @Override
    Enum<WorkFlowAction.Actions> getAction() {
        return action;
    }

}
